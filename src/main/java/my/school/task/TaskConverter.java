package my.school.task;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter implements Converter<String, Task> {
    private final TaskRepository taskRepository;

    public TaskConverter(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task convert(String source) {
        return taskRepository.findById(Long.parseLong(source)).orElse(null);
    }
}
