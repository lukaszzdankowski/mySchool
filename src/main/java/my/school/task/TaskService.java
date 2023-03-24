package my.school.task;

import my.school.exam.Exam;
import my.school.homework.Homework;
import my.school.homework.HomeworkRepository;
import my.school.homework.HomeworkService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final HomeworkRepository homeworkRepository;
    private final HomeworkService homeworkService;

    public TaskService(TaskRepository taskRepository, HomeworkRepository homeworkRepository, HomeworkService homeworkService) {
        this.taskRepository = taskRepository;
        this.homeworkRepository = homeworkRepository;
        this.homeworkService = homeworkService;
    }

    public Task getTask(Long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public List<Homework> getAllHomeworksForTask(Task task) {
        List<Homework> homeworksInUse = new ArrayList<>();//TODO - stream api
        for (Exam exam : task.getExams()) {
            homeworksInUse.addAll(homeworkRepository.getAllHomeworksForExamId(exam.getId()));
        }
        return homeworksInUse;
    }

    public void deleteTaskWithHomeworksAndFromExam(Task task) {
        for (Exam exam : task.getExams()) {//TODO - stream api
            List<Homework> homeworksInUse = homeworkRepository.getAllHomeworksForExamId(exam.getId());
            for (Homework homework : homeworksInUse) {
                homeworkService.deleteHomeworkWithReplies(homework);
            }
        }
        taskRepository.detachTaskFromExam(task.getId());
        taskRepository.delete(task);
    }
}
