package lk.ijs.studentregistration.dao.custom.impl;

import lk.ijs.studentregistration.dao.custom.QuaryDAO;
import lk.ijs.studentregistration.db.DBConnection;
import lk.ijs.studentregistration.dto.BatchDTO;
import lk.ijs.studentregistration.entity.CustomEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QuaryDAOImpl implements QuaryDAO {


    @Override
    public ArrayList<CustomEntity> select() throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("\n" +
                "SELECT b.batchNumber ,b.batchName,c.name,b.courceBatchNo , b.startDate,b.endDate,b.nofStudent,b.fee,b.appCloseDay,b.description FROM batch b\n" +
                "INNER JOIN cource c on b.courceNo = c.courceNo");
        ResultSet rst = pst.executeQuery();

        List<CustomEntity> temp = new ArrayList<>();

        while (rst.next()) {
            temp.add( new CustomEntity(rst.getString(1), rst.getString(2), rst.getString(3),
                    rst.getString(4), rst.getDate(5).toLocalDate(), rst.getDate(6).toLocalDate(),
                    rst.getInt(7), rst.getDouble(8), rst.getDate(9).toLocalDate(), rst.getString(10)));

        }
        return (ArrayList<CustomEntity>) temp;

    }

    @Override
    public ArrayList<CustomEntity> getStudentRegistrationInformtion(String id) throws Exception {
        ArrayList<CustomEntity>list = new ArrayList<>();
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("SELECT b.batchNumber,b.batchName,b.courceBatchNo, c.courceNo,c.name,c.duration,regDate FROM batchregistration r INNER JOIN batch b on r.courceBatchNo = b.courceBatchNo\n" +
                "INNER JOIN cource c on b.courceNo = c.courceNo WHERE r.studentId=?");
        pst.setObject(1,id);
        ResultSet rst = pst.executeQuery();
        while (rst.next()){
            list.add(new CustomEntity(rst.getString(1),rst.getString(2)
                    ,rst.getString(3),rst.getString(4),rst.getString(5)
                    ,rst.getString(6),rst.getDate(7).toLocalDate()));
        }
        return list;
    }

    @Override
    public ArrayList<CustomEntity> searchStudentRegistrationInformtion(String id) throws Exception {
        ArrayList<CustomEntity>list = new ArrayList<>();
        Connection connection = DBConnection.getInstance();
        PreparedStatement pst = connection.prepareStatement("SELECT b.batchNumber,b.batchName,b.courceBatchNo, c.courceNo,c.name,c.duration,regDate FROM batchregistration r INNER JOIN batch b on r.courceBatchNo = b.courceBatchNo\n" +
                "INNER JOIN cource c on b.courceNo = c.courceNo WHERE r.studentId like ? ");
        pst.setObject(1,id);
        ResultSet rst = pst.executeQuery();
        while (rst.next()){
            list.add(new CustomEntity(rst.getString(1),rst.getString(2)
                    ,rst.getString(3),rst.getString(4),rst.getString(5)
                    ,rst.getString(6),rst.getDate(7).toLocalDate()));
        }
        return list;

    }


}