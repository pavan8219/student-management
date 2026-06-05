package com.pavan.studentmanagement.service;

import com.pavan.studentmanagement.dto.EnrollmentDto;
import com.pavan.studentmanagement.entity.Course;
import com.pavan.studentmanagement.entity.Enrollment;
import com.pavan.studentmanagement.entity.Student;
import com.pavan.studentmanagement.repository.CourseRepo;
import com.pavan.studentmanagement.repository.EnrollmentRepo;
import com.pavan.studentmanagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentImpl {
    private final EnrollmentRepo enrollmentRepo;
    private final StudentRepository studentRepository;
    private final CourseRepo courseRepo;

    public String enrollStudentToaCourse(EnrollmentDto enrollmentDto) {
        Optional<Student> student=studentRepository.findById(enrollmentDto.getStudentId());
        Optional<Course> course=courseRepo.findById(enrollmentDto.getCourseId());

        if(student.isEmpty())
            return "student not found with id: "+ enrollmentDto.getStudentId();
        if(course.isEmpty())
            return "course not found with id: "+ enrollmentDto.getCourseId();

        Enrollment enrollment=Enrollment.builder()
                .course(course.get())
                .studentEnrolled(student.get())
                .build();

        enrollmentRepo.save(enrollment);

        return "enrollment successful";

    }
}
