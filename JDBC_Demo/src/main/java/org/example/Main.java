package org.example;

import org.example.model.Student;
import org.example.repository.StudentRepository;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        StudentRepository studentRepository = new StudentRepository();

//        studentRepository.CreateStudent(new Student("Kousik", "kousik@gmail.com", 21));

//        studentRepository.updateStudent(new Student("Kousik", "kousik@gmail.com", 25), 1L);

//        studentRepository.deleteStudent(1L);

        studentRepository.getStudentById(6L);

    }
}