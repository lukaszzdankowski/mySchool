package my.school.homework;

import my.school.exam.Exam;
import my.school.exam.ExamRepository;
import my.school.reply.Reply;
import my.school.reply.ReplyRepository;
import my.school.testing.ConsoleColors;
import my.school.user.User;
import my.school.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/homework/crud")
public class HomeworkCrudController {
    private final HomeworkRepository homeworkRepository;
    private final ExamRepository examRepository;
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;

    public HomeworkCrudController(HomeworkRepository homeworkRepository, ExamRepository examRepository, ReplyRepository replyRepository, UserRepository userRepository) {
        this.homeworkRepository = homeworkRepository;
        this.examRepository = examRepository;
        this.replyRepository = replyRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute("examlist")
    public List<Exam> examList(){
        return examRepository.findAll();
    }
    @ModelAttribute("studentlist")
    public List<User> studentList(){
        return userRepository.findAll().stream()
                .filter(user -> user.getRole().equals("student")).collect(Collectors.toList());
    }

    @GetMapping("/showall")
    public String showAllHomeworks(Model model){
        List<Homework> homeworks = homeworkRepository.findAll();
        model.addAttribute("homeworks",homeworks);
        return "/homework/crud/showall";
    }
    @PostMapping("/save")
    public String saveHomework(@RequestParam long[] students,
                               @RequestParam long exam){
        for (long studentId : students) {
            Homework homework = new Homework();
            homework.setUser(userRepository.findById(studentId).orElse(null));
            homework.setExam(examRepository.findById(exam).orElse(null));
            homeworkRepository.save(homework);
            homework.getExam().getTasks().stream()
                    .forEach(task -> {
                        Reply reply = new Reply();
                        reply.setTask(task);
                        reply.setHomework(homework);
                        replyRepository.save(reply);
                    });
        }
        return "redirect: /homework/crud/showall";
    }
    @GetMapping("/add")
    public String addHomework(){
        return "/homework/crud/add";
    }

    @GetMapping("/show/{id}")
    public String showHomework(@PathVariable Long id, Model model){
        Homework homework = homeworkRepository.getHomeworkWithReplies(id);
        if (homework == null){
            return "/homework/crud/nohomework";
        }
        model.addAttribute(homework);
        return "/homework/crud/showone";
    }
    @GetMapping("/delete/{id}")
    public String deleteHomework(@PathVariable Long id){
        homeworkRepository.detachRepliesFromHomework(id);
        homeworkRepository.deleteById(id);
        return "redirect: /homework/crud/showall";
    }
    @GetMapping("/remove/{id}")
    public String removeHomework(@PathVariable Long id, Model model){
        Homework homework = homeworkRepository.findById(id).orElse(null);
        if (homework == null){
            return "/homework/crud/nohomework";
        }
        model.addAttribute(homework);
        return "/homework/crud/remove";
    }
}
