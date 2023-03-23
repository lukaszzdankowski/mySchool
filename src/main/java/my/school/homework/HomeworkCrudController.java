package my.school.homework;

import my.school.exam.Exam;
import my.school.exam.ExamRepository;
import my.school.exam.ExamService;
import my.school.reply.ReplyRepository;
import my.school.user.User;
import my.school.user.UserRepository;
import my.school.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/homework/crud")
public class HomeworkCrudController {
    private final HomeworkRepository homeworkRepository;
    private final ExamRepository examRepository;
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final ExamService examService;
    private final UserService userService;
    private final HomeworkService homeworkService;

    public HomeworkCrudController(HomeworkRepository homeworkRepository, ExamRepository examRepository, ReplyRepository replyRepository, UserRepository userRepository, ExamService examService, UserService userService, HomeworkService homeworkService) {
        this.homeworkRepository = homeworkRepository;
        this.examRepository = examRepository;
        this.replyRepository = replyRepository;
        this.userRepository = userRepository;
        this.examService = examService;
        this.userService = userService;
        this.homeworkService = homeworkService;
    }

    @ModelAttribute("examlist")//TODO tylko tam gdzie używam
    public List<Exam> examList() {
        return examService.getAllExams();
    }

    @ModelAttribute("studentlist")//TODO tylko tam gdzie używam
    public List<User> studentList() {
        return userService.getAllStudents();
    }

    @GetMapping("/showall")
    public String showAllHomeworks(Model model) {
        List<Homework> homeworks = homeworkService.getAllHomeworks();
        model.addAttribute("homeworks", homeworks);
        return "/homework/crud/showall";
    }

    @PostMapping("/save")
    public String saveHomework(@RequestParam long[] studentsId,
                               @RequestParam long examId) {
        homeworkService.saveHomework(studentsId, examId);
        return "redirect: /homework/crud/showall";
    }

    @GetMapping("/add")
    public String addHomework() {
        return "/homework/crud/add";
    }

    @GetMapping("/show/{id}")
    public String showHomework(@PathVariable Long id, Model model) {
        Homework homework = homeworkService.getHomeworkWithReplies(id);
        if (homework == null) {
            return "/homework/crud/nohomework";
        }
        model.addAttribute(homework);
        return "/homework/crud/showone";
    }

    @GetMapping("/delete/{id}")
    public String deleteHomework(@PathVariable Long id) {
        Homework homework = homeworkService.getHomeworkWithReplies(id);
        if (homework == null) {
            return "/homework/crud/nohomework";
        }
        homeworkService.deleteHomeworkWithReplies(homework);
        return "redirect: /homework/crud/showall";
    }

    @GetMapping("/remove/{id}")
    public String removeHomework(@PathVariable Long id, Model model) {
        Homework homework = homeworkService.getHomework(id);
        if (homework == null) {
            return "/homework/crud/nohomework";
        }
        model.addAttribute(homework);
        return "/homework/crud/remove";
    }
}
