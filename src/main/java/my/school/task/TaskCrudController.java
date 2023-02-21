package my.school.task;

import my.school.exam.Exam;
import my.school.homework.Homework;
import my.school.homework.HomeworkRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/task/crud")
public class TaskCrudController {
    private final TaskRepository taskRepository;
    private final HomeworkRepository homeworkRepository;

    public TaskCrudController(TaskRepository taskRepository, HomeworkRepository homeworkRepository) {
        this.taskRepository = taskRepository;
        this.homeworkRepository = homeworkRepository;
    }

    @GetMapping("/showall")
    public String showAllTasks(Model model){
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks",tasks);
        return "task/crud/showall";
    }

    @PostMapping("/save")
    public String saveTask(@Valid Task task, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/task/crud/edit";
        }
        taskRepository.save(task);
        return "redirect: /task/crud/showall";
    }
    @GetMapping("/add")
    public String addTask(Model model) {
        Task task = new Task();
        model.addAttribute(task);
        return "/task/crud/edit";
    }
    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return "/task/crud/notask";
        }
        model.addAttribute(task);
        List<Homework> homeworksInUse = new ArrayList<>();
        for (Exam exam : task.getExams()) {
            homeworksInUse.addAll(homeworkRepository.getAllHomeworksForExamId(exam.getId()));
        }
        if (homeworksInUse.size() != 0){
            model.addAttribute("homeworksInUse",homeworksInUse);
            return "/task/crud/cannotedit";
        }
        return "/task/crud/edit";
    }
    @GetMapping("/show/{id}")
    public String showTask(@PathVariable Long id, Model model) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return "/task/crud/notask";
        }
        model.addAttribute(task);
        List<Homework> homeworksInUse = new ArrayList<>();
        for (Exam exam : task.getExams()) {
           homeworksInUse.addAll(homeworkRepository.getAllHomeworksForExamId(exam.getId()));
        }
        model.addAttribute("homeworksInUse",homeworksInUse);
        return "/task/crud/showone";
    }
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return "/task/crud/notask";
        }
        for (Exam exam : task.getExams()) {
            for (Homework homework : homeworkRepository.getAllHomeworksForExamId(exam.getId())) {
                homeworkRepository.detachRepliesFromHomework(homework.getId());
                homeworkRepository.delete(homework);
            }
        }
        taskRepository.detachExamsFromTask(id);
        taskRepository.deleteById(id);
        return "redirect: /task/crud/showall";
    }
    @GetMapping("/remove/{id}")
    public String removeTask(@PathVariable Long id, Model model) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return "/task/crud/notask";
        }
        model.addAttribute(task);
        List<Homework> homeworksInUse = new ArrayList<>();
        for (Exam exam : task.getExams()) {
            homeworksInUse.addAll(homeworkRepository.getAllHomeworksForExamId(exam.getId()));
        }
        model.addAttribute("homeworksInUse",homeworksInUse);
        return "/task/crud/remove";
    }
}
