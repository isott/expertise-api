package io.isott.expertise.domain.expertise.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Question {

    private Long id;
    private String text;
}