package com.example.SpringBootCRUD.repository;

import com.example.SpringBootCRUD.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByDeletedFalse();

    boolean existsByEmail(String emailId);
}
