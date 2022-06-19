package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class StudentService {

	private final StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository){
		this.studentRepository = studentRepository;
	}

    public List<Student> getStudents(){
		return this.studentRepository.findAll();
	}

	public void addNewStudent(Student student){
		Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
		if (studentByEmail.isPresent()) {
			throw new ResponseStatusException(HttpStatus.IM_USED, String.format("Email: %s is already used!", student.getEmail()));
		}
		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId){
		boolean exists = studentRepository.existsById(studentId);
		if(!exists){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Student with id %s does not exists", studentId));
		}
		studentRepository.deleteById(studentId);
	}
}
