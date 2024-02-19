package io.isott.expertise.domain.expertise;

import io.isott.expertise.domain.common.DomainComponent;
import io.isott.expertise.domain.common.exception.BusinessException;
import io.isott.expertise.domain.common.usecase.ObservableUseCasePublisher;
import io.isott.expertise.domain.common.usecase.UseCaseHandler;
import io.isott.expertise.domain.expertise.model.Answer;
import io.isott.expertise.domain.expertise.model.Expertise;
import io.isott.expertise.domain.expertise.port.ExpertisePort;
import io.isott.expertise.domain.expertise.usecase.CreateExpertise;

import static io.isott.expertise.domain.expertise.model.Answer.IssueStatus.YES;

@DomainComponent
public class CreateExpertiseUseCaseHandler extends ObservableUseCasePublisher
        implements UseCaseHandler<Expertise, CreateExpertise> {

    private final ExpertisePort expertisePort;

    public CreateExpertiseUseCaseHandler(ExpertisePort expertisePort) {
        this.expertisePort = expertisePort;
        register(CreateExpertise.class, this);
    }

    @Override
    public Expertise handle(CreateExpertise useCase) {
        var answers = useCase.getAnswers();
        answers.stream()
                .filter(answer -> answer.getHasIssue() == YES)
                .forEach(this::validatePhotosAndDescription);

        return expertisePort.createExpertise(useCase);
    }

    private void validatePhotosAndDescription(Answer answer) {
        var photoUrls = answer.getPhotoUrls();
        var description = answer.getDescription();

        if (photoUrls == null || photoUrls.isEmpty() || photoUrls.size() > 3) {
            throw new BusinessException("expertise.error.photosUploadFailed");
        }

        if (description == null || description.isEmpty()) {
            throw new BusinessException("expertise.error.missingDescription");
        }
    }
}
