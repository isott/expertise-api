package io.isott.expertise.domain.common.usecase;


import io.isott.expertise.domain.common.DomainComponent;
import io.isott.expertise.domain.common.exception.BusinessException;
import io.isott.expertise.domain.common.model.UseCase;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@DomainComponent
public class BeanAwareUseCasePublisher implements UseCasePublisher {

    @Override
    @SuppressWarnings("unchecked")
    public <R, T extends UseCase> R publish(Class<R> returnClass, T useCase) {
        var useCaseHandler = (UseCaseHandler<R, T>) UseCaseHandlerRegistry.INSTANCE.detectUseCaseHandlerFrom(useCase.getClass());
        validateUseCaseHandlerDetection(useCase, useCaseHandler);
        return useCaseHandler.handle(useCase);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R, T extends UseCase> void publish(T useCase) {
        var voidUseCaseHandler = (VoidUseCaseHandler<T>) UseCaseHandlerRegistry.INSTANCE.detectVoidUseCaseHandlerFrom(useCase.getClass());
        if (Objects.isNull(voidUseCaseHandler)) {
            var useCaseHandler = (UseCaseHandler<R, T>) UseCaseHandlerRegistry.INSTANCE.detectUseCaseHandlerFrom(useCase.getClass());
            validateUseCaseHandlerDetection(useCase, useCaseHandler);
            useCaseHandler.handle(useCase);
        } else {
            validateVoidUseCaseHandlerDetection(useCase, voidUseCaseHandler);
            voidUseCaseHandler.handle(useCase);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> R publish(Class<R> returnClass) {
        var useCaseHandler = (NoUseCaseHandler<R>) UseCaseHandlerRegistry.INSTANCE.detectNoUseCaseHandlerFrom(returnClass);
        validateNoParamUseCaseHandlerDetection(useCaseHandler);
        return useCaseHandler.handle();
    }

    private <R, T extends UseCase> void validateUseCaseHandlerDetection(T useCase, UseCaseHandler<R, T> useCaseHandler) {
        if (Objects.isNull(useCaseHandler)) {
            log.error("Use case handler cannot be detected for the use case: {}, handlers: {}",
                    useCase, UseCaseHandlerRegistry.INSTANCE.getRegistryForUseCaseHandlers());
            throw new BusinessException("inventoryapi.useCaseHandler.notDetected");
        }
    }

    private <T extends UseCase> void validateVoidUseCaseHandlerDetection(T useCase, VoidUseCaseHandler<T> useCaseHandler) {
        if (Objects.isNull(useCaseHandler)) {
            log.error("Void use case handler cannot be detected for the use case: {}, handlers: {}",
                    useCase, UseCaseHandlerRegistry.INSTANCE.getRegistryForVoidUseCaseHandlers());
            throw new BusinessException("inventoryapi.useCaseHandler.notDetected");
        }
    }

    private <R> void validateNoParamUseCaseHandlerDetection(NoUseCaseHandler<R> useCaseHandler) {
        if (Objects.isNull(useCaseHandler)) {
            log.error("Void use case handler cannot be detected for the handlers: {}",
                    UseCaseHandlerRegistry.INSTANCE.getRegistryForNoUseCaseHandlers());
            throw new BusinessException("inventoryapi.useCaseHandler.notDetected");
        }
    }
}
