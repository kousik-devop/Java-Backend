package com.example.SpringBootCRUD.controller;

import com.example.SpringBootCRUD.dto.CreateStudentRequestDto;
import com.example.SpringBootCRUD.dto.CreateStudentResponseDto;
import com.example.SpringBootCRUD.entity.Student;
import com.example.SpringBootCRUD.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    // Constructor Injection
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<CreateStudentResponseDto> createStudent(
            @Valid @RequestBody CreateStudentRequestDto studentRequestDto) {

        CreateStudentResponseDto response = studentService.createStudent(studentRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {

        List<Student> response = studentService.getStudents();

        return ResponseEntity.ok(response);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDto> getStudentById(
            @PathVariable Long id) {

        CreateStudentResponseDto response = studentService.getStudentById(id);


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        Student response = studentService.updateStudent(id, student);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    // HARD DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long id) {

        boolean response = studentService.deleteStudent(id);

        if (!response) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // SOFT DELETE
    @PatchMapping("/{id}/soft-delete")
    public ResponseEntity<Void> softDelete(
            @PathVariable Long id) {

        boolean response = studentService.softDelete(id);

        if (!response) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}