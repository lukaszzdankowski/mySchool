package my.school;

import my.school.homework.Homework;
import my.school.homework.HomeworkRepository;
import my.school.reply.Reply;
import my.school.reply.ReplyRepository;
import my.school.task.TaskRepository;
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
    private final HomeworkRepository homeworkRepository;
    private final ReplyRepository replyRepository;
    private final TaskRepository taskRepository;

    public StudentController(HomeworkRepository homeworkRepository, ReplyRepository replyRepository, TaskRepository taskRepository) {
        this.homeworkRepository = homeworkRepository;
        this.replyRepository = replyRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/student/home")
    public String goHome(HttpSession session) {
        Object loggedUserObj = session.getAttribute("loggedUser");
        if (loggedUserObj != null) {
            try {
                User loggedUser = (User) loggedUserObj;
                if ("student".equals(loggedUser.getRole())) {
                    List<Homework> studentsHomeworks = homeworkRepository.findByUser(loggedUser);
                    session.setAttribute("studentshomeworks", studentsHomeworks);
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
        Homework homework = homeworkRepository.getHomeworkWithReplies(homeworkId);
        model.addAttribute(homework);
        return "/student/attempt";
    }

    @PostMapping("/student/attempt")
    private String homeworkSave(Homework homework) {
        int counter = 0;
        double sum = 0;
        for (Reply reply : homework.getReplies()) {
            replyRepository.save(reply);
            counter++;
            if (reply.getAnswer() == taskRepository.getResultFromTask(reply.getTask().getId())) {

                sum += 1;
            }
        }
        homework.setScore(sum / counter);
        homeworkRepository.save(homework);
        return "redirect: /student/home";
    }
}
