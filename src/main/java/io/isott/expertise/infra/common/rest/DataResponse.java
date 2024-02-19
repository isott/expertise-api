package io.isott.expertise.infra.common.rest;

import java.util.List;

public record DataResponse<T>(List<T> items, Integer page, Integer size, Long totalSize) {

    public DataResponse(List<T> items) {
        this(items, null, null, null);
    }
}
