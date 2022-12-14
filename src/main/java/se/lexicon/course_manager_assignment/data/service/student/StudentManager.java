package se.lexicon.course_manager_assignment.data.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateStudentForm;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.Collection;
import java.util.List;

@Service
public class StudentManager implements StudentService {

    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final Converters converters;

    @Autowired
    public StudentManager(StudentDao studentDao, CourseDao courseDao, Converters converters) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.converters = converters;
    }

    @Override
    public StudentView create(CreateStudentForm form) {
        Student student=studentDao.createStudent(form.getName(),form.getEmail(),form.getAddress());
        return converters.studentToStudentView(student);
    }

    @Override
    public StudentView update(UpdateStudentForm form) {
        Student student=studentDao.findById(form.getId());
        student.setStudentName(form.getName());
        student.setStudentEmail(form.getEmail());
        student.setStudentAddress(form.getAddress());
        return converters.studentToStudentView(student);
    }

    @Override
    public StudentView findById(int id) {
        Student student=studentDao.findById(id);
            return converters.studentToStudentView(student);
    }

    @Override
    public StudentView searchByEmail(String email) {
        Student student=studentDao.findByEmailIgnoreCase(email);
        return converters.studentToStudentView(student);
    }

    @Override
    public List<StudentView> searchByName(String name) {
        Collection<Student> students=studentDao.findByNameContains(name);
        return converters.studentsToStudentViews(students);
    }

    @Override
    public List<StudentView> findAll() {
        Collection<Student> students=studentDao.findAll();
        return converters.studentsToStudentViews(students);
    }

    @Override
    public boolean deleteStudent(int id) {
        Student student =studentDao.findById(id);
        boolean removeStudent=studentDao.removeStudent(student);
        return removeStudent;
    }
}
