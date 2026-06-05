package com.pavan.studentmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @OneToOne(mappedBy = "student",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private StudentAccount studentAccount; // inverse side

    @OneToMany(mappedBy = "studentEnrolled",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Enrollment> courseEnrollments = new HashSet<>();

}
