package com.catsocute.identity_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.catsocute.identity_service.dto.response.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception) {
        ApiResponse apiResponse = ApiResponse.builder()
            .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
            .message(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage())
            .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getCode();
        ApiResponse apiResponse = ApiResponse.builder()
            .code(errorCode.getCode())
            .message(errorCode.getMessage())
            .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);
        ApiResponse apiResponse = ApiResponse.builder()
            .code(errorCode.getCode())
            .message(errorCode.getMessage())
            .build();

        return ResponseEntity.badRequest().body(apiResponse);
    }
}
