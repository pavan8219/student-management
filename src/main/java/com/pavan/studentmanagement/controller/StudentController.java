package com.pavan.studentmanagement.controller;

import com.pavan.studentmanagement.dto.StudentAccountDto;
import com.pavan.studentmanagement.dto.StudentDto;
import com.pavan.studentmanagement.entity.Student;
import com.pavan.studentmanagement.service.StudentImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("student")
public class StudentController {
    private final StudentImpl studentService;

    @PostMapping
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto studentDto){
        return ResponseEntity.ok(studentService.addStudent(studentDto));
    }

    @PostMapping("/createaccount")
    public ResponseEntity<String> createAccount(StudentAccountDto studentAccountDto){
        return ResponseEntity.ok(studentService.createStudentAccount(studentAccountDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.fetchStudentById(id));
    }

    @GetMapping
    public ResponseEntity<List<Student>> fetchAllStudents(){
        return ResponseEntity.ok(studentService.fetchAllStudents());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.deleteStudentByID(id));
    }

    @PutMapping
    public ResponseEntity<Student> updateStudentDetails(@RequestBody StudentDto studentDto,@RequestParam Long id){
        return ResponseEntity.ok(studentService.updateStudentDetails(studentDto,id));
    }

    @PatchMapping()
    public ResponseEntity<Student> updateStudentPartially(@RequestBody Map<String,String> updates, @RequestParam Long id){
        return ResponseEntity.ok(studentService.updateStudentPartially(updates,id));
    }
}
