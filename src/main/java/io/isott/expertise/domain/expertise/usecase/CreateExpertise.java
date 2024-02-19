package io.isott.expertise.domain.expertise.usecase;

import io.isott.expertise.domain.common.model.UseCase;
import io.isott.expertise.domain.expertise.model.Answer;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateExpertise implements UseCase {

    private String carId;
    private List<Answer> answers;
}
