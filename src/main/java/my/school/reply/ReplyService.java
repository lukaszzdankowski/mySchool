package my.school.reply;

import org.springframework.stereotype.Service;

@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public void saveReply(Reply reply) {
        replyRepository.save(reply);
    }
}
