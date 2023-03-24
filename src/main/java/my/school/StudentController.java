package my.school;

import my.school.homework.Homework;
import my.school.homework.HomeworkService;
import my.school.reply.Reply;
import my.school.reply.ReplyService;
import my.school.task.TaskService;
import my.school.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StudentController {
    private final HomeworkService homeworkService;
    private final ReplyService replyService;
    private final TaskService taskService;

    public StudentController(HomeworkService homeworkService, ReplyService replyService, TaskService taskService) {
        this.homeworkService = homeworkService;
        this.replyService = replyService;
        this.taskService = taskService;
    }

    @GetMapping("/student/home")
    public String goHome(HttpSession session, Model model) {
        Object loggedUserObj = session.getAttribute("loggedUser");
        if (loggedUserObj != null) {
            try {
                User loggedUser = (User) loggedUserObj;
                if ("student".equals(loggedUser.getRole())) {
                    List<Homework> homeworksInUse = homeworkService.getAllHomeworksForUser(loggedUser);
                    model.addAttribute("studentshomeworks", homeworksInUse);
                    return "/student/home";
                }
            } catch (Exception e) {
                return "/error";
            }
        }
        return "/error";
    }

    @GetMapping("/student/attempt/{homeworkId}")
    private String homeworkAttempt(@PathVariable long homeworkId,
                                   Model model) {
        Homework homework = homeworkService.getHomeworkWithReplies(homeworkId);
        model.addAttribute(homework);
        return "/student/attempt";
    }

    @PostMapping("/student/attempt")
    private String homeworkSave(Homework homework) {
        int counter = 0;
        double sum = 0;
        for (Reply reply : homework.getReplies()) {
            replyService.saveReply(reply);
            counter++;
            if (reply.getAnswer() == taskService.getResultFromTask(reply.getTask().getId())) {
                sum += 1;
            }
        }
        homework.setScore(sum / counter);
        homeworkService.saveHomework(homework);
        return "redirect: /student/home";
    }
}
