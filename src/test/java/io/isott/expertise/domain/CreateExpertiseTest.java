package io.isott.expertise.domain;

import io.isott.expertise.domain.adapter.ExpertiseFakeAdapter;
import io.isott.expertise.domain.common.exception.BusinessException;
import io.isott.expertise.domain.expertise.CreateExpertiseUseCaseHandler;
import io.isott.expertise.domain.expertise.model.Answer;
import io.isott.expertise.domain.expertise.model.Expertise;
import io.isott.expertise.domain.expertise.usecase.CreateExpertise;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class CreateExpertiseTest {

    @Test
    void should_create_expertise() {
        // given
        var carId = "001";
        var answers = List.of(Answer.builder().build());

        var createExpertiseUseCase = CreateExpertise.builder()
                .carId(carId)
                .answers(answers)
                .build();

        var expectedExpertise = Expertise.builder()
                .carId(carId)
                .answers(answers)
                .build();

        var createExpertiseUseCaseHandler =
                new CreateExpertiseUseCaseHandler(new ExpertiseFakeAdapter(expectedExpertise));

        // when
        var expertise = createExpertiseUseCaseHandler.handle(createExpertiseUseCase);

        // then
        assertThat(expertise).isNotNull()
                .returns(carId, Expertise::getCarId)
                .returns(answers, Expertise::getAnswers);
    }

    @Test
    void should_throw_business_exception_when_there_is_yes_answer_and_no_photo() {
        // given
        var carId = "001";
        var answers = List.of(Answer.builder()
                .hasIssue(Answer.IssueStatus.YES)
                .photoUrls(List.of())
                .build());

        var createExpertise = CreateExpertise.builder()
                .carId(carId)
                .answers(answers)
                .build();

        var expectedExpertise = Expertise.builder()
                .carId(carId)
                .answers(answers)
                .build();

        var createExpertiseUseCaseHandler =
                new CreateExpertiseUseCaseHandler(new ExpertiseFakeAdapter(expectedExpertise));

        // when
        assertThatExceptionOfType(BusinessException.class)
                .isThrownBy(() -> createExpertiseUseCaseHandler.handle(createExpertise))
                .withMessage("expertise.error.photosUploadFailed");
    }

    @Test
    void should_throw_business_exception_when_there_is_yes_answer_and_no_description() {
        // given
        var carId = "001";
        var photosUrls = List.of("example.com/photos/1");
        var answers = List.of(Answer.builder()
                .hasIssue(Answer.IssueStatus.YES)
                .photoUrls(photosUrls)
                .description(null)
                .build());

        var createExpertise = CreateExpertise.builder()
                .carId(carId)
                .answers(answers)
                .build();

        var expectedExpertise = Expertise.builder()
                .carId(carId)
                .answers(answers)
                .build();

        var createExpertiseUseCaseHandler =
                new CreateExpertiseUseCaseHandler(new ExpertiseFakeAdapter(expectedExpertise));

        // when
        assertThatExceptionOfType(BusinessException.class)
                .isThrownBy(() -> createExpertiseUseCaseHandler.handle(createExpertise))
                .withMessage("expertise.error.missingDescription");
    }
}
