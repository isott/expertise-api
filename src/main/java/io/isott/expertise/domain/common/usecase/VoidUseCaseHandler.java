package io.isott.expertise.domain.common.usecase;

import io.isott.expertise.domain.common.model.UseCase;

public interface VoidUseCaseHandler<T extends UseCase> {

    void handle(T useCase);
}
