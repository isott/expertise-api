package io.isott.expertise.infra.adapter.expertise.rest.dto;

import io.isott.expertise.domain.expertise.model.Answer;
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
public class QuestionAnswer {

    @NotNull
    private Long questionId;

    @NotNull
    private Answer.IssueStatus answer;
    private String description;
    private List<String> photoUrls;

    public static QuestionAnswer from(Answer answer) {
        return QuestionAnswer.builder()
                .questionId(answer.getQuestionId())
                .answer(answer.getHasIssue())
                .description(answer.getDescription())
                .photoUrls(answer.getPhotoUrls())
                .build();
    }

    public Answer toModel() {
        return Answer.builder()
                .hasIssue(answer)
                .description(description)
                .photoUrls(photoUrls)
                .questionId(questionId)
                .build();
    }
}
