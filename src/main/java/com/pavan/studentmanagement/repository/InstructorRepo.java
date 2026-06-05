package com.pavan.studentmanagement.repository;

import com.pavan.studentmanagement.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepo extends JpaRepository<Instructor,Long> {
    Optional<Instructor> findByInstructorName(String name);
}
