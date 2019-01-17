package lk.ijs.studentregistration.dao.custom.impl;

import lk.ijs.studentregistration.dao.custom.StudentRegistrationDAO;
import lk.ijs.studentregistration.db.DBConnection;
import lk.ijs.studentregistration.dto.StudentDTO;
import lk.ijs.studentregistration.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentRegistrationDAOImpl implements StudentRegistrationDAO {

    public static String getStudentID() throws Exception {
            Connection connection = DBConnection.getInstance();
            PreparedStatement pst = connection.prepareStatement("SELECT studentID FROM studentifo ORDER BY  studentID DESC  limit 1");
            ResultSet rst = pst.executeQuery();
            while (rst.next()){
              return rst.getString(1) ;
            }

            return null;
    }



    @Override
    public boolean save(Student entity) throws Exception {
        Connection connection =DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("INSERT INTO studentifo VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pst.setObject(1,entity.getStudentID());
        pst.setObject(2,entity.getNameWI());
        pst.setObject(3,entity.getFullName());
        pst.setObject(4,entity.getAddress());
        pst.setObject(5,entity.getEmail());
        pst.setObject(6,entity.getBod());
        pst.setObject(7,entity.getNic());
        pst.setObject(8,entity.getMobile());
        pst.setObject(9,entity.getPhone());
        pst.setObject(10,entity.getGen());
        pst.setObject(11,entity.getEduQ());
        pst.setObject(12,entity.getSchool());
        pst.setObject(13,entity.getUniversity());
        pst.setObject(14,entity.getFaculty());
        pst.setObject(15,entity.getCity());
        int rst = pst.executeUpdate();
        System.out.println("");
        if(rst>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Student entity) throws Exception {

        Connection connection =DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("UPDATE studentifo set  nameWI=?, fullName=?, address=?, email=?, bod=?, nic=?, mobile=?, phone=?, gen=?, eduQ=?, school=?, university=?, faculty=?, city=? WHERE studentID=? ");
        pst.setObject(1,entity.getNameWI());
        pst.setObject(2,entity.getFullName());
        pst.setObject(3,entity.getAddress());
        pst.setObject(4,entity.getEmail());
        pst.setObject(5,entity.getBod());
        pst.setObject(6,entity.getNic());
        pst.setObject(7,entity.getMobile());
        pst.setObject(8,entity.getPhone());
        pst.setObject(9,entity.getGen());
        pst.setObject(10,entity.getEduQ());
        pst.setObject(11,entity.getSchool());
        pst.setObject(12,entity.getUniversity());
        pst.setObject(13,entity.getFaculty());
        pst.setObject(14,entity.getCity());
        pst.setObject(15,entity.getStudentID());
        int rst = pst.executeUpdate();
        System.out.println("");
        if(rst>0){
            return true;
        }
        return false;

    }

    @Override
    public boolean delete(String key) throws Exception {
        Connection connection =DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("DELETE FROM studentifo WHERE studentID=?");
        pst.setObject(1,key);
        int result = pst.executeUpdate();
        if(result>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Student> findAll() throws Exception {
        Connection connection =DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM studentifo");
        ResultSet rst = pst.executeQuery();
        ArrayList<Student>temp =new ArrayList<>();
        while (rst.next()){
            String studentID=rst.getString(1);
            String nameWI=rst.getString(2);
            String fullName=rst.getString(3);
            String address=rst.getString(4);
            String email=rst.getString(5);
            LocalDate bod =rst.getDate(6).toLocalDate();
            String nic =rst.getString(7);
            String mobile =rst.getString(8);
            String phone =rst.getString(9);
            String gen=rst.getString(10);
            String eduQ=rst.getString(11);
            String school=rst.getString(12);
            String university =rst.getString(13);
            String faculty =rst.getString(14);
            String city =rst.getString(15);
            temp.add(new Student(studentID,nameWI,fullName,address,email,bod,nic,mobile,phone,gen,eduQ,school,university,faculty,city) )  ;
        }
            if(temp.size()>0){
                return temp;
            }
// ,
        return null;
    }

    @Override
    public Student find(String key) throws Exception {

        Connection connection =DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM studentifo where studentID=?");
        pst.setObject(1,key);
        ResultSet rst = pst.executeQuery();
        Student temp =null;
        while (rst.next()){
            String studentID=rst.getString(1);
            String nameWI=rst.getString(2);
            String fullName=rst.getString(3);
            String address=rst.getString(4);
            String email=rst.getString(5);
            LocalDate bod =rst.getDate(6).toLocalDate();
            String nic =rst.getString(7);
            String mobile =rst.getString(8);
            String phone =rst.getString(9);
            String gen=rst.getString(10);
            String eduQ=rst.getString(11);
            String school=rst.getString(12);
            String university =rst.getString(13);
            String faculty =rst.getString(14);
            String city =rst.getString(15);
            temp=new Student(studentID,nameWI,fullName,address,email,bod,nic,mobile,phone,gen,eduQ,school,university,faculty,city) ;
        }
        if(temp!=null){
            return temp;
        }

        return null;
    }

    @Override
    public StudentDTO getStudentProfile(String studentID) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Student> searchStudentProfile(String ID) throws Exception {
        Connection connection =DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM studentifo WHERE studentID like ?");
        pst.setObject(1,ID);
        ResultSet rst = pst.executeQuery();
        ArrayList<Student>temp =new ArrayList<>();
        while (rst.next()){
            String studentID=rst.getString(1);
            String nameWI=rst.getString(2);
            String fullName=rst.getString(3);
            String address=rst.getString(4);
            String email=rst.getString(5);
            LocalDate bod =rst.getDate(6).toLocalDate();
            String nic =rst.getString(7);
            String mobile =rst.getString(8);
            String phone =rst.getString(9);
            String gen=rst.getString(10);
            String eduQ=rst.getString(11);
            String school=rst.getString(12);
            String university =rst.getString(13);
            String faculty =rst.getString(14);
            String city =rst.getString(15);
            temp.add(new Student(studentID,nameWI,fullName,address,email,bod,nic,mobile,phone,gen,eduQ,school,university,faculty,city) )  ;
        }
        if(temp.size()>0){
            return temp;
        }

        return null;
    }
}
