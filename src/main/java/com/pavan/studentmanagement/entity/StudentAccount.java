package com.pavan.studentmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student_account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAccount {

    @Id
    @JsonIgnore
    private Long id;
    @Column(name = "user_name")
    private String userName;
    private String password;

    @OneToOne
    @JoinColumn(name = "student_id")
    @MapsId
    @JsonIgnore
    private Student student;   //owning side
}
