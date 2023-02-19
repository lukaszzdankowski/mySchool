package my.school.reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByHomeworkId(Long homeworkId);
}
