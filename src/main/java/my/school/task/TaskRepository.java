package my.school.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


public interface TaskRepository extends JpaRepository<Task, Long> {
    @Transactional
    @Modifying
    @Query(value = "delete from exam_tasks where task_id = ?1", nativeQuery = true)
    void detachTaskFromExam(Long id);

    @Query(value = "select t.result from Task t where t.id = :id")
    double getResultFromTask(@Param("id") Long id);
}
