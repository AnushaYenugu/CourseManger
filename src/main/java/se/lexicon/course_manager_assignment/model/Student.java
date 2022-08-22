package se.lexicon.course_manager_assignment.model;

import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;

public class Student {
    private int studentId;
    private String studentName;
    private String studentEmail;
    private String studentAddress;

    public Student(int studentId, String studentName, String studentEmail, String studentAddress) {
        this.studentId = StudentSequencer.nextStudentId();
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentAddress = studentAddress;
    }
    public Student(String studentName,String studentEmail, String studentAddress){
        this.studentName=studentName;
        this.studentEmail=studentEmail;
        this.studentAddress=studentAddress;
    }
    public Student(){}
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    @Override
    public boolean equals(Object object){

        if(object==this){
            return true;
        }

        if(object==null || object.getClass()!=this.getClass()){
            return false;
        }
       Student student=(Student) object;
        return studentId==student.studentId && (studentName.equals(student.studentName) || (studentName!=null && studentName.equals(student.getStudentName()))) &&
                (studentEmail!=null || studentEmail.equals(student.getStudentEmail())) &&
                (studentAddress!=null || (student.equals(getStudentAddress())));

    }

    @Override
    public int hashCode(){
        final int prime=31;
        int result=1;
        result=prime*result +studentId;
        result=prime*result+((studentName==null)?0:studentName.hashCode());
        result=prime*result+((studentEmail==null)?0:studentEmail.hashCode());
        result=prime*result+((studentAddress==null)?0:studentAddress.hashCode());
        return result;
    }

    @Override
    public String toString(){
        return studentId+" "+studentName+" "+studentEmail+" "+studentAddress;
    }
}
