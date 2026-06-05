package com.pavan.studentmanagement.controller;


import com.pavan.studentmanagement.dto.EnrollmentDto;
import com.pavan.studentmanagement.service.EnrollmentImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("enrollment")
public class EnrollmentController {
    private final EnrollmentImpl enrollmentService;

    @PostMapping
    public ResponseEntity<String> enrollStudentToaCourse(@RequestBody EnrollmentDto enrollmentDto){
        return ResponseEntity.ok(enrollmentService.enrollStudentToaCourse(enrollmentDto));
    }
}
