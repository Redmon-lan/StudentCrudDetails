package com.StudentData.StudentData.Controllers;

import com.StudentData.StudentData.dto.StudentDto;
import com.StudentData.StudentData.model.Student;
import com.StudentData.StudentData.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;

@Controller
public class studentController {
    private StudentService studentService;

    @Autowired
    public studentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/Students")
    public String ListStudents(Model model){
        List<StudentDto>students= studentService.findAllStudents();
        model.addAttribute("students",students);
        return "Student_List";
    }
    @GetMapping("/Students/Search")
    public String searchStudent(@RequestParam (value="query") String query,Model model){
        List<StudentDto> students= studentService.searchStudents(query);
        model.addAttribute("students",students);
        return "Student_List";
    }

    @GetMapping("/Students/AddNew")
    public String NewStudentForm(Model model)
    {
        Student student=new Student();
        model.addAttribute("student",student);
        return "Create-NewStudent";
    }
    @PostMapping("/Students/AddNew")
    public String saveStudent(@Valid @ModelAttribute("student")StudentDto studentDto,BindingResult result,Model model){
       if(result.hasErrors()){
           model.addAttribute("student",studentDto);
           return "Create-NewStudent";
       }
        studentService.saveStudent(studentDto);
        return "redirect:/Students";
    }
    @GetMapping("/Students/{studentId}")
    public String Details(@PathVariable("studentId") Long studentId, Model model){
        StudentDto studentDto=studentService.findStudentById(studentId);
        model.addAttribute("student",studentDto);
        return "Student-Details";
    }
    @GetMapping("/Students/{studentId}/Delete")
    public String DeleteStudent(@PathVariable("studentId")Long studentId){
        studentService.delete(studentId);
        return "redirect:/Students";
    }
    @GetMapping("/Students/{studentId}/Edit")
    public String editStudentForm(@PathVariable("studentId") long studentId,Model model){
        StudentDto student=studentService.findStudentById(studentId);
        model.addAttribute("student",student );
        return"Student-Edit";
    }
    @PostMapping("/Students/{studentId}/Edit")
    public String updateStudent(@PathVariable("studentId")Long studentId, @Valid @ModelAttribute("student") StudentDto student, BindingResult result){
       if(result.hasErrors()){
           return"Student-Edit";
       }
        student.setId(studentId);
        studentService.updateStudent(student);
        return "redirect:/Students";
    }


}
