package my.school.user;

import my.school.homework.Homework;
import my.school.homework.HomeworkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/user/crud")
public class UserCrudController {
    private final UserService userService;
    private final HomeworkService homeworkService;

    public UserCrudController(UserService userService, HomeworkService homeworkService) {
        this.userService = userService;
        this.homeworkService = homeworkService;
    }

    @ModelAttribute("roles")// TODO
    public Set<String> roles() {
        return Stream.of("teacher", "student").collect(Collectors.toCollection(HashSet::new));
    }

    @GetMapping("/showall")
    public String showAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "/user/crud/showall";
    }

    @PostMapping("/save")
    public String saveUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/user/crud/edit";
        }
        if (userService.checkIfEmailUsed(user.getEmail())) {
            return "/user/crud/emailduplicate";
        }
        userService.hashPasswordAndSaveUser(user);
        return "redirect: /user/crud/showall";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "/user/crud/add";
    }

    @PostMapping("/update")
    public String updateUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/user/crud/edit";
        }
        userService.hashPasswordAndSaveUser(user);
        return "redirect: /user/crud/showall";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.getUser(id);
        if (user == null) {
            return "/user/crud/nouser";
        }
        model.addAttribute(user);
        return "/user/crud/edit";
    }

    @GetMapping("/show/{id}")
    public String showUser(@PathVariable Long id, Model model) {
        User user = userService.getUser(id);//TODO - routing lepszy
        if (user == null) {
            return "/user/crud/nouser";
        }
        model.addAttribute(user);
        if ("student".equals(user.getRole())) {
            List<Homework> homeworksInUse = homeworkService.getAllHomeworksForUser(user);
            model.addAttribute("homeworksInUse", homeworksInUse);
        }
        return "/user/crud/showone";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        if (user == null) {
            return "/user/crud/nouser";
        }
        userService.deleteUserWithHomeworks(user);
        return "redirect: /user/crud/showall";
    }

    @GetMapping("/remove/{id}")
    public String removeUser(@PathVariable Long id, Model model) {
        User user = userService.getUser(id);
        if (user == null) {
            return "/user/crud/nouser";
        }
        model.addAttribute(user);
        if ("student".equals(user.getRole())) {
            List<Homework> homeworksInUse = homeworkService.getAllHomeworksForUser(user);
            model.addAttribute("homeworksInUse", homeworksInUse);
        }
        return "/user/crud/remove";
    }
}
