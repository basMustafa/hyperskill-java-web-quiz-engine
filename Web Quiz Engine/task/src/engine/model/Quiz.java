package engine.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity(name = "Quiz")
@Table(name = "quiz")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    @ElementCollection
    private List<String> options;
    @ElementCollection
    private List<Integer> answer;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}
