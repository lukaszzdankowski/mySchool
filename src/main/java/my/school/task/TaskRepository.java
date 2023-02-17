package my.school.task;

import my.school.exam.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Modifying
    @Query(value = "delete from exam_tasks where task_id = ?1", nativeQuery = true)
    void detachExamsFromTask(Long id);
}
