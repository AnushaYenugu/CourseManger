package se.lexicon.course_manager_assignment.model;

import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class Course {
    private int courseId;
    private String courseName;
    private LocalDate courseStartDate;
    private int courseWeekDuration;
  //  Student student;
    private Collection<Student> students=new ArrayList<>();

    public Course(int courseId, String courseName, LocalDate courseStartDate, int courseWeekDuration, Collection<Student> students) {
        this.courseId = CourseSequencer.nextCourseId();
        this.courseName = courseName;
        this.courseStartDate = courseStartDate;
        this.courseWeekDuration = courseWeekDuration;
        this.students = students;
    }
    public Course(String courseName, LocalDate courseStartDate,int courseWeekDuration){
        this.courseName=courseName;
        this.courseStartDate=courseStartDate;
        this.courseWeekDuration=courseWeekDuration;
    }
    public Course(){}

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(LocalDate courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public int getCourseWeekDuration() {
        return courseWeekDuration;
    }

    public void setCourseWeekDuration(int courseWeekDuration) {
        this.courseWeekDuration = courseWeekDuration;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

 /**   public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    } */

    public boolean enrollStudents(Student student){
        students.add(student);
        return true;
    }
    public boolean unenrollStudent(Student student){
        students.remove(student);
        return true;
    }
    @Override
    public boolean equals(Object object){
        if(object==this){
            return true;
        }

        if(object==null || object.getClass()!=this.getClass()){
            return false;
        }
        Course course=(Course) object;
        return courseId==course.courseId &&
                (courseName.equals(course.courseName) || (courseName!=null && courseName.equals(course.getCourseName()))) &&
                (courseStartDate!=null || courseStartDate.equals(course.getCourseStartDate())) &&
                courseWeekDuration==course.courseWeekDuration &&
                (students!=null ||students.equals(course.students)|| students.equals(course.getStudents()));
    }


    @Override
    public int hashCode(){
        final int prime=31;
        int result=1;
        result=prime*result +courseId;
        result=prime*result+((courseName==null)?0:courseName.hashCode());
        result=prime*result+((courseStartDate==null)?0:courseStartDate.hashCode());
        result=prime*result+(courseWeekDuration);
        result=prime*result+((students==null)?0:students.hashCode());
        return result;

    }

    public String toString(){
        return getCourseId()+" "+getCourseName()+" "+getCourseStartDate()+" "+getCourseWeekDuration()+" "+getStudents();
    }
}
