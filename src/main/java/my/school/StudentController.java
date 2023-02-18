package my.school;

import my.school.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class StudentController {
    @GetMapping("/student/home")
    public String goHome(HttpSession session) {
        Object loggedUserObj = session.getAttribute("loggedUser");
        if (loggedUserObj != null) {
            try {
                User loggedUser = (User) loggedUserObj;
                if ("student".equals(loggedUser.getRole())) {
                    return "/student/home";
                }
            } catch (Exception e) {
                return "/error";
            }
        }
        return "/error";
    }
}
