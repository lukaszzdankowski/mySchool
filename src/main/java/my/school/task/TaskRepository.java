package my.school.task;

import my.school.exam.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select t from Task t join fetch t.exams where t.id = :id")
    Task getTaskByIdWithExams(@Param("id") Long id);
}
