package my.school.exam;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ExamConverter implements Converter<String, Exam> {
    private final ExamRepository examRepository;

    public ExamConverter(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public Exam convert(String source) {
        return examRepository.findById(Long.parseLong(source)).orElse(null);
    }
}
