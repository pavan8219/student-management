package com.pavan.studentmanagement.controller;

import com.pavan.studentmanagement.service.CourseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("course")
public class CourseController {

    private final CourseImpl courseService;

    @PostMapping
    public ResponseEntity<String> createCourse(@RequestBody String courseName){
        return ResponseEntity.ok(courseService.createCourse(courseName));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCourse(@RequestBody Long id){
        return ResponseEntity.ok(courseService.deleteCourse(id));
    }
}
