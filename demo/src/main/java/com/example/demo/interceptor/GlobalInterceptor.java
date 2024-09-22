package com.example.demo.interceptor;

import com.example.demo.exception.InvalidParametersException;
import com.example.demo.interceptor.dto.ApiErrorResponse;
import com.example.demo.interceptor.dto.FieldErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestControllerAdvice
public class GlobalInterceptor {

    private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(value = InvalidParametersException.class)
    public ResponseEntity<ApiErrorResponse> getError(InvalidParametersException ex, HttpServletRequest request) {
        var errorResponse = getApiErrorResponse(BAD_REQUEST, request.getRequestURI(), ex.getMessage());

        List<FieldErrorResponse> errors = new ArrayList<>();
        ex.getErrors().forEach((k, v) -> errors.add(new FieldErrorResponse(k, v.toString())));

        errorResponse.setErrors(errors);

        return ResponseEntity.status(BAD_REQUEST).body(errorResponse);
    }

    private ApiErrorResponse getApiErrorResponse(HttpStatus status, String uri, String error) {
        return new ApiErrorResponse(LocalDateTime.now().toString(), status.value(), uri, error, null);
    }

}
