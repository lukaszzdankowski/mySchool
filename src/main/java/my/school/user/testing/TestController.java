package my.school.user.testing;

import my.school.user.User;
import my.school.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    private final UserRepository userRepository;

    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
