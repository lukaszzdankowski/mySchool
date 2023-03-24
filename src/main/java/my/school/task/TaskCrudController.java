package my.school.task;

import my.school.homework.Homework;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/task/crud")
public class TaskCrudController {
    private final TaskService taskService;

    public TaskCrudController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/showall")
    public String showAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "task/crud/showall";
    }

    @PostMapping("/save")
    public String saveTask(@Valid Task task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/task/crud/edit";
        }
        taskService.saveTask(task);
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
        Task task = taskService.getTask(id);
        if (task == null) {
            return "/task/crud/notask";
        }
        model.addAttribute(task);
        List<Homework> homeworksInUse = taskService.getAllHomeworksForTask(task);
        if (homeworksInUse.size() != 0) {
            model.addAttribute("homeworksInUse", homeworksInUse);
            return "/task/crud/cannotedit";
        }
        return "/task/crud/edit";
    }

    @GetMapping("/show/{id}")
    public String showTask(@PathVariable Long id, Model model) {
        Task task = taskService.getTask(id);
        if (task == null) {
            return "/task/crud/notask";
        }
        model.addAttribute(task);
        List<Homework> homeworksInUse = taskService.getAllHomeworksForTask(task);
        model.addAttribute("homeworksInUse", homeworksInUse);
        return "/task/crud/showone";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        Task task = taskService.getTask(id);
        if (task == null) {
            return "/task/crud/notask";
        }
        taskService.deleteTaskWithHomeworksAndFromExam(task);
        return "redirect: /task/crud/showall";
    }

    @GetMapping("/remove/{id}")
    public String removeTask(@PathVariable Long id, Model model) {
        Task task = taskService.getTask(id);
        if (task == null) {
            return "/task/crud/notask";
        }
        model.addAttribute(task);
        List<Homework> homeworksInUse = taskService.getAllHomeworksForTask(task);
        model.addAttribute("homeworksInUse", homeworksInUse);
        return "/task/crud/remove";
    }
}
