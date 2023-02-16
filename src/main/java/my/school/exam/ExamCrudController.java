package my.school.exam;

import my.school.task.Task;
import my.school.task.TaskRepository;
import my.school.testing.ConsoleColors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/exam/crud")
public class ExamCrudController {
    private final ExamRepository examRepository;
    private final TaskRepository taskRepository;

    public ExamCrudController(ExamRepository examRepository, TaskRepository taskRepository) {
        this.examRepository = examRepository;
        this.taskRepository = taskRepository;
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
        if (examRepository.getExamByIdWithTasks(id).getTasks() != null){
            List<Task> tasks = examRepository.getExamByIdWithTasks(id).getTasks();
            model.addAttribute("tasks", tasks);
        }
        return "/exam/crud/showone";
    }
    @GetMapping("/add")
    public String addExam(Model model) {
        Exam exam = new Exam();
        model.addAttribute(exam);
        return "/exam/crud/edit";
    }
    @PostMapping("/save")
    public String saveExam(Exam exam){
        System.out.println(ConsoleColors.BLUE + "exam object below:"+ ConsoleColors.RESET);
        System.out.println(exam);
        examRepository.save(exam);
        return "redirect: /exam/crud/showall";
    }

}
