package lk.ijs.studentregistration.dao.custom.impl;

import lk.ijs.studentregistration.dao.custom.StudentBacthDAO;
import lk.ijs.studentregistration.db.DBConnection;
import lk.ijs.studentregistration.entity.BatchRegistration;
import lk.ijs.studentregistration.view.utill.RegistrationInfoTM;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentBacthImpl implements StudentBacthDAO {
    public static boolean studentBatch(RegistrationInfoTM infoTM) throws SQLException {

        return false;

    }

    @Override
    public boolean save(BatchRegistration entity) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("INSERT INTO batchregistration(studentId, batchNumber, courceBatchNo, regDate) VALUES (?,?,?,?)");
        pst.setObject(1,entity.getStudentID());
        pst.setObject(2,entity.getBatchNumber());
        pst.setObject(3,entity.getCourseBatchNo());
        pst.setObject(4,entity.getRegDate());
        int num = pst.executeUpdate();
        if(num>0){ return true;}
        return false;
    }

    @Override
    public boolean update(BatchRegistration entity) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("UPDATE  batchregistration SET  courceBatchNo=?, regDate WHERE studentId=? AND batchNumber=?");
        pst.setObject(3,entity.getStudentID());
        pst.setObject(4,entity.getBatchNumber());
        pst.setObject(1,entity.getCourseBatchNo());
        pst.setObject(2,entity.getRegDate());
        int num = pst.executeUpdate();
        if(num>0){ return true;}
        return false;

    }

    @Override
    public boolean delete(String key) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("DELETE FROM batchregistration where id =?");
        pst.setObject(1,key);
        int result = pst.executeUpdate();
        if(result>0){
            return true;
        }
        return false;
    }

    @Override
    public List<BatchRegistration> findAll() throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM batchregistration");
        ArrayList<BatchRegistration> temp = new ArrayList<>();
        ResultSet rst = pst.executeQuery();
        //, , ,
        while (rst.next()){
            int id =rst.getInt(1);
            String studentId =rst.getString(2);
            String batchNumber =rst.getString(3);
            String courceBatchNo =rst.getString(4);
            LocalDate regDate =rst.getDate(5).toLocalDate();
            temp.add( new BatchRegistration(id,studentId,batchNumber,courceBatchNo,regDate));
        }
       return temp;
    }

    @Override
    public BatchRegistration find(String key) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM batchregistration WHERE id=? ");
        pst.setObject(1,key);
        BatchRegistration temp = null;
        ResultSet rst = pst.executeQuery();
        while (rst.next()){
            int id =rst.getInt(1);
            String studentId =rst.getString(2);
            String batchNumber =rst.getString(3);
            String courceBatchNo =rst.getString(4);
            LocalDate regDate =rst.getDate(5).toLocalDate();
            temp= new BatchRegistration(id,studentId,batchNumber,courceBatchNo,regDate);
        }
        return temp;
    }

    @Override
    public int getRegisterdStudent(String courseBatchNo) throws Exception {

            Connection connection =DBConnection.getInstance();
            PreparedStatement pst = connection.prepareStatement("SELECT count(courceBatchNo) FROM batchregistration WHERE courceBatchNo=?");
            pst.setObject(1,courseBatchNo);
            ResultSet rst = pst.executeQuery();
            while (rst.next()){
                return rst.getInt(1);
            }
        return 0;
    }
}
