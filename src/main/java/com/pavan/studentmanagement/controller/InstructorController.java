package com.pavan.studentmanagement.controller;

import com.pavan.studentmanagement.dto.CourseAssignDto;
import com.pavan.studentmanagement.entity.Course;
import com.pavan.studentmanagement.service.InstructorImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("instructor")
public class InstructorController {

    private final InstructorImpl instructorService;

    @PostMapping
    public ResponseEntity<?> createInstructor(@RequestBody String name){
        return ResponseEntity.ok(instructorService.createInstructor(name));
    }

    @PostMapping("/assigncourse")
    public ResponseEntity<?> assignCourse(@RequestBody CourseAssignDto courseAssignDto){
        return ResponseEntity.ok(instructorService.assignCourse(courseAssignDto));
    }

    @GetMapping("/courses")
    public ResponseEntity<Set<Course>> getAllCoursesOfInstructor(@RequestParam Long id){
        return ResponseEntity.ok(instructorService.getAllCoursesOfInstructor(id));
    }
}
