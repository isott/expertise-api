package io.isott.expertise.infra.common.jpa;

import io.isott.expertise.domain.common.model.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Status status) {
        return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(Integer statusCode) {
        return Status.of(statusCode);
    }
}
