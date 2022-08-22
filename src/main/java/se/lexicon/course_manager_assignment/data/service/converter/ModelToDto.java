package se.lexicon.course_manager_assignment.data.service.converter;

import org.springframework.stereotype.Component;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelToDto implements Converters {
    List<CourseView> courseViewLists;
    List<StudentView> studentViewLists;
    @Override
    public StudentView studentToStudentView(Student student) {
        return new StudentView(student.getStudentId(), student.getStudentName(), student.getStudentEmail(), student.getStudentAddress());
    }

    @Override
    public CourseView courseToCourseView(Course course) {
        List<StudentView> list=studentsToStudentViews(course.getStudents());
        return new CourseView(course.getCourseId(), course.getCourseName(),course.getCourseStartDate(),course.getCourseWeekDuration(),list);
    }

    @Override
    public List<CourseView> coursesToCourseViews(Collection<Course> courses) {
        courseViewLists = new ArrayList<>();
        for (Course course: courses) {
            courseViewLists.add(courseToCourseView(course));
        }
          return courseViewLists;
    }

    @Override
    public List<StudentView> studentsToStudentViews(Collection<Student> students) {
        studentViewLists=new ArrayList<>();
        for (Student student: students) {
            studentViewLists.add(studentToStudentView(student));
        }
        return studentViewLists;
    }
}
