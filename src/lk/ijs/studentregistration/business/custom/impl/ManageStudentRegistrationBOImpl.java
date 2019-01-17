package lk.ijs.studentregistration.business.custom.impl;

import lk.ijs.studentregistration.business.Converter;
import lk.ijs.studentregistration.business.custom.ManageStudentRegistrationBO;
import lk.ijs.studentregistration.dao.DAOFactoty;
import lk.ijs.studentregistration.dao.custom.*;
import lk.ijs.studentregistration.dao.custom.impl.StudentRegistrationDAOImpl;
import lk.ijs.studentregistration.db.DBConnection;
import lk.ijs.studentregistration.dto.*;
import lk.ijs.studentregistration.entity.CustomEntity;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class ManageStudentRegistrationBOImpl implements ManageStudentRegistrationBO {
    static StudentRegistrationDAO manageStudentRegistration = DAOFactoty.getInstance().daoType(DAOFactoty.DAOType.STUDENT);
    static ParentDAO parentDAO = DAOFactoty.getInstance().daoType(DAOFactoty.DAOType.PARENT);
    static QualificationDAO qualificationDAO = DAOFactoty.getInstance().daoType(DAOFactoty.DAOType.Qualification);
    static StudentBacthDAO studentBacthDAO =DAOFactoty.getInstance().daoType(DAOFactoty.DAOType.BATCHSTUDENT);
    static QuaryDAO quaryDAO =DAOFactoty.getInstance().daoType(DAOFactoty.DAOType.QUARY);
    public String getStudentID() throws Exception {
        String textPart = StudentRegistrationDAOImpl.getStudentID();
        if (textPart != null) {
            return "IJSE00" + (1 + Integer.parseInt(textPart.substring(6)));
        }
        return "IJSE001";
    }

    public boolean StudentCourceRegistration(StudentDTO student, ArrayList<EducationDTO> qualification, ArrayList<ParentDTO> parentDetails, ArrayList<RegistrationInfoDTO> regInfo) throws Exception {

        Connection connection = DBConnection.getInstance();
        connection.setAutoCommit(false);
        boolean result = manageStudentRegistration.save(Converter.getEntity(student));
        if(!result){
          connection.rollback();
          connection.setAutoCommit(true );
          return false;
        }
            int count =0;

        for (ParentDTO dto:parentDetails) {

            result= parentDAO.save(Converter.ParentDTOConvert(dto));

            if(!result){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
            ++count;
        }

        for (EducationDTO e:qualification) {
            result = qualificationDAO.save(Converter.getEntity(e));
            if(!result){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }

        for (RegistrationInfoDTO dto:regInfo) {
            result = studentBacthDAO.save(Converter.getEntity(dto));
            if(!result){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public StudentDTO findStudent(String id)throws Exception {
        return Converter.getDTO(manageStudentRegistration.find(id));
    }

    @Override
    public boolean registrationExistingStudent(ArrayList<RegistrationInfoDTO> registrationInfoDTO) throws Exception {

        Connection connection = DBConnection.getInstance();
        connection.setAutoCommit(false);
        for (RegistrationInfoDTO dto:registrationInfoDTO) {
            boolean result = studentBacthDAO.save(Converter.getEntity(dto));
            if(!result){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public List<StudentDTO> AllRegisteredStudent() throws Exception {
      return Converter.getDTOList(manageStudentRegistration.findAll());
    }

    @Override
    public List<CustomEntity> studentCoursesInformation(String id) throws Exception {
        return quaryDAO.getStudentRegistrationInformtion(id);
    }

    @Override
    public List<StudentDTO> searchStudentCoursesInformation(String id) throws Exception {
       return Converter.getDTOList( manageStudentRegistration.searchStudentProfile(id));
    }
}