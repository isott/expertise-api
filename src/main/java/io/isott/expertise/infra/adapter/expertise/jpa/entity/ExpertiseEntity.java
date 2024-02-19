package io.isott.expertise.infra.adapter.expertise.jpa.entity;

import io.isott.expertise.domain.expertise.model.Expertise;
import io.isott.expertise.infra.common.jpa.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Getter
@Setter
@Table(name = "expertise")
@Entity(name = "expertise")
@SQLRestriction("status <> -1")
public class ExpertiseEntity extends AbstractEntity {

    @Column(name = "car_id")
    private String carId;

    @OneToMany(mappedBy = "expertise", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerEntity> answers;

    public Expertise toModel() {
        var builder = Expertise.builder();

        builder.carId(carId);
        if (this.answers != null) {
            builder.answers(
                    this.answers.stream()
                            .map(AnswerEntity::toModel)
                            .toList()
            );
        }
        builder.createdAt(getCreatedAt());

        return builder.build();
    }
}
