package se.lexicon.course_manager_assignment.data.dao;



import se.lexicon.course_manager_assignment.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;


public class StudentCollectionRepository implements StudentDao {

    private Collection<Student> students;
    Student studentObject;

    public StudentCollectionRepository(Collection<Student> students) {

        this.students = students;
    }

    @Override
    public Student createStudent(String name, String email, String address) {
        studentObject=new Student(name,email,address);
        return studentObject;
    }

    @Override
    public Student findByEmailIgnoreCase(String email) {
        for (Student student:students) {
            if(student.getStudentEmail().equalsIgnoreCase(email)){
                return student;
            }

        }
        return null;
    }

    @Override
    public Collection<Student> findByNameContains(String name) {
        Collection<Student>findByName=new ArrayList<>();
        for (Student student:students) {
            if(student.getStudentName().equals(name)){
                findByName.add(student);
            }

        }
        return findByName;
    }

    @Override
    public Student findById(int id) {
        for (Student student:students) {
            if(student.getStudentId()==id){
                return student;
            }

        }
        return null;
    }

    @Override
    public Collection<Student> findAll() {
        return students;
    }

    @Override
    public boolean removeStudent(Student student) {
        Iterator<Student> remove=students.iterator();
        while(remove.hasNext()){
            if(remove.next().getStudentId()==student.getStudentId()){
                remove.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        this.students = new HashSet<>();
        students.clear();
    }
}
