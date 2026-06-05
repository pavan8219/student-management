package com.pavan.studentmanagement.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseAssignDto {

    private Long instructorId;
    private Long courseId;
}
