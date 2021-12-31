package com.example.demo.student;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentService {
    private final StudentRepository studentRepository;
    private final String not_found_error_msg = "student not found";

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudent(Integer id) throws NotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException(this.not_found_error_msg));
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(Integer id, String firstName, String lastName, Integer age) throws NotFoundException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundException(this.not_found_error_msg));
        if (firstName != null) {
            student.setFirstName(firstName);
        }
        if (lastName != null) {
            student.setLastName(lastName);
        }
        if (age != null) {
            student.setAge(age);
        }
        return student;
    }

    public String deleteStudent(Integer id) throws NotFoundException {
        studentRepository.findById(id).orElseThrow(() -> new NotFoundException(this.not_found_error_msg));
        studentRepository.deleteById(id);
        return "student deleted";
    }
}
