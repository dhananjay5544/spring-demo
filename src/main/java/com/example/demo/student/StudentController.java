package com.example.demo.student;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/student")
@Slf4j
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "{id}")
    Student getStudent(@PathVariable("id") Integer id) {
        try {
            return studentService.getStudent(id);
        } catch (NotFoundException e) {
            log.error(String.valueOf(e));
        }
        return null;
    }

    @GetMapping()
    List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping()
    Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping(path = "{id}")
    Student updateStudent(@PathVariable("id") Integer id, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) Integer age) {
        try {
            return studentService.updateStudent(id, firstName, lastName, age);
        } catch (NotFoundException e) {
            log.error(String.valueOf(e));
        }
        return null;
    }

    @DeleteMapping(path = "{id}")
    String deleteStudent(@PathVariable("id") Integer id) {
        try {
            return studentService.deleteStudent(id);
        } catch (NotFoundException e) {
            log.error(String.valueOf(e));
            return e.getMessage();
        }
    }
}
