package lk.ijs.studentregistration.business.custom;

import lk.ijs.studentregistration.business.SuperBO;
import lk.ijs.studentregistration.dto.BatchDTO;
import lk.ijs.studentregistration.entity.CustomEntity;

import java.util.ArrayList;

public interface ManageBatchesBO extends SuperBO {
     boolean createBatch(BatchDTO batch)throws Exception;
     boolean updatBatch(BatchDTO batch)throws Exception;
     boolean deleteBatch(String text)throws Exception;
     ArrayList<CustomEntity> AllBatches()throws Exception;
     ArrayList<String> getAllCources()throws Exception ;
     String getBatchNumber()throws Exception;
     String getCourceBatchNumber(String cource) throws Exception;
     String getCourceNumber(String name)throws Exception;
     ArrayList<String> getAviableBatches(String courceNumber)throws Exception;
     String getBatchName(String batchNo) throws Exception;
     String getBatchNum(String courseBatchNo)throws Exception;
     int getMaxstudent( String code)throws Exception;
     int getRegisterdStudent(String courseBatchNo)throws Exception;

}
