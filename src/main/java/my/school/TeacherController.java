package my.school;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherController {
    @GetMapping("/teacher/home")
    public String goHome() {
        return "/teacher/home";
    }
}
