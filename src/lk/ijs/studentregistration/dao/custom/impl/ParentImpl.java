package lk.ijs.studentregistration.dao.custom.impl;

import lk.ijs.studentregistration.dao.custom.ParentDAO;
import lk.ijs.studentregistration.db.DBConnection;
import lk.ijs.studentregistration.entity.Parent;
import lk.ijs.studentregistration.view.utill.ParentTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParentImpl implements ParentDAO {

    public static boolean studentSave(ParentTM parent) throws SQLException {
     return false;
    }

    @Override
    public boolean save(Parent entity) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst2 = connection.prepareStatement("INSERT INTO parent(name, mobile1, mobile2, email, designation, workingPlace, address, studentID) VALUES (?,?,?,?,?,?,?,?)");
        pst2.setObject(1,entity.getName());
        pst2.setObject(2,entity.getMobile1());
        pst2.setObject(3,entity.getMobile2());
        pst2.setObject(4,entity.getEmail());
        pst2.setObject(5,entity.getDesignation());
        pst2.setObject(6,entity.getWorkingPlace());
        pst2.setObject(7,entity.getAddress());
        pst2.setObject(8,entity.getStudentID());
        int result = pst2.executeUpdate();

        if(result>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Parent entity) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst2 = connection.prepareStatement(" UPDATE parent SET name=?, mobile1=?, mobile2=?, email=?, designation=?, workingPlace=?, address=? WHERE studentID=? ");
        pst2.setObject(1,entity.getName());
        pst2.setObject(2,entity.getMobile1());
        pst2.setObject(3,entity.getMobile2());
        pst2.setObject(4,entity.getEmail());
        pst2.setObject(5,entity.getDesignation());
        pst2.setObject(6,entity.getWorkingPlace());
        pst2.setObject(7,entity.getAddress());
        pst2.setObject(8,entity.getStudentID());
        int result = pst2.executeUpdate();

        if(result>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String key) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("DELETE FROM parent WHERE studentID =?");
        pst.setObject(1, key);
        int row = pst.executeUpdate();
        if(row>0){
            return true;
        }
        return false;
    }


    @Override
    public List<Parent> findAll() throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM parent");
        ResultSet rst = pst.executeQuery();
        ArrayList<Parent>temp =new ArrayList<>();
        while (rst.next()){
            int id =rst.getInt(1);
            String name=rst.getString(1);
            String mobile1 =rst.getString(2);
            String mobile2 =rst.getString(3);
            String email =rst.getString(4);
            String designation =rst.getString(5);
            String workingPlace =rst.getString(6);
            String address =rst.getString(7);
            String studentID =rst.getString(8);
            temp.add(new Parent(id,name,mobile1,mobile2,email,designation,workingPlace,address,studentID));
        }

        return temp;
    }

    @Override
    public Parent find(String key) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM parent WHERE studentID=?");
        pst.setObject(1,key);
        ResultSet rst = pst.executeQuery();
        Parent parent =null;
        while (rst.next()){
            int id =rst.getInt(1);
            String name=rst.getString(1);
            String mobile1 =rst.getString(2);
            String mobile2 =rst.getString(3);
            String email =rst.getString(4);
            String designation =rst.getString(5);
            String workingPlace =rst.getString(6);
            String address =rst.getString(7);
            String studentID =rst.getString(8);
            parent = new Parent(id,name,mobile1,mobile2,email,designation,workingPlace,address,studentID);
        }
        return parent;
    }
}
