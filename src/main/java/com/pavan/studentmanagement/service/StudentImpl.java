package com.pavan.studentmanagement.service;

import com.pavan.studentmanagement.dto.StudentAccountDto;
import com.pavan.studentmanagement.dto.StudentDto;
import com.pavan.studentmanagement.entity.Student;
import com.pavan.studentmanagement.entity.StudentAccount;
import com.pavan.studentmanagement.exceptions.BadRequestException;
import com.pavan.studentmanagement.exceptions.ResourceNotFoundException;
import com.pavan.studentmanagement.repository.StudentAccountRepo;
import com.pavan.studentmanagement.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentImpl {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final StudentAccountRepo studentAccountRepo;
    private final PasswordEncoder passwordEncoder;

    public StudentDto addStudent(StudentDto studentDto){
        Student student1=modelMapper.map(studentDto,Student.class);
        Student saved=studentRepository.save(student1);
        return modelMapper.map(saved,StudentDto.class);
    }


    public String createStudentAccount(StudentAccountDto studentAccountDto) {
        Long studentId=studentAccountDto.getStudent_id();
        if(studentId==null){
            throw new BadRequestException("student id is missing");
        }
        Optional<Student> student=studentRepository.findById(studentId);
        if(student.isEmpty()){
            throw new ResourceNotFoundException("student does not exist with id : "+studentId);
        }
        Optional<StudentAccount> isStudentExist=studentAccountRepo.findById(studentAccountDto.getStudent_id());
        if(isStudentExist.isPresent())
            throw new RuntimeException("Student with Id : " + studentAccountDto.getStudent_id() + "already exist");
        StudentAccount studentAccount=modelMapper.map(studentAccountDto,StudentAccount.class);
        String encryptedPassword=passwordEncoder.encode(studentAccountDto.getPassword());
        studentAccount.setPassword(encryptedPassword);
        studentAccount.setStudent(student.get());
        studentAccountRepo.save(studentAccount);
        return "Account created successfully for student Id :" + studentAccountDto.getStudent_id();
    }

    public Student fetchStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student not found with id : "+id));
    }

    public List<Student> fetchAllStudents() {
        return studentRepository.findAllStudents();
    }

    public boolean isStudentExist(Long id){
        Optional<Student> student=studentRepository.findById(id);
        return student.isPresent();
    }

    public String deleteStudentByID(Long id) {
        if(!isStudentExist(id))
            throw new ResourceNotFoundException("Student with Id : "+ id + " does not exist");

        studentRepository.deleteById(id);
        return "Student with Id : "+ id + " deleted successfully";
    }

    @Transactional
    public Student updateStudentDetails(StudentDto studentDto, Long id) {
        if(!isStudentExist(id)){
            return null;
        }
        Student student=studentRepository.findById(id).orElseThrow();
        modelMapper.map(studentDto,student);
        return student;
    }

    @Transactional
    public Student updateStudentPartially(Map<String, String> updates, Long id) {

        Student student=studentRepository.findById(id).orElseThrow();
        updates.forEach((fieldName,value)->{
            Field field = ReflectionUtils.getRequiredField(Student.class,fieldName);
            field.setAccessible(true);
            ReflectionUtils.setField(field,student,value);
        });
        return student;
    }
}
