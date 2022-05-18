package engine.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import engine.model.User;
import lombok.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String text;
    @NotEmpty
    @Size(min = 2)
    private List<String> options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> answer;
    @JsonIgnore
    private User user;
}
