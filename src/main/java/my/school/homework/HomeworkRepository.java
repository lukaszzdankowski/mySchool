package my.school.homework;

import my.school.exam.Exam;
import my.school.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface HomeworkRepository extends JpaRepository<Homework, Long> {
    List<Homework> findByUser(User user);
    @Query("select h from Homework h join fetch h.replies where h.id = :id")
    Homework getHomeworkWithReplies(@Param("id") Long id);
    @Modifying
    @Query(value = "delete from replies where homework_id = ?1",nativeQuery = true)
    void detachRepliesFromHomework(Long id);

    @Query(value = "select * from homeworks where exam_id = ?1",nativeQuery = true)
    List<Homework> getAllHomeworksForExamId(Long id);

    @Query(value = "select * from homeworks where user_id=?1",nativeQuery = true)
    List<Homework> getAllHomeworksForStudentId(Long id);
}
