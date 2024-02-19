package io.isott.expertise.domain.common.usecase;


import io.isott.expertise.domain.common.model.UseCase;

public interface UseCaseHandler<R, T extends UseCase> {

    R handle(T useCase);
}
