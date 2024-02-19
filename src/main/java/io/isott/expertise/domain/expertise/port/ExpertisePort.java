package io.isott.expertise.domain.expertise.port;

import io.isott.expertise.domain.expertise.model.Expertise;
import io.isott.expertise.domain.expertise.usecase.CreateExpertise;

public interface ExpertisePort {

    Expertise retrieveLatestExpertiseByCarId(String carId);

    Expertise createExpertise(CreateExpertise createExpertise);
}
