package my.school.user;

import my.school.homework.Homework;
import my.school.homework.HomeworkRepository;
import org.mindrot.jbcrypt.BCrypt;
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
    private final UserRepository userRepository;
    private final HomeworkRepository homeworkRepository;

    public UserCrudController(UserRepository userRepository, HomeworkRepository homeworkRepository) {
        this.userRepository = userRepository;
        this.homeworkRepository = homeworkRepository;
    }

    @ModelAttribute("roles")
    public Set<String> roles() {
        return Stream.of("teacher", "student").collect(Collectors.toCollection(HashSet::new));
    }

    @GetMapping("/showall")
    public String showAllUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "/user/crud/showall";
    }

    @PostMapping("/save")
    public String saveUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/user/crud/edit";
        }
        List<User> listOfUsersWithThisEmail = userRepository.findByEmail(user.getEmail());
        if (listOfUsersWithThisEmail.size() != 0) {
            return "/user/crud/emailduplicate";
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
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
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
        return "redirect: /user/crud/showall";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return "/user/crud/nouser";
        }
        model.addAttribute(user);
        return "/user/crud/edit";
    }

    @GetMapping("/show/{id}")
    public String showUser(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return "/user/crud/nouser";
        }
        model.addAttribute(user);
        if ("student".equals(user.getRole())) {
            List<Homework> homeworksInUse = homeworkRepository.getAllHomeworksForStudentId(user.getId());
            model.addAttribute("homeworksInUse",homeworksInUse);
        }
        return "/user/crud/showone";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return "/user/crud/nouser";
        }
        if ("student".equals(user.getRole())) {
            for (Homework homework : homeworkRepository.getAllHomeworksForStudentId(id)) {
                homeworkRepository.detachRepliesFromHomework(homework.getId());
                homeworkRepository.delete(homework);
            }
        }
        userRepository.deleteById(id);
        return "redirect: /user/crud/showall";
    }

    @GetMapping("/remove/{id}")
    public String removeUser(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return "/user/crud/nouser";
        }
        model.addAttribute(user);
        if ("student".equals(user.getRole())) {
            List<Homework> homeworksInUse = homeworkRepository.getAllHomeworksForStudentId(user.getId());
            model.addAttribute("homeworksInUse",homeworksInUse);
        }
        return "/user/crud/remove";
    }
}
