package io.isott.expertise.domain.expertise.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@Builder
public class Expertise {

    private String carId;
    private LocalDateTime createdAt;
    private List<Answer> answers;

    public static Expertise empty() {
        return Expertise.builder()
                .answers(Collections.emptyList())
                .build();
    }
}
