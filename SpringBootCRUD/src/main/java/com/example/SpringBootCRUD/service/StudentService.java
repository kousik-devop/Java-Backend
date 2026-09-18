package com.example.SpringBootCRUD.service;

import com.example.SpringBootCRUD.dto.CreateStudentRequestDto;
import com.example.SpringBootCRUD.dto.CreateStudentResponseDto;
import com.example.SpringBootCRUD.entity.Student;
import com.example.SpringBootCRUD.exception.DuplicateResourceFoundException;
import com.example.SpringBootCRUD.exception.ResourceNotFoundException;
import com.example.SpringBootCRUD.repository.StudentRepository;
import org.aspectj.apache.bcel.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // Constructor Injection
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    // CREATE STUDENT
    public CreateStudentResponseDto createStudent(CreateStudentRequestDto studentRequestDto) {

        Student newStudent = mapToEntity(studentRequestDto);

        if(emailExists(newStudent.getEmail())){
            throw new DuplicateResourceFoundException("Student with this email " + newStudent.getEmail() + " is already exists");
        }
        studentRepository.save(newStudent);

        return mapToDto(newStudent);
    }

    private boolean emailExists(String email) {
        return studentRepository.existsByEmail(email);
    }

    private CreateStudentResponseDto mapToDto(Student student) {
        CreateStudentResponseDto studentResponseDto = new CreateStudentResponseDto();

        studentResponseDto.setId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setRollNumber(student.getRollNumber());
        studentResponseDto.setSubject(student.getSubject());
        studentResponseDto.setDeleted(student.isDeleted());
        studentResponseDto.setMessage("Student created successfully");

        return studentResponseDto;
    }

    // GET ALL ACTIVE STUDENTS
    public List<Student> getStudents() {
        return studentRepository.findByDeletedFalse();
    }

    // GET STUDENT BY ID
    public CreateStudentResponseDto getStudentById(Long id) {

        Student student =  studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with this id " + id + "not found."));

        return mapToDto(student);
    }

    // UPDATE STUDENT
    public Student updateStudent(Long id, Student student) {

        Optional<Student> existingStudent = studentRepository.findById(id);

        // Student doesn't exist
        if (existingStudent.isEmpty()) {
            return null;
        }

        Student studentToUpdate = existingStudent.get();

        // Don't update deleted student
        if (studentToUpdate.isDeleted()) {
            return null;
        }

        studentToUpdate.setName(student.getName());
        studentToUpdate.setEmail(student.getEmail());
        studentToUpdate.setAge(student.getAge());
        studentToUpdate.setRollNumber(student.getRollNumber());
        studentToUpdate.setSubject(student.getSubject());

        return studentRepository.save(studentToUpdate);
    }

    // HARD DELETE
    public boolean deleteStudent(Long id) {

        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }

        return false;
    }

    // SOFT DELETE
    public boolean softDelete(Long id) {

        Optional<Student> existingStudent = studentRepository.findById(id);

        if (existingStudent.isEmpty()) {
            return false;
        }

        Student student = existingStudent.get();

        // Already deleted
        if (student.isDeleted()) {
            return false;
        }

        student.setDeleted(true);

        studentRepository.save(student);

        return true;
    }

    private Student mapToEntity (CreateStudentRequestDto studentRequestDto){
        Student student = new Student();

        student.setName(studentRequestDto.getName());
        student.setEmail(studentRequestDto.getEmail());
        student.setAge(studentRequestDto.getAge());
        student.setRollNumber(studentRequestDto.getRollNumber());
        student.setSubject(studentRequestDto.getSubject());
        student.setDeleted(false);

        return student;
    }


}