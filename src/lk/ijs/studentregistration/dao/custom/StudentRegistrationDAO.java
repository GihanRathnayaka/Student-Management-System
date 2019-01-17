package lk.ijs.studentregistration.dao.custom;

import lk.ijs.studentregistration.dao.CurdDAO;
import lk.ijs.studentregistration.dto.StudentDTO;
import lk.ijs.studentregistration.entity.Student;

import java.util.ArrayList;

public interface StudentRegistrationDAO extends CurdDAO<Student,String> {

    public StudentDTO getStudentProfile(String studentID)throws Exception;
    public ArrayList<Student> searchStudentProfile(String studentID)throws Exception;
}
