package lk.ijs.studentregistration.dao.custom;

import lk.ijs.studentregistration.dao.CurdDAO;
import lk.ijs.studentregistration.entity.Batch;

import java.util.ArrayList;
import java.util.List;

public interface BatchDAO extends CurdDAO<Batch,String> {

     ArrayList<String> getAllCources()throws Exception;
     String getBatchNumber()throws Exception;
     String getCourceBatchNumber(String cource)throws Exception;
     List<String> getAviableBatches(String courceNumber)throws Exception;
     String getBatchNum(String courseBatchNo)throws Exception;
     int getMaxstudent( String code)throws Exception;

}
