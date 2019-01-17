package lk.ijs.studentregistration.business.custom;

import lk.ijs.studentregistration.business.SuperBO;
import lk.ijs.studentregistration.dto.*;
import lk.ijs.studentregistration.entity.CustomEntity;

import java.util.ArrayList;
import java.util.List;

public interface ManageStudentRegistrationBO extends SuperBO {
     String getStudentID() throws Exception;
    boolean StudentCourceRegistration(StudentDTO student, ArrayList<EducationDTO> qualification, ArrayList<ParentDTO> parentDetails, ArrayList<RegistrationInfoDTO> regInfo) throws Exception;
    StudentDTO findStudent(String id)throws Exception;
    boolean registrationExistingStudent( ArrayList<RegistrationInfoDTO> registrationInfoDTO)throws Exception;
    List<StudentDTO> AllRegisteredStudent()throws Exception;
    List<CustomEntity> studentCoursesInformation(String id)throws Exception;
    List<StudentDTO> searchStudentCoursesInformation(String id)throws Exception;

}
