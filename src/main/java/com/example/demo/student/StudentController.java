package com.example.demo.student;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="api/v1/students")
public class StudentController {

    public final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    
    @GetMapping
	public List<Student> getStudents(){
		return studentService.getStudents();
	}

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentID}")
    public void deleteStudent(@PathVariable("studentID") Long studentId){
        studentService.deleteStudent(studentId);
    }
}
