package com.example.demo.application.interceptor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {

    private String timestamp;
    private int status;
    private String path;
    private String error;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldErrorResponse> errors;

}
