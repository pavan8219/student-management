package com.pavan.studentmanagement.advices;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class ApiError {
    public String message;
    public HttpStatus http_status;
}
