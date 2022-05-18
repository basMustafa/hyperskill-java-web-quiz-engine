package engine.repository;

import engine.model.CompletedQuiz;
import engine.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedQuizRepository extends JpaRepository<CompletedQuiz, Long> {

    Page<CompletedQuiz> findAllByUserOrderByCompletedAtDesc(User user, Pageable page);
}
