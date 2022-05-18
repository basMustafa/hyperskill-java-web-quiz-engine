package engine.service;

import engine.dto.AnswerRequestDTO;
import engine.dto.AnswerResponseDTO;
import engine.model.CompletedQuiz;
import engine.model.Quiz;
import engine.model.User;
import engine.repository.CompletedQuizRepository;
import engine.repository.QuizRepository;
import engine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final CompletedQuizRepository completedQuizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository,
                       UserRepository userRepository,
                       CompletedQuizRepository completedQuizRepository) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
        this.completedQuizRepository = completedQuizRepository;
    }

    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public Quiz findById (Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Page<Quiz> getAllQuizzes(int page){
        return quizRepository.findAll(PageRequest.of(page, 10));
    }

    public void deleteQuizById(Long id, String email) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found!"));

        if (!quiz.getUser().getEmail().equals(email)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "the specified user is not the author of this quiz");
        }

        quizRepository.delete(quiz);
    }

    public AnswerResponseDTO solveQuiz(Long id, AnswerRequestDTO answer, String email) {
        Quiz quiz = findById(id);
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

        if (answer.getAnswer().containsAll(quiz.getAnswer())
                && answer.getAnswer().size() == quiz.getAnswer().size()) {
            completedQuizRepository.save(new CompletedQuiz(quiz.getId(), LocalDateTime.now(), user));
            return new AnswerResponseDTO(true, "Quiz solved!");
        } else {
            return new AnswerResponseDTO(false, "Quiz failed!");
        }
    }

    public Page<CompletedQuiz> getSolvedQuizPage(int page, String email) {
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

        Pageable pageable = PageRequest.of(page, 10);
        return completedQuizRepository.findAllByUserOrderByCompletedAtDesc(user, pageable);
    }
}
