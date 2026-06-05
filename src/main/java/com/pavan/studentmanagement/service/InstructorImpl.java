package com.pavan.studentmanagement.service;

import com.pavan.studentmanagement.dto.CourseAssignDto;
import com.pavan.studentmanagement.entity.Course;
import com.pavan.studentmanagement.entity.Instructor;
import com.pavan.studentmanagement.repository.CourseRepo;
import com.pavan.studentmanagement.repository.InstructorRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class InstructorImpl {

    private final InstructorRepo instructorRepo;
    private final CourseRepo courseRepo;

    private static final Logger logger =
            LoggerFactory.getLogger(InstructorImpl.class);
    public String createInstructor(String name) {
        Optional<Instructor> instructor=instructorRepo.findByInstructorName(name);
        if(instructor.isPresent())
            return "instructor already present";

        Instructor instructor1=Instructor.builder()
                .instructorName(name)
                .build();
        instructorRepo.save(instructor1);

        return "Instructor created successfully";
    }

    @Transactional
    public String assignCourse(CourseAssignDto courseAssignDto) {
        logger.info("request body {}",courseAssignDto);
        Optional<Course> course=courseRepo.findById(courseAssignDto.getCourseId());
        Optional<Instructor> instructor=instructorRepo.findById(courseAssignDto.getInstructorId());
        if(course.isEmpty() && instructor.isEmpty())
            return "course and instructor does not exist";

        if(instructor.isEmpty()){
            return "instructor does not exist";
        }
        if(course.isEmpty())
            return "course does not exist";

        course.get().getCourseInstructors().add(instructor.get());
        instructor.get().getCourses().add(course.get());
        return "Course assigned successfully";
    }

    public Set<Course> getAllCoursesOfInstructor(Long instructorId) {
      Instructor instructor=instructorRepo.findById(instructorId).orElseThrow();

      return instructor.getCourses();
    }
}
