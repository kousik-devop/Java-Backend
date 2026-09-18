package org.example.repository;

import org.example.model.Student;

import java.sql.*;

public class StudentRepository {

    public void CreateStudent(Student student) {

        String sql = """
                INSERT INTO student (name, email, age)
                VALUES (?, ?, ?)
                """;

        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/jdbc_demo_db",
                        "root",
                        "root"
                );

                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setInt(3, student.getAge());

            try {

                int rowAffected = statement.executeUpdate();

                if (rowAffected == 1) {
                    System.out.println("Student Created");
                } else {
                    System.out.println("Student not created");
                }

            } catch (SQLIntegrityConstraintViolationException e){
                System.out.println("Student is already present : " + e.getMessage());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStudent(Student student, Long id){
        String sql = """
                UPDATE student
                SET name = ?, email = ?, age = ?
                WHERE id = ?;
                """;

        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/jdbc_demo_db",
                        "root",
                        "root"
                );

                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setInt(3, student.getAge());
            statement.setLong(4, id);

            int rowAffected = statement.executeUpdate();

            if (rowAffected == 1) {
                System.out.println("Student update");
            } else {
                System.out.println("Student not update");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getStudentById(Long id) {

        String sql = """
            SELECT id, name, email, age
            FROM student
            WHERE id = ?
            """;

        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/jdbc_demo_db",
                        "root",
                        "root"
                );

                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Student student = mapToStudent(resultSet);

                    System.out.println(student.toString());

                } else {

                    System.out.println("Student not found with id: " + id);
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private Student mapToStudent(ResultSet resultSet) throws SQLException {

        Student student = new Student();

        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        student.setAge(resultSet.getInt("age"));

        return student;
    }

    public void deleteStudent(Long id){
        String sql = """
                    DELETE FROM student
                    WHERE id = ?;
                     """;

        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/jdbc_demo_db",
                        "root",
                        "root"
                );
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setLong(1, id);

            int rowAffected = statement.executeUpdate();

            if (rowAffected == 1){
                System.out.println("Student Deleted");
            } else {
                System.out.println("Not Deleted");
            }

        } catch(SQLException e){
            System.out.println("Connection Failed");
        }
    }
}
