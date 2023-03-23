package my.school.reply;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReplyConverter implements Converter<String, Reply> {
    private final ReplyRepository replyRepository;

    public ReplyConverter(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public Reply convert(String source) {
        return replyRepository.findById(Long.parseLong(source)).orElse(null);
    }
}
