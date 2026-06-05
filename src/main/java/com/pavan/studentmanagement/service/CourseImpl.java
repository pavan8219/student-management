package com.pavan.studentmanagement.service;

import com.pavan.studentmanagement.entity.Course;
import com.pavan.studentmanagement.repository.CourseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseImpl {

    private final CourseRepo courseRepo;
    public String createCourse(String courseName) {
        Optional<Course> course=courseRepo.findByCourseName(courseName);
        if(course.isPresent())
            return "course already present";

        Course course1=Course.builder()
                .courseName(courseName)
                .build();
        courseRepo.save(course1);

        return "course saved successfully";
    }

    public String deleteCourse(Long id) {
        courseRepo.deleteById(id);
        return "course deleted successfuly";
    }
}
