package io.isott.expertise.domain.expertise;

import io.isott.expertise.domain.common.DomainComponent;
import io.isott.expertise.domain.common.usecase.ObservableUseCasePublisher;
import io.isott.expertise.domain.common.usecase.UseCaseHandler;
import io.isott.expertise.domain.expertise.model.Expertise;
import io.isott.expertise.domain.expertise.port.ExpertisePort;
import io.isott.expertise.domain.expertise.usecase.RetrieveLatestExpertise;

@DomainComponent
public class RetrieveLatestExpertiseUseCaseHandler extends ObservableUseCasePublisher
        implements UseCaseHandler<Expertise, RetrieveLatestExpertise> {

    private final ExpertisePort expertisePort;

    public RetrieveLatestExpertiseUseCaseHandler(ExpertisePort expertisePort) {
        this.expertisePort = expertisePort;
        register(RetrieveLatestExpertise.class, this);
    }

    @Override
    public Expertise handle(RetrieveLatestExpertise useCase) {
        return expertisePort.retrieveLatestExpertiseByCarId(useCase.getCarId());
    }
}