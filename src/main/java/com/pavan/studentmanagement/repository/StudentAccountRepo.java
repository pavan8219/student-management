package com.pavan.studentmanagement.repository;

import com.pavan.studentmanagement.entity.StudentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAccountRepo extends JpaRepository<StudentAccount,Long> {
    StudentAccount findByUserName(String userName);
}
