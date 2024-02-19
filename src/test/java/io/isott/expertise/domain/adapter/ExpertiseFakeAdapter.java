package io.isott.expertise.domain.adapter;

import io.isott.expertise.domain.expertise.model.Expertise;
import io.isott.expertise.domain.expertise.port.ExpertisePort;
import io.isott.expertise.domain.expertise.usecase.CreateExpertise;

public class ExpertiseFakeAdapter implements ExpertisePort {

    private final Expertise expertise;

    public ExpertiseFakeAdapter(Expertise expertise) {
        this.expertise = expertise;
    }

    @Override
    public Expertise retrieveLatestExpertiseByCarId(String carId) {
        return expertise;
    }

    @Override
    public Expertise createExpertise(CreateExpertise createExpertise) {
        return expertise;
    }
}
