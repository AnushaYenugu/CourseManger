package se.lexicon.course_manager_assignment.data.service.course;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.dao.CourseCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.data.service.converter.ModelToDto;
import se.lexicon.course_manager_assignment.dto.forms.CreateCourseForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateCourseForm;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {CourseManager.class, CourseCollectionRepository.class, ModelToDto.class, StudentCollectionRepository.class})
public class CourseManagerTest {

    @Autowired
    private CourseService testObject;

    @Autowired
    private CourseDao courseDao;
    private StudentDao studentDao;
    private Converters converters;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertNotNull(testObject);
        assertNotNull(courseDao);
    }

    //Write your tests here
    CourseManager courseManager;

    void setup() {
        Student anusha = new Student(1, "Anusha", "anusha@gmail.com", "Jönköping");
        List<Student> list = new ArrayList<>();
        list.add(anusha);
        Course java = new Course(1, "Java", LocalDate.parse("2022-08-31"), 40, list);
       List<Course> courseList=new ArrayList<>();
       courseList.add(java);
       courseDao=new CourseCollectionRepository(courseList);
       studentDao=new StudentCollectionRepository(list);
       converters=new ModelToDto();
      //  CourseManager courseManager = new CourseManager(courseDao, studentDao, converters);
         courseManager = new CourseManager(courseDao, studentDao, converters);

    }

    @Test
    void testCreateMethod(){
        setup();
        CreateCourseForm form=new CreateCourseForm(1,"Java", LocalDate.parse("2022-08-31"),40);
        CourseView result=courseManager.create(form);
        assertEquals("Java",result.getCourseName());
        assertEquals(LocalDate.parse("2022-08-31"),result.getStartDate());
        assertEquals(40,result.getWeekDuration());

    }

    @Test
    void testUpdateMethod(){
        setup();
        UpdateCourseForm form=new UpdateCourseForm(1,".Net",LocalDate.parse("2022-09-30"),35);
        CourseView result=courseManager.update(form);
        assertEquals(1,result.getId());
        assertEquals(".Net",result.getCourseName());
        assertEquals(LocalDate.parse("2022-09-30"),result.getStartDate());
        assertEquals(35,result.getWeekDuration());
    }

    @Test
    void testSearchByCourseNameMethod(){
        setup();
        List<CourseView> result=courseManager.searchByCourseName("Java");
        assertEquals(1,result.size());
        assertEquals(1,result.get(0).getId());
        assertEquals("Java",result.get(0).getCourseName());
        assertEquals(LocalDate.parse("2022-08-31"),result.get(0).getStartDate());
        assertEquals(40,result.get(0).getWeekDuration());
    }

    @Test
    void testSearchByDateBeforeMethod(){
        setup();
        List<CourseView> result=courseManager.searchByDateBefore(LocalDate.parse("2022-09-01"));
        assertEquals(1,result.size());
        assertEquals(1,result.get(0).getId());
        assertEquals("Java",result.get(0).getCourseName());
        assertEquals(LocalDate.parse("2022-08-31"),result.get(0).getStartDate());
        assertEquals(40,result.get(0).getWeekDuration());
        assertEquals(1,result.get(0).getStudents().size());
        assertEquals("Anusha",result.get(0).getStudents().get(0).getName());
    }
    @Test
    void testSearchByDateAfterMethod(){
        setup();
        List<CourseView> result=courseManager.searchByDateAfter(LocalDate.parse("2022-08-30"));
        assertEquals(1,result.size());
        assertEquals(1,result.get(0).getId());
        assertEquals("Java",result.get(0).getCourseName());
        assertEquals(LocalDate.parse("2022-08-31"),result.get(0).getStartDate());
        assertEquals(40,result.get(0).getWeekDuration());
        assertEquals(1,result.get(0).getStudents().size());
        StudentView testValue=result.get(0).getStudents().get(0);
        assertEquals("Anusha",testValue.getName());
    }
    @Test
    void testAddStudentToCourseMethod(){
        setup();
        boolean result=courseManager.addStudentToCourse(1,1);
        assertTrue(result);
    }

    @Test
    void testRemoveStudentFromCourseMethod(){
        setup();
        boolean result=courseManager.removeStudentFromCourse(1,1);
        assertTrue(result);
    }

    @Test
    void testFindByIdMethod(){
        setup();
        CourseView result=courseManager.findById(1);
        assertEquals(1,result.getId());
        assertEquals("Java",result.getCourseName());
        assertEquals(LocalDate.parse("2022-08-31"),result.getStartDate());
        assertEquals(40,result.getWeekDuration());
        assertEquals(1,result.getStudents().get(0).getId());
        assertEquals("Anusha",result.getStudents().get(0).getName());
    }
    @Test
    void testFindAllMethod(){
        setup();
        List<CourseView> result=courseManager.findAll();
        assertEquals(1,result.size());
        assertEquals(1,result.get(0).getId());
        assertEquals("Java",result.get(0).getCourseName());
        assertEquals(LocalDate.parse("2022-08-31"),result.get(0).getStartDate());
        assertEquals(40,result.get(0).getWeekDuration());
        assertEquals(1,result.get(0).getStudents().get(0).getId());
        assertEquals("Anusha",result.get(0).getStudents().get(0).getName());
        assertEquals("anusha@gmail.com",result.get(0).getStudents().get(0).getEmail());
        assertEquals("Jönköping",result.get(0).getStudents().get(0).getAddress());
    }

    @Test
    void testFindByStudentId(){
        setup();
        List<CourseView> result=courseManager.findByStudentId(1);
        assertEquals(1,result.size());
        assertEquals("Java",result.get(0).getCourseName());
        assertEquals(LocalDate.parse("2022-08-31"),result.get(0).getStartDate());
        assertEquals(40,result.get(0).getWeekDuration());

    }
    @Test
    void testDeleteCourseMethod(){
        setup();
        boolean result=courseManager.deleteCourse(1);
        assertTrue(result);
    }

    @AfterEach
    void tearDown() {
        courseDao.clear();
        CourseSequencer.setCourseSequencer(0);
    }
}
