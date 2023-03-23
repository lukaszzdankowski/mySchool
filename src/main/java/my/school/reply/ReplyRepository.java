package my.school.reply;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ReplyRepository extends JpaRepository<Reply, Long> {

    void deleteAllByHomeworkId(Long id);
}
