package se.lexicon.course_manager_assignment.data.dao;



import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;


public class CourseCollectionRepository implements CourseDao{

    private Collection<Course> courses;
    Course courseObject;


    public CourseCollectionRepository(Collection<Course> courses) {

        this.courses = courses;
    }

    @Override
    public Course createCourse(String courseName, LocalDate startDate, int weekDuration) {
        courseObject=new Course(courseName,startDate,weekDuration);
        return courseObject;
    }
    /**
    @Override
    public Course updateCourse(int id, String courseName,LocalDate startDate,int weekDuration){
        courseObject.setCourseName(courseName);
        courseObject.setCourseStartDate(startDate);
        courseObject.setCourseWeekDuration(weekDuration);
        return courseObject;
    }
     */

    @Override
    public Course findById(int id) {
        for (Course findId:courses) {
            if(findId.getCourseId()==id){
                return findId;
            }

        }
        return null;
    }

    @Override
    public Collection<Course> findByNameContains(String name) {
        Collection<Course> findByName=new ArrayList<>();
        for (Course byName: courses) {
            findByName.add(byName);

        }
        return findByName;
    }

    @Override
    public Collection<Course> findByDateBefore(LocalDate end) {
        Collection<Course> byDateBefore=new ArrayList<>();
        for(Course byDate: courses){
            if(byDate.getCourseStartDate().isBefore(end))
                  byDateBefore.add(byDate);
        }
        return byDateBefore;
    }

    @Override
    public Collection<Course> findByDateAfter(LocalDate start) {
        Collection<Course> byDateAfter=new ArrayList<>();
        for (Course byDate:courses) {
            if(byDate.getCourseStartDate().isAfter(start))
                 byDateAfter.add(byDate);
        }
        return byDateAfter;
    }

    @Override
    public Collection<Course> findAll() {

        return courses;
    }

    @Override
    public Collection<Course> findByStudentId(int studentId) {
        Collection<Course> byStudentId=new ArrayList<>();
        for (Course course: courses) {
            for(Student courseStudent : course.getStudents()) {
                if(courseStudent.getStudentId() == studentId) {
                    byStudentId.add(course);
                    break;
                }
            }
          }
        return byStudentId;
    }

    @Override
    public boolean removeCourse(Course course) {
        Iterator<Course> remove= courses.iterator();
        while(remove.hasNext()){
            if(remove.next().getCourseId()==course.getCourseId()){
                remove.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        this.courses = new HashSet<>();
        courses.clear();
    }
}
