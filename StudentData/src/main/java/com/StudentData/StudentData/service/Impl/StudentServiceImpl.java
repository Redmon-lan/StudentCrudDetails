package com.StudentData.StudentData.service.Impl;

import com.StudentData.StudentData.dto.StudentDto;
import com.StudentData.StudentData.model.Student;
import com.StudentData.StudentData.repository.StudentRepository;
import com.StudentData.StudentData.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDto> findAllStudents() {
        List<Student> students=studentRepository.findAll();
        return  students.stream().map(student -> mapToStudentDto(student)).collect(Collectors.toList());
    }

    @Override
    public Student saveStudent(StudentDto studentDto) {
        Student student=mapToStudent(studentDto);
        return studentRepository.save(student);
    }

    @Override
    public StudentDto findStudentById(long studentId) {
        Student student=studentRepository.findById(studentId).get();
        return mapToStudentDto(student);
    }

    @Override
    public void updateStudent(StudentDto studentDto) {
        Student student=mapToStudent(studentDto);
        studentRepository.save(student);
    }

    @Override
    public void delete(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public List<StudentDto> searchStudents(String query) {

        List<Student> students=studentRepository.searchStudents(query);
        return  students.stream().map(student -> mapToStudentDto(student)).collect(Collectors.toList());
    }

    private Student mapToStudent(StudentDto student) {
        Student studentDto=Student.builder()
                .id(student.getId())
                .AdmissionNo(student.getAdmissionNo())
                .FirstName(student.getFirstName())
                .OtherName(student.getOtherName())
                .Gender(student.getGender())
                .Age(student.getAge())
                .Year(student.getYear())
                .DateCreated(student.getDateCreated())
                .UpdatedOn(student.getUpdatedOn())
                .build();
        return studentDto;
    }

    private StudentDto mapToStudentDto(Student student){
        StudentDto studentDto=StudentDto.builder()
                .id(student.getId())
                .AdmissionNo(student.getAdmissionNo())
                .FirstName(student.getFirstName())
                .OtherName(student.getOtherName())
                .Gender(student.getGender())
                .Age(student.getAge())
                .Year(student.getYear())
                .DateCreated(student.getDateCreated())
                .UpdatedOn(student.getUpdatedOn())
                .build();
        return studentDto;
    }
}
