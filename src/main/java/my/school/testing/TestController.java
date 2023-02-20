package my.school.testing;

import my.school.exam.Exam;
import my.school.task.TaskRepository;
import my.school.user.User;
import my.school.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public TestController(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }
    public void showConsoleColors(){
        System.out.println(ConsoleColors.YELLOW_BACKGROUND + "wyswietlanie ksiazek"+ ConsoleColors.RESET);
    }

    @RequestMapping("/test1")
    @ResponseBody
    public String testAddUser(){
        User user = new User();
        user.setId(0L);
        user.setEmail("z@z.pl");
        user.setName("Mark Stewart");
        user.setRole("teacher");
        userRepository.save(user);
        return user.toString();
    }
    @RequestMapping("/test2")
    @ResponseBody
    public String testFetchExamsForTask(){
//        List<Exam> exams = taskRepository.getTaskByIdWithExams(1L).getExams();
//        return exams.toString();
        return "";
    }
}
