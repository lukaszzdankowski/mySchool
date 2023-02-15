package my.school.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query("select e from Exam e join fetch e.tasks where e.id = :id")
    Exam getByIdWithTasks(@Param("id") Long id);
}
