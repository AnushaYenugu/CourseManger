package se.lexicon.course_manager_assignment.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseTest {

    Course course=new Course();

    @BeforeEach
    void setUp(){
        Student anusha=new Student(1,"Anusha","anusha@gmail.com","Jönköping");
        Student harika=new Student(2,"Harika","harika@gmail.com","Hyderabad");
        List<Student> addingStudents=new ArrayList<>();
        addingStudents.add(anusha);
        addingStudents.add(harika);
    }
    @Test
    void courseConstructorTest(){
        Student anusha=new Student(1,"Anusha","anusha@gmail.com","Jönköping");
        Student harika=new Student(2,"Harika","harika@gmail.com","Hyderabad");
        List<Student> addingStudents=new ArrayList<>();
        addingStudents.add(anusha);
        addingStudents.add(harika);
        LocalDate courseStartDate= LocalDate.parse("2020-08-31");
        Course java=new Course(21,"Java",courseStartDate,40,addingStudents);
        assertEquals(java.getStudents().size(),addingStudents.size());

    }
    @Test
    void courseEnrollStudent(){
        Student anusha=new Student(1,"Anusha","anusha@gmail.com","Jönköping");
        Student harika=new Student(2,"Harika","harika@gmail.com","Hyderabad");
        List<Student> addingStudents=new ArrayList<>();
        addingStudents.add(anusha);
        boolean result=course.enrollStudents(anusha);
        boolean result1=course.enrollStudents(harika);
        assertTrue(result1);
        assertTrue(result);

        addingStudents.add(harika);
        assertEquals(addingStudents.size(),2);
    }

@Test
    void courseUnenrollStudent(){
    Student anusha=new Student(1,"Anusha","anusha@gmail.com","Jönköping");
    Student harika=new Student(2,"Harika","harika@gmail.com","Hyderabad");
    boolean result= course.unenrollStudent(anusha);
    assertTrue(result);
}
}
