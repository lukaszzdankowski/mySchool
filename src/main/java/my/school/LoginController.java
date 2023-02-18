package my.school;

import my.school.user.User;
import my.school.user.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "/login";
    }

    @PostMapping("/login")
    public String authorizing(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        List<User> listOfUsersWithThisEmail = userRepository.findByEmail(email);
        if (listOfUsersWithThisEmail.size() == 0) {
            return "/nouser";
        }
        User user = listOfUsersWithThisEmail.get(0);
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
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect: /login";
    }
}
