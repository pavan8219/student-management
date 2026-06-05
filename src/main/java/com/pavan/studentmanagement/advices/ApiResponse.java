package com.pavan.studentmanagement.advices;

import java.time.LocalDateTime;

public class ApiResponse <T>{
    public T data;
    public ApiError apiError;
    public LocalDateTime timeStamp;

    public ApiResponse(){
        this.timeStamp=LocalDateTime.now();
    }
    public ApiResponse(T data){
        this();
        this.data=data;
    }
    public ApiResponse(ApiError error){
        this();
        this.apiError=error;
    }
}
