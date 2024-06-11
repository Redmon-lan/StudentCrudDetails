package com.StudentData.StudentData.repository;

import com.StudentData.StudentData.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    //Optional<Student> findById(String url);
    @Query("SELECT s FROM Student s WHERE s.FirstName LIKE CONCAT('%',:query, '%')")
    List<Student> searchStudents(String query);


}
