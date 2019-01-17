package lk.ijs.studentregistration.dao.custom;

import lk.ijs.studentregistration.dao.SuperDAO;
import lk.ijs.studentregistration.entity.CustomEntity;

import java.util.ArrayList;

public interface QuaryDAO extends SuperDAO {

    ArrayList<CustomEntity> select()throws Exception;
    ArrayList<CustomEntity>getStudentRegistrationInformtion (String id) throws Exception;
    ArrayList<CustomEntity>searchStudentRegistrationInformtion (String id) throws Exception;
}
