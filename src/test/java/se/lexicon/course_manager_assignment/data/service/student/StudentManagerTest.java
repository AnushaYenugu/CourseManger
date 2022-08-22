package se.lexicon.course_manager_assignment.data.service.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.dao.CourseCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.data.service.converter.ModelToDto;
import se.lexicon.course_manager_assignment.dto.forms.CreateCourseForm;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateStudentForm;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {StudentManager.class, CourseCollectionRepository.class, StudentCollectionRepository.class, ModelToDto.class})
public class StudentManagerTest {

    @Autowired
    private StudentService testObject;
    @Autowired
    private StudentDao studentDao;
    private CourseDao courseDao;
    private Converters converters;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertNotNull(testObject);
        assertNotNull(studentDao);
    }

    //Write your tests here

    StudentManager studentManager;
    void setUp(){
        Student anusha=new Student(1,"Anusha","anusha@gmail.com","Jönköping");
        List<Student> students=new ArrayList<>();
        students.add(anusha);
        Course java =new Course(1,"Java", LocalDate.parse("2022-08-31"),40,students);
        List<Course> courses=new ArrayList<>();
        courses.add(java);

        studentDao=new StudentCollectionRepository(students);
        courseDao=new CourseCollectionRepository(courses);
        converters=new ModelToDto();
        studentManager=new StudentManager(studentDao,courseDao,converters);
    }

    @Test
    void testCreateMethod(){
        setUp();
        CreateStudentForm form=new CreateStudentForm(1,"Anusha","anusha@gmail.com","Jönköping");
        StudentView result=studentManager.create(form);
        assertEquals("Anusha",result.getName());
        assertEquals("anusha@gmail.com",result.getEmail());
        assertEquals("Jönköping",result.getAddress());
    }

    @Test
    void testUpdateMethod(){
        setUp();
        UpdateStudentForm form=new UpdateStudentForm(1,"Harika","harika@gmail.com","Hyderabad");
        StudentView result=studentManager.update(form);
        assertEquals("Harika",result.getName());
        assertEquals("harika@gmail.com",result.getEmail());
        assertEquals("Hyderabad",result.getAddress());
    }

    @Test
    void testFindByIdMethod(){
        setUp();
        StudentView result=studentManager.findById(1);
        assertEquals("Anusha",result.getName());
        assertEquals("anusha@gmail.com",result.getEmail());
        assertEquals("Jönköping",result.getAddress());
    }
    @Test
    void testSearchByEmailMethod(){
        setUp();
        StudentView result=studentManager.searchByEmail("anusha@gmail.com");
        assertEquals("Anusha",result.getName());
        assertEquals("Jönköping",result.getAddress());
        assertEquals(1,result.getId());
    }

    @Test
    void testSearchByName(){
        setUp();
        List<StudentView> result=studentManager.searchByName("Anusha");
        assertEquals(1,result.size());
        assertEquals("anusha@gmail.com",result.get(0).getEmail());
        assertEquals(1,result.get(0).getId());
        assertEquals("Jönköping",result.get(0).getAddress());
    }

    @Test
    void testFindAll(){
        setUp();
        List<StudentView> result=studentManager.findAll();
        assertEquals(1,result.size());
        assertEquals(1,result.get(0).getId());
        assertEquals("Anusha",result.get(0).getName());
        assertEquals("anusha@gmail.com",result.get(0).getEmail());
        assertEquals("Jönköping",result.get(0).getAddress());
    }

    @Test
    void testDeleteStudent(){
        setUp();
        boolean result=studentManager.deleteStudent(1);
        assertTrue(result);
    }

    @AfterEach
    void tearDown() {
        StudentSequencer.setStudentSequencer(0);
        studentDao.clear();
    }
}
