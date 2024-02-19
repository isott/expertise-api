package io.isott.expertise.infra.common.rest;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Response<T>(T data, ErrorResponse errors) {

    public Response(T data) {
        this(data, null);
    }

    public Response(ErrorResponse errors) {
        this(null, errors);
    }
}
