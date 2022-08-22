package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {StudentCollectionRepository.class})
public class StudentCollectionRepositoryTest {

    @Autowired
    private StudentDao testObject;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }

    //Write your tests here
    StudentCollectionRepository studentCollectionRepository;
    void setUp(){
        List<Student> studentList=new ArrayList<>();
        Student anusha=new Student(1,"Anusha","anusha@gmail.com","Jönköping");
        Student harika=new Student(2,"Harika","harika@gmail.com","Hyderabad");
          studentList.add(anusha);
          studentList.add(harika);
        studentCollectionRepository=new StudentCollectionRepository(studentList);
    }
    @Test
    void testCreateStudentMethodFromStudentCollectionRepository(){
           setUp();
        Student result=studentCollectionRepository.createStudent("Anusha Yenugu","yenugu@gmail.com","India");
        assertEquals(result.getStudentName(),"Anusha Yenugu");
        assertEquals(result.getStudentEmail(),"yenugu@gmail.com");
        assertEquals(result.getStudentAddress(),"India");

    }

    @Test
    void testFindByEmail(){
        setUp();
        Student result=studentCollectionRepository.findByEmailIgnoreCase("ANUSHA@Gmail.com");
        assertEquals(1,result.getStudentId());
        assertEquals("Anusha",result.getStudentName());
        assertEquals("anusha@gmail.com",result.getStudentEmail());
    }

    @Test
    void testFindByName(){
        setUp();
        Collection<Student> result=studentCollectionRepository.findByNameContains("Harika");
        assertEquals(result.size(),1);
    }

    @Test
    void testFindByStudentId(){
        setUp();
        Student result=studentCollectionRepository.findById(2);
        assertEquals(2,result.getStudentId());
        assertEquals("Harika",result.getStudentName());
        assertEquals("harika@gmail.com",result.getStudentEmail());
    }

    @Test
    void testFindAll(){
        setUp();
        Collection<Student> result= studentCollectionRepository.findAll();
        assertEquals(2,result.size());
    }

    @Test
    void testRemoveStudentAnusha(){

        List<Student> studentList=new ArrayList<>();
        Student anusha=new Student(1,"Anusha","anusha@gmail.com","Jönköping");
        Student harika=new Student(2,"Harika","harika@gmail.com","Hyderabad");
        studentList.add(anusha);
        studentList.add(harika);
        studentCollectionRepository=new StudentCollectionRepository(studentList);
        boolean result=studentCollectionRepository.removeStudent(anusha);
        assertTrue(result);
        assertEquals(studentList.size(),1);
    }

    @Test
    void testClear(){
        setUp();
        studentCollectionRepository.clear();
        assertTrue(studentCollectionRepository.findAll().isEmpty());

    }

    @AfterEach
    void tearDown() {
        testObject.clear();
        StudentSequencer.setStudentSequencer(0);
    }


}
