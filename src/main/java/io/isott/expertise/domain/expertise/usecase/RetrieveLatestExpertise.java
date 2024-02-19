package io.isott.expertise.domain.expertise.usecase;

import io.isott.expertise.domain.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RetrieveLatestExpertise implements UseCase {

    private String carId;

    public static RetrieveLatestExpertise from(String carId) {
        return RetrieveLatestExpertise.builder()
                .carId(carId)
                .build();
    }
}
