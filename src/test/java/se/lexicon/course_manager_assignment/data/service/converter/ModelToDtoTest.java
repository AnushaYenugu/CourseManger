package se.lexicon.course_manager_assignment.data.service.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {ModelToDto.class})
public class ModelToDtoTest {

    @Autowired
    private Converters testObject;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertNotNull(testObject);
    }

    //write your tests here

    ModelToDto modelToDto=new ModelToDto();

    @Test
    void testStudentToStudentViewMethod(){
        Student anusha=new Student(1,"Anusha","anusha@gmail.com","Jönköping");
        StudentView result=modelToDto.studentToStudentView(anusha);
        assertEquals(1,result.getId());
        assertEquals("Anusha",result.getName());
        assertEquals("anusha@gmail.com",result.getEmail());
        assertEquals("Jönköping",result.getAddress());

    }

    @Test
    void testCourseToCourseViewMethod(){
        Student anusha=new Student(1,"Anusha","anusha@gmail.com","Jönköping");
        Student harika=new Student(2,"Harika","harika@gmail.com","Hyderabad");
        List list=new ArrayList();
        list.add(anusha);
        list.add(harika);
        Course java=new Course(1,"Java", LocalDate.parse("2022-08-31"),40,list);
        CourseView result=modelToDto.courseToCourseView(java);
       // assertEquals("1",result.getId());
        assertEquals("Java",result.getCourseName());
        assertEquals(LocalDate.parse("2022-08-31"),result.getStartDate());
        assertEquals(40,result.getWeekDuration());
        assertEquals(2,result.getStudents().size());
        assertEquals(anusha,result.getStudents().get(0));

    }

    @Test
    void testCoursesToCourseViews(){
        Student anusha=new Student(1,"Anusha","anusha@gmail.com","Jönköping");
        Student harika=new Student(2,"Harika","harika@gmail.com","Hyderabad");
        List list=new ArrayList();
        list.add(anusha);
        list.add(harika);
        Course java=new Course(1,"Java", LocalDate.parse("2022-08-31"),40,list);
        Course dotNet=new Course(2,".Net",LocalDate.parse("2022-09-30"),35,list);
        List courseList=new ArrayList<>();
        courseList.add(java);
        courseList.add(dotNet);
        List<CourseView> result=modelToDto.coursesToCourseViews(courseList);
        assertEquals(2,result.size());
        assertEquals("Java",result.get(0).getCourseName());
        assertEquals(2,result.get(1).getId());

    }
    @Test
    void testStudentsToStudentViews(){
        Student anusha=new Student(1,"Anusha","anusha@gmail.com","Jönköping");
        Student harika=new Student(2,"Harika","harika@gmail.com","Hyderabad");
        List list=new ArrayList();
        list.add(anusha);
        list.add(harika);
       List<StudentView> result=modelToDto.studentsToStudentViews(list);
       assertEquals(1,result.get(0).getId());
       assertEquals("Anusha",result.get(0).getName());
       assertEquals("anusha@gmail.com",result.get(0).getEmail());
       assertEquals(2,result.get(1).getId());
       assertEquals("Harika",result.get(1).getName());
       assertEquals("harika@gmail.com",result.get(1).getEmail());

    }


}
