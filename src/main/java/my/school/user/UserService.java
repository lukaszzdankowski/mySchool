package my.school.user;

import my.school.homework.HomeworkService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final HomeworkService homeworkService;

    public UserService(UserRepository userRepository, HomeworkService homeworkService) {
        this.userRepository = userRepository;
        this.homeworkService = homeworkService;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllStudents() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRole().equals("student")).collect(Collectors.toList());
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public boolean checkIfEmailUsed(String email) {
        return userRepository.existsByEmail(email);
    }

    public void hashPasswordAndSaveUser(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
    }

    public void deleteUserWithHomeworks(User user) {
        if ("student".equals(user.getRole())) {
            homeworkService.deleteAllHomeworksForUserId(user.getId());
        }
        userRepository.delete(user);
    }
}
