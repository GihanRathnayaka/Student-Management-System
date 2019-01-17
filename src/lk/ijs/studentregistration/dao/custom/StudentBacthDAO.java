package lk.ijs.studentregistration.dao.custom;

import lk.ijs.studentregistration.dao.CurdDAO;
import lk.ijs.studentregistration.entity.BatchRegistration;

public interface StudentBacthDAO extends CurdDAO<BatchRegistration,String> {
    int getRegisterdStudent(String courseBatchNo)throws Exception;

}
