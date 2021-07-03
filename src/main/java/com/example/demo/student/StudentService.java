package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<Student> getStudent(Integer id){
        return studentRepository.findById(id);
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(Integer id,String firstName,String lastName,String age){
        Student student = studentRepository.findById(id).orElseThrow(()->new IllegalStateException("student not found"));
        if (firstName!=null){
            student.setFirstName(firstName);
        }
        if (lastName!=null) {
            student.setLastName(lastName);
        }
        if (age!=null){
            int newAge = Integer.parseInt(age);
            student.setAge(newAge);
        }
    }

    public void deleteStudent(Integer id){
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) studentRepository.deleteById(id);
    }
}
