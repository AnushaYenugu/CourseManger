package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {CourseCollectionRepository.class})
public class CourseCollectionRepositoryTest {

    @Autowired
    private CourseDao testObject;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }

    //Write your tests here
    CourseCollectionRepository course;
    void setup(){
    Student anusha=new Student(1,"Anusha","anusha@gmail.com","Jönköping");
    List<Student> students=new ArrayList<>();
    students.add(anusha);
   Course java=new Course(1,"Java",LocalDate.parse("2022-08-31"),40,students);
   List<Course> courseList=new ArrayList<>();
   courseList.add(java);
   course=new CourseCollectionRepository(courseList);

    }



    @Test
    void testCreateCourseMethodFromCourseCollectionRepository(){
        setup();
       Course result= course.createCourse("Java Full Stack",LocalDate.parse("2022-09-30"),40);
       assertEquals(result.getCourseName(),"Java Full Stack");
       assertEquals(result.getCourseStartDate(),LocalDate.parse("2022-09-30"));
       assertEquals(result.getCourseWeekDuration(),40);
    }

    @Test
    void testFindById(){
        setup();
        Course result=course.findById(1);
        assertEquals(result.getCourseId(),1);
        assertEquals(result.getCourseName(),"Java");
    }

    @Test
    void testFindByNameContains(){
        setup();
       // Collection<Course> findName=new ArrayList<>();
        Collection<Course> result= course.findByNameContains("Java");
        assertEquals(result.size(),1);
    }

    @Test
    void testFindByDateBefore(){
        setup();
        Collection<Course> result=course.findByDateBefore(LocalDate.parse("2022-10-01"));
        assertEquals(result.size(),1);
    }

    @Test
    void testFindByDateAfter(){
        setup();
        Collection<Course> result=course.findByDateAfter(LocalDate.parse("2022-09-29"));
        assertEquals(result.size(),0);

    }

    @Test
    void testFindAll(){
        setup();
        Collection<Course> result=course.findAll();
        assertEquals(result.size(),1);
    }

    @Test
    void testFindByStudentId(){
        setup();
        Collection<Course> result=course.findByStudentId(1);
        assertEquals(result.size(),1);
    }

    @Test
    void testRemoveCourse(){
      //  setup();
        Student anusha=new Student(1,"Anusha","anusha@gmail.com","Jönköping");
        List<Student> students=new ArrayList<>();
        students.add(anusha);
        Course java=new Course(1,"Java",LocalDate.parse("2022-08-31"),40,students);
        List<Course> courseList=new ArrayList<>();
        courseList.add(java);
        course=new CourseCollectionRepository(courseList);
        assertEquals(courseList.size(),1);
        boolean result=course.removeCourse(java);
        assertTrue(result);
        assertEquals(courseList.size(),0);
    }

    @Test
    void testClear(){
        setup();
        course.clear();
        assertTrue(course.findAll().isEmpty());
    }

    @AfterEach
    void tearDown() {
        testObject.clear();
        CourseSequencer.setCourseSequencer(0);
    }
}
