package com.StudentData.StudentData.service;

import com.StudentData.StudentData.dto.StudentDto;
import com.StudentData.StudentData.model.Student;

import java.util.List;

public interface StudentService {
    List<StudentDto> findAllStudents();
    Student saveStudent(StudentDto studentDto);

    StudentDto findStudentById(long studentId);

    void updateStudent(StudentDto student);

    void delete(Long studentId);
    List<StudentDto> searchStudents(String query);
}
