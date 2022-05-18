package engine.controller;

import engine.dto.AnswerRequestDTO;
import engine.dto.AnswerResponseDTO;
import engine.dto.QuizDTO;
import engine.mapper.ModelMapper;
import engine.model.CompletedQuiz;
import engine.model.Quiz;
import engine.service.QuizService;
import engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public QuizController(QuizService quizService, UserService userService, ModelMapper modelMapper) {
        this.quizService = quizService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    public QuizDTO createQuiz(@Valid @RequestBody QuizDTO quizDTO, @AuthenticationPrincipal UserDetails userDetails) {
        quizDTO.setUser(userService.getUser(userDetails.getUsername()));
        Quiz quiz = quizService.saveQuiz(modelMapper.mapToEntity(quizDTO));
        return modelMapper.mapToDTO(quiz);
    }

    @GetMapping("/{id}")
    public QuizDTO getQuizById(@PathVariable Long id) {
        return modelMapper.mapToDTO(quizService.findById(id));
    }

    @GetMapping()
    public Page<QuizDTO> getAllQuizzes(@RequestParam(defaultValue = "0") int page) {

        List<QuizDTO> quizList = quizService.getAllQuizzes(page).stream()
                .map(modelMapper::mapToDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(quizList);
    }

    @PostMapping("/{id}/solve")
    public AnswerResponseDTO solveQuiz(@PathVariable Long id, @RequestBody AnswerRequestDTO answer,
                                       @AuthenticationPrincipal UserDetails userDetails) {

        return quizService.solveQuiz(id, answer, userDetails.getUsername());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuiz(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        quizService.deleteQuizById(id, userDetails.getUsername());
    }

    @GetMapping("/completed")
    public Page<CompletedQuiz> getCompletedQuizzes(@RequestParam(defaultValue = "0") int page,
                                                   @AuthenticationPrincipal UserDetails userDetails) {

        return quizService.getSolvedQuizPage(page, userDetails.getUsername());
    }
}
