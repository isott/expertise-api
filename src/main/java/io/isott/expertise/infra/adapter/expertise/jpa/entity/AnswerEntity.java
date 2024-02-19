package io.isott.expertise.infra.adapter.expertise.jpa.entity;

import io.isott.expertise.domain.expertise.model.Answer;
import io.isott.expertise.domain.expertise.model.Answer.IssueStatus;
import io.isott.expertise.infra.common.jpa.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Getter
@Setter
@Table(name = "answer")
@Entity(name = "answer")
@SQLRestriction("status <> -1")
public class AnswerEntity extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "has_issue")
    private IssueStatus hasIssue;

    private String description;

    @OneToOne
    @JoinColumn(name = "question_id", unique = true)
    private QuestionEntity question;

    @ManyToOne
    @JoinColumn(name = "expertise_id")
    private ExpertiseEntity expertise;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "answer_id")
    private List<PhotoEntity> photos;

    public Answer toModel() {
        return Answer.builder()
                .hasIssue(hasIssue)
                .description(description)
                .questionId(question.getId())
                .photoUrls(photos.stream()
                        .map(PhotoEntity::getImageUrl)
                        .toList()
                ).build();
    }
}
