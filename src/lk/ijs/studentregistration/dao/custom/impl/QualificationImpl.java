package lk.ijs.studentregistration.dao.custom.impl;

import lk.ijs.studentregistration.dao.custom.QualificationDAO;
import lk.ijs.studentregistration.db.DBConnection;
import lk.ijs.studentregistration.entity.Qualification;
import lk.ijs.studentregistration.view.utill.EducationTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QualificationImpl implements QualificationDAO {

    public static boolean saveRegistration(EducationTM education) throws SQLException {
       return false;
    }

    @Override
    public boolean save(Qualification entity) throws Exception {
        Connection connection =DBConnection.getInstance();
        PreparedStatement pst4 = connection.prepareStatement("INSERT INTO qualification(Qualification, inistution, isuseDate, specification, description, studentID) VALUES (?,?,?,?,?,?)");
        pst4.setObject(1,entity.getQualification());
        pst4.setObject(2,entity.getInistution());
        pst4.setObject(3,entity.getIsuseDate());
        pst4.setObject(4,entity.getSpecification());
        pst4.setObject(5,entity.getDescription());
        pst4.setObject(6,entity.getStudentID());
        int n = pst4.executeUpdate();
        if(n>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Qualification entity) throws Exception {
        Connection connection =DBConnection.getInstance();
        PreparedStatement pst4 = connection.prepareStatement("UPDATE qualification SET Qualification=?, inistution=?, isuseDate=?, specification=?, description=? WHERE studentID=? ");
        pst4.setObject(1,entity.getQualification());
        pst4.setObject(2,entity.getInistution());
        pst4.setObject(3,entity.getIsuseDate());
        pst4.setObject(4,entity.getSpecification());
        pst4.setObject(5,entity.getDescription());
        pst4.setObject(6,entity.getStudentID());
        int n = pst4.executeUpdate();
        if(n>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String key) throws Exception {
        Connection connection =DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("DELETE FROM qualification WHERE studentID =?");
        pst.setObject(1,key);
        int result = pst.executeUpdate();
        if(result>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Qualification> findAll() throws Exception {
        Connection connection =DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM qualification");
        ArrayList<Qualification> temp = new ArrayList<>();
        ResultSet rst = pst.executeQuery();
        while (rst.next()){
            int id =rst.getInt(1);
            String qualification = rst.getString(2);
            String inistution = rst.getString(2);
            LocalDate isuseDate = rst.getDate(3).toLocalDate();
            String specification= rst.getString(4);
            String description = rst.getString(5);
            String studentID = rst.getString(6);
            temp.add(new Qualification(id,qualification,inistution,isuseDate,specification,description,studentID));
        }
        return temp;
    }

    @Override
    public Qualification find(String key) throws Exception {
        Connection connection =DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM qualification WHERE studentID=?");
        pst.setObject(1,key);
        Qualification qual =null;
        ResultSet rst = pst.executeQuery();
        while (rst.next()){
            int id =rst.getInt(1);
            String qualification = rst.getString(2);
            String inistution = rst.getString(2);
            LocalDate isuseDate = rst.getDate(3).toLocalDate();
            String specification= rst.getString(4);
            String description = rst.getString(5);
            String studentID = rst.getString(6);
            qual = new Qualification(id,qualification,inistution,isuseDate,specification,description,studentID);
        }

        return qual;
    }
}
