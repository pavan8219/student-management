package com.pavan.studentmanagement.advices;

import com.pavan.studentmanagement.exceptions.BadRequestException;
import com.pavan.studentmanagement.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
       ApiError error=ApiError.builder()
               .http_status(HttpStatus.NOT_FOUND)
               .message(resourceNotFoundException.getLocalizedMessage())
               .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
   }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException badRequestException){
        ApiError error=ApiError.builder()
                .http_status(HttpStatus.BAD_REQUEST)
                .message(badRequestException.getLocalizedMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
