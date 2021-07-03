package com.example.demo.student;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerUnitTest {

    @Before
    public  void  setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getStudents() {

    }
}