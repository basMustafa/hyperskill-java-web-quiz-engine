package engine.mapper;

import engine.dto.QuizDTO;
import engine.dto.UserDTO;
import engine.model.Quiz;
import engine.model.User;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    public Quiz mapToEntity(QuizDTO dto) {
        return Quiz.builder()
                .title(dto.getTitle())
                .text(dto.getText())
                .options(dto.getOptions())
                .answer(dto.getAnswer())
                .user(dto.getUser())
                .build();
    }

    public QuizDTO mapToDTO(Quiz quiz) {
        return QuizDTO.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .text(quiz.getText())
                .options(quiz.getOptions())
                .answer(quiz.getAnswer())
                .build();
    }

    public User mapToEntity(UserDTO userDTO) {
        return User.builder()
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }
}
