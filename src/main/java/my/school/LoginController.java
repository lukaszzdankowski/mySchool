package my.school;

import my.school.user.User;
import my.school.user.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "/login";
    }

    @PostMapping("/login")
    public String authorizing(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (!userService.checkIfEmailUsed(email)) {
            return "/nouser";
        }
        User user = userService.getUserByEmail(email);
        if (!BCrypt.checkpw(password, user.getPassword())) {
            return "/wrongpassword";
        }
        HttpSession session = request.getSession();
        session.setAttribute("loggedUser", user);
        if ("teacher".equals(user.getRole())) {
            return "redirect: /teacher/home";
        } else if ("student".equals(user.getRole())) {
            return "redirect: /student/home";
        } else {
            return "/error";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect: /login";
    }
}
