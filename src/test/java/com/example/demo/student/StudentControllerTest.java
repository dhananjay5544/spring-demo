package com.example.demo.student;

import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentService studentService;
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        studentController = new StudentController(studentService);
    }

    @Test
    void getStudent() throws NotFoundException {
        // given
        Student student = new Student(1, "test", "test", 25);
        given(studentService.getStudent(1)).willReturn(student);

        // when
        Student foundStudent = studentController.getStudent(1);

        // assert
        verify(studentService).getStudent(1);
        Assertions.assertEquals(foundStudent.getId(), student.getId());
    }

    @Test
    void getStudentThrowError() throws NotFoundException {
        // given
        given(studentService.getStudent(1)).willAnswer(invocationOnMock -> {
            throw new NotFoundException("student not found");
        });

        // when
        Student student = studentController.getStudent(1);

        // assert
        verify(studentService).getStudent(1);
        Assertions.assertNull(student);
    }

    @Test
    void getStudents() {
        // when
        studentController.getStudents();

        // assert
        verify(studentService).getStudents();
    }

    @Test
    void createStudent() {
        // given
        Student student = new Student("test", "test", 25);
        given(studentService.createStudent(student)).willReturn(student);

        // when
        Student newStudent = studentController.createStudent(student);

        // assert
        verify(studentService).createStudent(student);
        Assertions.assertEquals(newStudent.getId(), student.getId());
    }

    @Test
    void updateStudent() throws NotFoundException {
        // given
        Student student = new Student(1, "new-firstname", "new-lastname", 26);
        given(studentService.updateStudent(1, "new-firstname", "new-lastname", 26)).willReturn(student);

        // when
        Student updatedStudent = studentController.updateStudent(1, "new-firstname", "new-lastname", 26);

        // assert
        verify(studentService).updateStudent(1, "new-firstname", "new-lastname", 26);
        Assertions.assertEquals(updatedStudent.getFirstName(), student.getFirstName());
        Assertions.assertEquals(updatedStudent.getLastName(), student.getLastName());
        Assertions.assertEquals(updatedStudent.getAge(), student.getAge());
    }

    @Test
    void updateStudentThrowError() throws NotFoundException {
        // given
        given(studentService.updateStudent(1, "new-firstname", "new-lastname", 26)).willAnswer(invocationOnMock -> {
            throw new NotFoundException("student not found");
        });

        // when
        Student updatedStudent = studentController.updateStudent(1, "new-firstname", "new-lastname", 26);

        // assert
        verify(studentService).updateStudent(1, "new-firstname", "new-lastname", 26);
        Assertions.assertNull(updatedStudent);
    }

    @Test
    void deleteStudent() throws NotFoundException {
        // given
        given(studentService.deleteStudent(1)).willReturn("student deleted");

        // when
        String res = studentController.deleteStudent(1);

        // assert
        verify(studentService).deleteStudent(1);
        Assertions.assertEquals("student deleted", res);
    }

    @Test
    void deleteStudentThrowError() throws NotFoundException {
        // given
        given(studentService.deleteStudent(1))
                .willAnswer(invocationOnMock -> {
                    throw new NotFoundException("student not found");
                });

        // when
        String res = studentController.deleteStudent(1);

        // assert
        verify(studentService).deleteStudent(1);
        Assertions.assertEquals("student not found", res);
    }
}