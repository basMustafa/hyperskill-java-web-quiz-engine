package engine.dto;

import lombok.*;
import java.util.LinkedList;
import java.util.List;

@Getter
public class AnswerRequestDTO {

    private List<Integer> answer = new LinkedList<>();
}
