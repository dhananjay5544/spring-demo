package com.example.demo.student;

import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentRepository);
    }

    @Test
    void getStudent() throws NotFoundException {
        // given
        Student student = new Student(1, "test", "test", 25);
        given(studentRepository.findById(1)).willReturn(Optional.of(student));

        // when
        Student newStudent = studentService.getStudent(1);

        // assert
        verify(studentRepository).findById(1);
        Assertions.assertEquals(newStudent, student);
    }

    @Test
    void getStudentThrowError() {
        // given
        given(studentRepository.findById(1)).willAnswer(invocationOnMock -> {
            throw new NotFoundException("student not found");
        });

        // assert
        assertThatThrownBy(() -> studentService.getStudent(1))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("student not found");
    }

    @Test
    void getStudents() {
        // when
        studentService.getStudents();

        // assert
        verify(studentRepository).findAll();
    }

    @Test
    void createStudent() {
        // given
        Student student = new Student("test", "test", 25);
        given(studentRepository.save(student)).willReturn(student);

        // when
        Student newStudent = studentService.createStudent(student);

        // assert
        verify(studentRepository).save(student);
        Assertions.assertEquals(newStudent.getId(), student.getId());
    }

    @Test
    void updateStudent() throws NotFoundException {
        // given
        Student student = new Student(1, "test", "test", 25);
        given(studentRepository.findById(1)).willReturn(Optional.of(student));

        // when
        Student updatedStudent = studentService.updateStudent(1, "new-firstname", "new-lastname", 26);

        // assert
        Assertions.assertEquals(updatedStudent.getFirstName(), student.getFirstName());
        Assertions.assertEquals(updatedStudent.getLastName(), student.getLastName());
        Assertions.assertEquals(updatedStudent.getAge(), student.getAge());
    }

    @Test
    void updateStudentThrowError() {
        // given
        given(studentRepository.findById(1)).willAnswer(invocationOnMock -> {
            throw new NotFoundException("student not found");
        });

        // assert
        assertThatThrownBy(() -> studentService.updateStudent(1, "new-firstname", "new-lastname", 26))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("student not found");
    }

    @Test
    void deleteStudent() throws NotFoundException {
        // given
        Student student = new Student(1, "test", "test", 25);
        given(studentRepository.findById(1)).willReturn(Optional.of(student));

        // when
        studentService.deleteStudent(1);

        // assert
        verify(studentRepository).deleteById(1);
    }

    @Test
    @Disabled
    void deleteStudentThrowError() {
        // given
        given(studentRepository.findById(1)).willAnswer(invocationOnMock -> {
            throw new NotFoundException("student not found");
        });

        // assert
        assertThatThrownBy(() -> studentService.deleteStudent(1))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("student not found");
    }
}