package lk.ijs.studentregistration.business.custom.impl;

import lk.ijs.studentregistration.business.Converter;
import lk.ijs.studentregistration.business.custom.ManageBatchesBO;
import lk.ijs.studentregistration.dao.DAOFactoty;
import lk.ijs.studentregistration.dao.custom.BatchDAO;
import lk.ijs.studentregistration.dao.custom.CourceDAO;
import lk.ijs.studentregistration.dao.custom.StudentBacthDAO;
import lk.ijs.studentregistration.dao.custom.QuaryDAO;
import lk.ijs.studentregistration.dto.BatchDTO;
import lk.ijs.studentregistration.entity.Batch;
import lk.ijs.studentregistration.entity.CustomEntity;


import java.util.ArrayList;
import java.util.List;

public class ManageBatchesBOImpl implements ManageBatchesBO {

    BatchDAO batchDAO = DAOFactoty.getInstance().daoType(DAOFactoty.DAOType.BATCH);
    CourceDAO manageCourceDAO = DAOFactoty.getInstance().daoType(DAOFactoty.DAOType.COURSE);
    StudentBacthDAO studentBacthDAO = DAOFactoty.getInstance().daoType(DAOFactoty.DAOType.BATCHSTUDENT);
    QuaryDAO quaryDAO  =DAOFactoty.getInstance().daoType(DAOFactoty.DAOType.QUARY);
    @Override
    public boolean createBatch(BatchDTO batch) throws Exception {
        Batch b = Converter.get(batch);
        return batchDAO.save(b);

    }

    @Override
    public boolean updatBatch(BatchDTO batch) throws Exception {
        Batch b = Converter.get(batch);
        return batchDAO.update(b);
    }

    @Override
    public boolean deleteBatch(String text) throws Exception {
       return batchDAO.delete(text);
    }

    @Override
    public ArrayList<CustomEntity> AllBatches() throws Exception {
        List<CustomEntity> all = quaryDAO.select();
        return (ArrayList<CustomEntity>) all;
    }

    @Override
    public ArrayList<String> getAllCources() throws Exception {
       return (ArrayList<String>) manageCourceDAO.getAllCources();
    }

    @Override
    public String getBatchNumber() throws Exception {
        String batch= batchDAO.getBatchNumber();
        return batch.substring(0,3)+(1+Integer.parseInt(batch.substring(3)));
    }

    @Override
    public String getCourceBatchNumber(String cource) throws Exception {
        String courceBatchNumber = batchDAO.getCourceBatchNumber(cource);
        String[] split = cource.split("%");
        if(courceBatchNumber==null){return split[0]+1;}
        courceBatchNumber=split[0]+(1+Integer.parseInt(courceBatchNumber.substring(split[0].length())));
        return courceBatchNumber;
    }

    @Override
    public String getCourceNumber(String name) throws Exception {
        return manageCourceDAO.getCourceNumber(name);
    }

    @Override
    public ArrayList<String> getAviableBatches(String courceNumber) throws Exception {
        return (ArrayList<String>) batchDAO.getAviableBatches(courceNumber);
    }

    @Override
    public String getBatchName(String batchNo) throws Exception {
        return batchDAO.getCourceBatchNumber(batchNo);
    }

    @Override
    public String getBatchNum(String BatchNo) throws Exception {
       return batchDAO.getBatchNum(BatchNo);
    }

    @Override
    public int getMaxstudent(String code) throws Exception {
        return batchDAO.getMaxstudent(code);
    }

    @Override
    public int getRegisterdStudent(String courseBatchNo) throws Exception {
        return studentBacthDAO.getRegisterdStudent(courseBatchNo);
    }
}
