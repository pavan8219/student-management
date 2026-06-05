package com.pavan.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "instructors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Instructor {

    @Column(name = "instructor_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructorId;

    @Column(name = "name")
    private String instructorName;

    @ManyToMany(mappedBy = "courseInstructors")
    private Set<Course> courses = new HashSet<>();

//    @OneToMany(mappedBy = "instructor")
//    private Set<Enrollment> enrollments=new HashSet<>();

}
