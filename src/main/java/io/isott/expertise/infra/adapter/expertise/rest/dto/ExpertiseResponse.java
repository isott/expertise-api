package io.isott.expertise.infra.adapter.expertise.rest.dto;

import io.isott.expertise.domain.expertise.model.Expertise;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpertiseResponse {

    private String carId;
    private LocalDateTime createdAt;
    private List<QuestionAnswer> questionAnswers;

    public static ExpertiseResponse from(Expertise expertise) {
        return ExpertiseResponse.builder()
                .carId(expertise.getCarId())
                .createdAt(expertise.getCreatedAt())
                .questionAnswers(expertise.getAnswers()
                        .stream()
                        .map(QuestionAnswer::from)
                        .toList())
                .build();
    }
}
