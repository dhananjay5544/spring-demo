package com.example.demo.student;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class StudentControllerTest {

    @Autowired
    private StudentController studentController;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void insertRecords(){
        List<Student> studentList = List.of(
                new Student(1,"test1","test1",20),
                new Student(2,"test2","test2",30),
                new Student(3,"test3","test3",50),
                new Student(4,"test4","test4",60),
                new Student(5,"test5","test5",20)
        );
        studentRepository.saveAll(studentList);
    }

    @AfterEach
    @Transactional
    void deleteRecords(){
        studentRepository.deleteAll();
    }

    @Test
    void getStudents() {
        List<Student> students = studentController.getStudents();

        MatcherAssert.assertThat(students.size(), Is.is(IsEqual.equalTo(5)));
        MatcherAssert.assertThat(students.get(0).getFirstName(), Is.is(IsEqual.equalTo("test1")));
    }

    @Test
    void createStudent() {
        Student student = new Student("test","test",20);
        Student newStudent = studentController.createStudent(student);

        Assertions.assertNotNull(newStudent);
        Assertions.assertNotNull(newStudent.getId());
        Assertions.assertEquals(student.getFirstName(), newStudent.getFirstName());
    }

    @Test
    void updateStudent()  {

    }

    @Test
    void deleteStudent()  {
        studentController.deleteStudent(4);
        MatcherAssert.assertThat(studentRepository.findAll().size(), IsEqual.equalTo(4));
    }

}