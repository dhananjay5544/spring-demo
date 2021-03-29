package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "{id}")
    Optional<Student> getStudent(@PathVariable("id") Long id){
        return studentService.getStudent(id);
    }

    @GetMapping()
    List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping()
    Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @PutMapping (path = "{id}")
    void updateStudent(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String age
    ){
        studentService.updateStudent(id,firstName,lastName,age);
    }

    @DeleteMapping(path = "{id}")
    void deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
    }
}
