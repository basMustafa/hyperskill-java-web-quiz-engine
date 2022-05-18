package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class CompletedQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long entityId;
    @NonNull
    private Long id;
    @NonNull
    private LocalDateTime completedAt;
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    @NonNull
    private User user;


}
