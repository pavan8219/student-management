package com.pavan.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "enrollment")
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Builder
public class Enrollment {

    @Column(name = "enrollment_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student studentEnrolled; //owning side

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;    //owning side

}
