package io.isott.expertise.domain.common.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Status {

    ACTIVE(1), PASSIVE(0), DELETED(-1);

    private final Integer value;

    public static Status of(Integer value) {
        return Stream.of(Status.values())
                .filter(status -> status.value.equals(value))
                .findFirst()
                .orElseThrow();
    }
}
