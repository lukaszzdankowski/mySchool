package my.school.exam;

import my.school.homework.Homework;
import my.school.homework.HomeworkRepository;
import my.school.task.Task;
import my.school.task.TaskRepository;
import my.school.testing.ConsoleColors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/exam/crud")
public class ExamCrudController {
    private final ExamRepository examRepository;
    private final TaskRepository taskRepository;
    private final HomeworkRepository homeworkRepository;

    public ExamCrudController(ExamRepository examRepository, TaskRepository taskRepository, HomeworkRepository homeworkRepository) {
        this.examRepository = examRepository;
        this.taskRepository = taskRepository;
        this.homeworkRepository = homeworkRepository;
    }
    @ModelAttribute("tasklist")
    public List<Task> taskList(){
        return taskRepository.findAll();
    }

    @GetMapping("/showall")
    public String showAllExams(Model model) {
        List<Exam> exams = examRepository.findAll();
        model.addAttribute("exams", exams);
        return "exam/crud/showall";
    }

    @GetMapping("/show/{id}")
    public String showExam(@PathVariable Long id, Model model) {
        Exam exam = examRepository.findById(id).orElse(null);
        if (exam == null) {
            return "/exam/crud/noexam";
        }
        model.addAttribute(exam);
        List<Homework> homeworksInUse = homeworkRepository.getAllHomeworksForExamId(id);
        model.addAttribute("homeworksInUse",homeworksInUse);
        return "/exam/crud/showone";
    }
    @PostMapping("/save")
    public String saveExam(@Valid Exam exam, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/exam/crud/edit";
        }
        List<Homework> homeworksInUse = homeworkRepository.getAllHomeworksForExamId(exam.getId());
        if (homeworksInUse.size() != 0){
            exam.setId(0L);
            exam.setTitle(exam.getTitle()+"_copy");
        }
        examRepository.save(exam);
        return "redirect: /exam/crud/showall";
    }
    @GetMapping("/add")
    public String addExam(Model model) {
        Exam exam = new Exam();
        model.addAttribute(exam);
        return "/exam/crud/edit";
    }

    @GetMapping("/edit/{id}")
    public String editExam(@PathVariable Long id, Model model) {
        Exam exam = examRepository.findById(id).orElse(null);
        if (exam == null) {
            return "/exam/crud/noexam";
        }
        model.addAttribute(exam);
        List<Homework> homeworksInUse = homeworkRepository.getAllHomeworksForExamId(id);
        model.addAttribute("homeworksInUse",homeworksInUse);
        return "/exam/crud/edit";
    }
    @GetMapping("/delete/{id}")
    public String deleteExam(@PathVariable Long id) {
        Exam exam = examRepository.findById(id).orElse(null);
        if (exam == null) {
            return "/exam/crud/noexam";
        }
        List<Homework> homeworksInUse = homeworkRepository.getAllHomeworksForExamId(id);
        for (Homework homework : homeworksInUse) {
            homeworkRepository.detachRepliesFromHomework(homework.getId());
            homeworkRepository.deleteById(homework.getId());
        }
        examRepository.deleteById(id);
        return "redirect: /exam/crud/showall";
    }
    @GetMapping("/remove/{id}")
    public String removeExam(@PathVariable Long id, Model model) {
        Exam exam = examRepository.findById(id).orElse(null);
        if (exam == null) {
            return "/exam/crud/noexam";
        }
        model.addAttribute(exam);
        List<Homework> homeworksInUse = homeworkRepository.getAllHomeworksForExamId(id);
        model.addAttribute("homeworksInUse",homeworksInUse);
        return "/exam/crud/remove";
    }
}
