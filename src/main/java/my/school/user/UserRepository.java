package my.school.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findALLByEmail(String email);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
