package lk.ijs.studentregistration.dao.custom.impl;

import lk.ijs.studentregistration.dao.custom.CourceDAO;
import lk.ijs.studentregistration.db.DBConnection;
import lk.ijs.studentregistration.entity.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourceDAOImpl implements CourceDAO {
    @Override
    public boolean save(Course entity) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("INSERT INTO cource VALUES (?,?,?,?,?)");
        pst.setObject(1,entity.getCourceNo());
        pst.setObject(2,entity.getStartDate());
        pst.setObject(3,entity.getName());
        pst.setObject(4,entity.getDescription());
        pst.setObject(5,entity.getDuration());
        if(pst.executeUpdate()>0){
            return true;
        }
        return false;

//        return CrudUtil.<Integer>execute("INSERT INTO Customer VALUES (?,?,?,?,?)",
//                entity.getCourceNo(), entity.getStartDate(),
//                entity.getName(),entity.getDescription(),entity.getDuration()) > 0;



    }

    @Override
    public boolean update(Course entity) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("UPDATE cource SET  startDate=?, name=?, description=?, duration=? WHERE courceNo =? ");
        pst.setObject(5,entity.getCourceNo());
        pst.setObject(1,entity.getStartDate());
        pst.setObject(2,entity.getName());
        pst.setObject(3,entity.getDescription());
        pst.setObject(4,entity.getDuration());
        if(pst.executeUpdate()>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String key) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("DELETE  FROM cource WHERE courceNo=?");
        pst.setObject(1,key);
        if(pst.executeUpdate()>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Course> findAll() throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM cource");
        ResultSet rst = pst.executeQuery();
        List<Course>temp = new ArrayList<>();
        while (rst.next()){
          String courceNo   =rst.getString(1);
          LocalDate startDate =rst.getDate(2).toLocalDate();
          String name =rst.getString(3);
          String description =rst.getString(4);
          String duration =rst.getString(5);
          temp.add(new Course(courceNo,startDate,name,description,duration));
        }
       if(temp.size()>0){
           return temp;
       }
       return null;
    }

    @Override
    public Course find(String key) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM cource");
        ResultSet rst = pst.executeQuery();
        Course temp = null;
        while (rst.next()){
            String courceNo   =rst.getString(1);
            LocalDate startDate =rst.getDate(2).toLocalDate();
            String name =rst.getString(3);
            String description =rst.getString(4);
            String duration =rst.getString(5);
            temp =new Course(courceNo,startDate,name,description,duration);
        }
        if(temp!=null){
            return temp;
        }
        return null;
    }

    @Override
    public String getCourceNumber(String name) throws Exception {

            Connection connection = DBConnection.getInstance();
            PreparedStatement pst = connection.prepareStatement("SELECT courceNo FROM cource WHERE name=? ");
            pst.setObject(1,name);
            ResultSet rst = pst.executeQuery();
            while (rst.next()){
                return rst.getString(1);
            }
            return null;

    }

    @Override
    public String setCourceNumber() throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("SELECT courceNo FROM cource ORDER  BY  courceNo DESC limit 1  ");
        ResultSet rst = pst.executeQuery();
        while (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public List<String> getAllCources() throws Exception {
            Connection connection= DBConnection.getInstance();
            PreparedStatement pst = connection.prepareStatement("SELECT courceNo,name FROM cource");
            ResultSet rst = pst.executeQuery();
            ArrayList<String> tms = new ArrayList<>();
            while (rst.next()){
                tms.add(rst.getString(2));
            }
            if(tms.size()>0){
                return tms;
            }

        return null;
    }
}
