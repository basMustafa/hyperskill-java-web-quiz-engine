package engine.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AnswerResponseDTO {

    private final boolean success;
    private final String feedback;
}
