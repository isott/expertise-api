package io.isott.expertise.infra.adapter.expertise.rest.dto;

import io.isott.expertise.domain.expertise.usecase.CreateExpertise;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpertiseRequest {

    @NotNull
    private String carId;
    private List<QuestionAnswer> questionAnswers;

    public CreateExpertise toUseCase() {
        return CreateExpertise.builder()
                .carId(carId)
                .answers(questionAnswers.stream()
                        .map(QuestionAnswer::toModel)
                        .toList())
                .build();
    }
}
