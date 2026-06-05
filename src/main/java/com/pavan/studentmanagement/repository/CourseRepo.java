package com.pavan.studentmanagement.repository;

import com.pavan.studentmanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course,Long> {
    Optional<Course> findByCourseName(String courseName);
}
