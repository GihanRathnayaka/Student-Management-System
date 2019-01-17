package lk.ijs.studentregistration.dao.custom.impl;

import lk.ijs.studentregistration.dao.custom.BatchDAO;
import lk.ijs.studentregistration.db.DBConnection;
import lk.ijs.studentregistration.entity.Batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BatchDAOImpl implements BatchDAO {


    @Override
    public boolean save(Batch entity) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("INSERT INTO batch VALUES (?,?,?,?,?,?,?,?,?,?)");
        pst.setObject(1,entity.getBatchNumber());
        pst.setObject(2,entity.getBatchName());
        pst.setObject(3,entity.getCourceBatchNo());
        pst.setObject(4,entity.getStartDate());
        pst.setObject(5,entity.getEndDate());
        pst.setObject(6,entity.getNofStudent());
        pst.setObject(7,entity.getDescription());
        pst.setObject(8,entity.getCourceNo());
        pst.setObject(9,entity.getAppCloseDay());
        pst.setObject(10,entity.getFee());
        int num = pst.executeUpdate();
        if(num>0){ return true;}
        return false;
    }

    @Override
    public boolean update(Batch entity) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("UPDATE batch SET  batchName=?, courceBatchNo=?, startDate=?, endDate=?, nofStudent=?, description=?, courceNo=?, appCloseDay=? ,fee=? WHERE batchNumber=?");
        pst.setObject(10,entity.getBatchNumber());
        pst.setObject(1,entity.getBatchName());
        pst.setObject(2,entity.getCourceBatchNo());
        pst.setObject(3,entity.getStartDate());
        pst.setObject(4,entity.getEndDate());
        pst.setObject(5,entity.getNofStudent());
        pst.setObject(6,entity.getDescription());
        pst.setObject(7,entity.getCourceNo());
        pst.setObject(8,entity.getAppCloseDay());
        pst.setObject(9,entity.getFee());

        int num = pst.executeUpdate();
        if(num>0){ return true;}
        return false;
    }

    @Override
    public boolean delete(String key) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("DELETE FROM batch WHERE batchNumber =?");
        pst.setObject(1,key);
        int rst = pst.executeUpdate();
        if(rst>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Batch> findAll() throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM batch");
        ResultSet rst = pst.executeQuery();
        List<Batch>temp = new ArrayList<>();
        while (rst.next()){
            String batchNumber = rst.getString(1);
            String batchName = rst.getString(2);
            String courceBatchNo = rst.getString(3);
            LocalDate startDate= rst.getDate(4).toLocalDate();
            LocalDate endDate= rst.getDate(5).toLocalDate();
            int nofStudent = rst.getInt(6);
            String description = rst.getString(7);
            String courceNo = rst.getString(8);
            LocalDate appCloseDa = rst.getDate(9).toLocalDate();
            double fee =rst.getDouble(10);
            temp.add(new Batch(batchNumber,batchName,courceBatchNo,startDate,endDate,nofStudent,description,courceNo,appCloseDa,fee));
        }
        if(temp.size()>0){
            return temp;
        }
        return null;
    }

    @Override
    public Batch find(String key) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM batch WHERE batchNumber =?");
        ResultSet rst = pst.executeQuery();
        Batch temp =null;
        while (rst.next()){
            String batchNumber = rst.getString(1);
            String batchName = rst.getString(2);
            String courceBatchNo = rst.getString(3);
            LocalDate startDate= rst.getDate(4).toLocalDate();
            LocalDate endDate= rst.getDate(5).toLocalDate();
            int nofStudent = rst.getInt(6);
            String description = rst.getString(7);
            String courceNo = rst.getString(8);
            LocalDate appCloseDa = rst.getDate(9).toLocalDate();
            double fee =rst.getDouble(10);
            new Batch(batchNumber,batchName,courceBatchNo,startDate,endDate,nofStudent,description,courceNo,appCloseDa,fee);
        }
        if(temp!=null){
            return temp;
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllCources() throws Exception {
        return null;
    }

    @Override
    public String getBatchNumber() throws Exception {
            Connection connection = DBConnection.getInstance();
            PreparedStatement pst = connection.prepareStatement("SELECT batchNumber FROM batch ORDER BY batchNumber DESC LIMIT 1");
            ResultSet rst = pst.executeQuery();
            while (rst.next()){
               return rst.getString(1);
            }

            return null;
    }

    @Override
    public String getCourceBatchNumber(String cource) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("SELECT courceBatchNo FROM batch where courceBatchNo like ?  ORDER BY courceBatchNo desc  limit 1 ");
        pst.setObject(1,cource);
        ResultSet rst = pst.executeQuery();
        while (rst.next()){
            return rst.getString(1);
        }
        return null;
    }


    @Override
    public List<String> getAviableBatches(String courceNumber) throws Exception {

            Connection connection =DBConnection.getInstance();
            PreparedStatement pst = connection.prepareStatement("SELECT courceBatchNo FROM batch   WHERE courceNo=? AND appCloseDay>=CURRENT_DATE ");
            pst.setObject(1,courceNumber);
            ResultSet rst = pst.executeQuery();
            ArrayList<String> list = new ArrayList<>();
            System.out.println("");
            while (rst.next()){
                list.add(rst.getString(1)) ;
            }
            if(list.size()>0){
                return list;
            }
            return null;

    }

    @Override
    public String getBatchNum(String batchNo) throws Exception {

            Connection  connection =  DBConnection.getInstance();
            PreparedStatement pst = connection.prepareStatement("SELECT batchNumber FROM batch WHERE courceBatchNo=?");
            pst.setObject(1,batchNo);
            ResultSet rst = pst.executeQuery();
            if(rst.next()){
                return rst.getString(1);
            }
            System.out.println("");
            return null;

    }

    @Override
    public int getMaxstudent(String code) throws Exception {
            Connection connection=DBConnection.getInstance();
            PreparedStatement pst = connection.prepareStatement("SELECT nofStudent FROM batch WHERE courceBatchNo=?");
            pst.setObject(1,code);
            ResultSet rst = pst.executeQuery();
            while (rst.next()){
                return rst.getInt(1);
            }
          return 0;

    }



}
