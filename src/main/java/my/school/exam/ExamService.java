package my.school.exam;

import my.school.homework.Homework;
import my.school.homework.HomeworkRepository;
import my.school.homework.HomeworkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    private final ExamRepository examRepository;
    private final HomeworkRepository homeworkRepository;
    private final HomeworkService homeworkService;

    public ExamService(ExamRepository examRepository, HomeworkRepository homeworkRepository, HomeworkService homeworkService) {
        this.examRepository = examRepository;
        this.homeworkRepository = homeworkRepository;
        this.homeworkService = homeworkService;
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Exam findExamById(Long id) {
        return examRepository.findById(id).orElse(null);
    }

    public void saveExamOrCreateNew(Exam exam) {
        long numberOfExamUsages = homeworkRepository.countAllByExamId(exam.getId());
        if (numberOfExamUsages != 0) {
            exam.setId(0L);
            exam.setTitle(exam.getTitle() + "_copy");
        }
        examRepository.save(exam);
    }

    public void deleteExamWithHomeworks(Exam exam) {
        List<Homework> homeworksInUse = homeworkRepository.findAllByExamId(exam.getId());
        for (Homework homework : homeworksInUse) {
            homeworkService.deleteHomeworkWithReplies(homework);
        }
        examRepository.deleteById(exam.getId());
    }
}
