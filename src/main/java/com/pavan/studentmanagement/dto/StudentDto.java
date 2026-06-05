package com.pavan.studentmanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentDto {

    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
}
