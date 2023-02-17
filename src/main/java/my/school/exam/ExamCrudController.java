package my.school.exam;

import my.school.task.Task;
import my.school.task.TaskRepository;
import my.school.testing.ConsoleColors;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if (examRepository.getExamByIdWithTasks(id) != null){
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
        examRepository.save(exam);
        return "redirect: /exam/crud/showall";
    }

    @GetMapping("/edit/{id}")
    public String editExam(@PathVariable Long id, Model model) {
//        Exam exam = examRepository.findById(id).orElse(null);
//        if (exam == null) {
//            return "/exam/crud/noexam";
//        }
        Exam exam = examRepository.getExamByIdWithTasks(id);
        model.addAttribute(exam);
        return "/exam/crud/edit";
    }

}
