package my.school.homework;

import my.school.reply.ReplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkService {
    private final HomeworkRepository homeworkRepository;
    private final ReplyRepository replyRepository;

    public HomeworkService(HomeworkRepository homeworkRepository, ReplyRepository replyRepository) {
        this.homeworkRepository = homeworkRepository;
        this.replyRepository = replyRepository;
    }

    public List<Homework> getAllHomeworksForExamId(Long id){
        return homeworkRepository.findAllByExamId(id);
    }

    public void deleteHomeworkWithReplies(Homework homework){
        replyRepository.deleteAllByHomeworkId(homework.getId());
        homeworkRepository.delete(homework);
    }
}
