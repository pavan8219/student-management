package com.pavan.studentmanagement.service;

import com.pavan.studentmanagement.entity.StudentAccount;
import com.pavan.studentmanagement.repository.StudentAccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentDetailService implements UserDetailsService {

    private final StudentAccountRepo studentAccountRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StudentAccount studentAccount=studentAccountRepo.findByUserName(username);
        return User.builder()
                .username(studentAccount.getUserName())
                .password(studentAccount.getPassword()) // encoded password from DB
                .roles("STUDENT")
                .build();
    }
}
