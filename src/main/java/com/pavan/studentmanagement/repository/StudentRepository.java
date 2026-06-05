package com.pavan.studentmanagement.repository;

import com.pavan.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("""
       SELECT s
       FROM Student s
       """)
    List<Student> findAllStudents();
}
