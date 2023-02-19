package my.school.homework;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HomeworkConverter implements Converter<String, Homework> {
    private final HomeworkRepository homeworkRepository;

    public HomeworkConverter(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }
    @Override
    public Homework convert(String source){
        return homeworkRepository.findById(Long.parseLong(source)).orElse(null);
    }
}
