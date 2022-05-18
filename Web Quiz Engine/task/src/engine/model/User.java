package engine.model;

import lombok.*;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Quiz> quizList;

    @OneToMany(mappedBy = "user")
    private List<CompletedQuiz> solvedQuizzes = new LinkedList<>();
}
