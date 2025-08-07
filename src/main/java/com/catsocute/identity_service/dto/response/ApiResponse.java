package com.catsocute.identity_service.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) // ignore field has null value
public class ApiResponse <T> {
    private int code = 1000;
    private String message;
    private T result;
}
