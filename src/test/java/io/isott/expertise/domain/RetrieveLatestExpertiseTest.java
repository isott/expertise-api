package io.isott.expertise.domain;

import io.isott.expertise.domain.adapter.ExpertiseFakeAdapter;
import io.isott.expertise.domain.expertise.RetrieveLatestExpertiseUseCaseHandler;
import io.isott.expertise.domain.expertise.model.Answer;
import io.isott.expertise.domain.expertise.model.Expertise;
import io.isott.expertise.domain.expertise.model.Question;
import io.isott.expertise.domain.expertise.usecase.RetrieveLatestExpertise;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RetrieveLatestExpertiseTest {

    @Test
    void should_retrieve_latest_expertise() {
        // given
        var carId = "001";
        var question = Question.builder()
                .id(1L)
                .text("Does vehicle can fly?")
                .build();

        var answers = List.of(Answer.builder()
                .questionId(1L)
                .hasIssue(Answer.IssueStatus.NO)
                .description("thank god it doesn't fly")
                .build());

        var retrieveLatestExpertise = RetrieveLatestExpertise.builder()
                .carId(carId)
                .build();

        var expectedExpertise = Expertise.builder()
                .carId(carId)
                .answers(answers)
                .build();

        var retrieveLatestExpertiseUseCaseHandler =
                new RetrieveLatestExpertiseUseCaseHandler(new ExpertiseFakeAdapter(expectedExpertise));
        // when
        var expertise = retrieveLatestExpertiseUseCaseHandler.handle(retrieveLatestExpertise);

        // then
        assertThat(expertise).isNotNull()
                .returns(carId, Expertise::getCarId)
                .returns(answers, Expertise::getAnswers);
    }
}
