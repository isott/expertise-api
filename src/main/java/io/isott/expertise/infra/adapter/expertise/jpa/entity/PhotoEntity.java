package io.isott.expertise.infra.adapter.expertise.jpa.entity;

import io.isott.expertise.domain.expertise.model.Photo;
import io.isott.expertise.infra.common.jpa.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Table(name = "photo")
@Entity(name = "photo")
@SQLRestriction("status <> -1")
public class PhotoEntity extends AbstractEntity {

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private AnswerEntity answer;

    public Photo toModel() {
        return Photo.builder()
                .imageUrl(imageUrl)
                .build();
    }
}
