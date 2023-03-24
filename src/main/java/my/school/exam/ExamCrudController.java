package my.school.exam;

import my.school.homework.Homework;
import my.school.homework.HomeworkService;
import my.school.task.Task;
import my.school.task.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/exam/crud")
public class ExamCrudController {
    private final TaskService taskService;
    private final ExamService examService;
    private final HomeworkService homeworkService;

    public ExamCrudController(TaskService taskService, ExamService examService, HomeworkService homeworkService) {
        this.taskService = taskService;
        this.examService = examService;
        this.homeworkService = homeworkService;
    }

    @ModelAttribute("tasklist")
    public List<Task> taskList() {
        return taskService.getAllTasks();
    }

    @GetMapping("/showall")
    public String showAllExams(Model model) {
        List<Exam> exams = examService.getAllExams();
        model.addAttribute("exams", exams);
        return "exam/crud/showall";
    }

    @GetMapping("/show/{id}")
    public String showExam(@PathVariable Long id, Model model) {
        Exam exam = examService.findExamById(id);
        if (exam == null) {
            return "/exam/crud/noexam";
        }
        model.addAttribute(exam);
        List<Homework> homeworksInUse = homeworkService.getAllHomeworksForExamId(id);
        model.addAttribute("homeworksInUse", homeworksInUse);
        return "/exam/crud/showone";
    }

    @PostMapping("/save")
    public String saveExam(@Valid Exam exam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/exam/crud/edit";
        }
        examService.saveExamOrCreateNew(exam);
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
        Exam exam = examService.findExamById(id);
        if (exam == null) {
            return "/exam/crud/noexam";
        }
        model.addAttribute(exam);
        List<Homework> homeworksInUse = homeworkService.getAllHomeworksForExamId(id);
        model.addAttribute("homeworksInUse", homeworksInUse);
        return "/exam/crud/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteExam(@PathVariable Long id) {
        Exam exam = examService.findExamById(id);
        if (exam == null) {
            return "/exam/crud/noexam";
        }
        examService.deleteExamWithHomeworks(exam);
        return "redirect: /exam/crud/showall";
    }

    @GetMapping("/remove/{id}")
    public String removeExam(@PathVariable Long id, Model model) {
        Exam exam = examService.findExamById(id);
        if (exam == null) {
            return "/exam/crud/noexam";
        }
        model.addAttribute(exam);
        List<Homework> homeworksInUse = homeworkService.getAllHomeworksForExamId(id);
        model.addAttribute("homeworksInUse", homeworksInUse);
        return "/exam/crud/remove";
    }
}
