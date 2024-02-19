package io.isott.expertise.infra.adapter.expertise.jpa.entity;

import io.isott.expertise.domain.expertise.model.Question;
import io.isott.expertise.infra.common.jpa.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Table(name = "question")
@Entity(name = "question")
@SQLRestriction("status <> -1")
public class QuestionEntity extends AbstractEntity {

    private String text;

    public Question toModel() {
        return Question.builder()
                .text(text)
                .build();
    }
}
