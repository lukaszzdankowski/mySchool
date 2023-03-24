package my.school.homework;

import my.school.exam.ExamRepository;
import my.school.reply.Reply;
import my.school.reply.ReplyRepository;
import my.school.user.User;
import my.school.user.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HomeworkService {
    private final HomeworkRepository homeworkRepository;
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final ExamRepository examRepository;

    public HomeworkService(HomeworkRepository homeworkRepository, ReplyRepository replyRepository, UserRepository userRepository, ExamRepository examRepository) {
        this.homeworkRepository = homeworkRepository;
        this.replyRepository = replyRepository;
        this.userRepository = userRepository;
        this.examRepository = examRepository;
    }

    public List<Homework> getAllHomeworks() {
        return homeworkRepository.findAll();
    }

    public Homework getHomework(Long homeworkId) {
        return homeworkRepository.findById(homeworkId).orElse(null);
    }

    public Homework getHomeworkWithReplies(Long homeworkId) {
        return homeworkRepository.getHomeworkWithReplies(homeworkId);
    }

    public void saveHomework(long[] studentsId, long examId) {
        for (long studentId : studentsId) {
            Homework homework = new Homework();
            homework.setUser(userRepository.findById(studentId).orElseThrow(RuntimeException::new));
            homework.setExam(examRepository.findById(examId).orElseThrow(RuntimeException::new));
            homeworkRepository.save(homework);
            homework.getExam().getTasks().stream()
                    .forEach(task -> {
                        Reply reply = new Reply();
                        reply.setTask(task);
                        reply.setHomework(homework);
                        replyRepository.save(reply);
                    });
        }
    }

    public List<Homework> getAllHomeworksForExamId(Long id) {
        return homeworkRepository.findAllByExamId(id);
    }

    @Transactional
    public void deleteHomeworkWithReplies(Homework homework) {
        replyRepository.deleteAllByHomeworkId(homework.getId());
        homeworkRepository.delete(homework);
    }

    public List<Homework> getAllHomeworksForUser(User user) {
        return homeworkRepository.findByUser(user);
    }

    @Transactional
    public void deleteAllHomeworksForUserId(Long userId) {
        for (Homework homework : getAllHomeworksForUser(userRepository.findById(userId).orElse(null))) {
            deleteHomeworkWithReplies(homework);
        }

    }
}
