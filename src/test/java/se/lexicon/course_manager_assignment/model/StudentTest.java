package se.lexicon.course_manager_assignment.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import static org.springframework.test.util.AssertionErrors.assertEquals;

public class StudentTest {
    Student student=new Student();
    @Test
    void studentConstructionTest(){
        int id=1;
        Student anusha=new Student(1,"Anusha","anusha@gmail.com","Jönköping");
        assertEquals(anusha.getStudentName(),"Anusha");
        assertEquals(anusha.getStudentId(),1);
        assertEquals(anusha.getStudentEmail(),"anusha@gmail.com");
        assertEquals(anusha.getStudentAddress(),"Jönköping");
    }


}
