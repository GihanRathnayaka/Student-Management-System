package lk.ijs.studentregistration.dao;

import lk.ijs.studentregistration.db.DBConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;

public class CrudUtil {

    private CrudUtil(){}

    public static <T> T execute(String sql, Object... params) throws Exception {
        Connection connection = DBConnection.getInstance();
        PreparedStatement pstm = connection.prepareStatement(sql);
        int parametersCount = getParametersCount(sql);

        if (params.length != parametersCount) {
            throw new RuntimeException("Parameters count mismatched error");
        }

        for (int i = 0; i < parametersCount; i++) {
            pstm.setObject(i + 1, params[i]);
        }

        return sql.trim().startsWith("SELECT") ? (T) pstm.executeQuery() : (T) (Integer) pstm.executeUpdate();
    }

    private static int getParametersCount(String sql) {
        return sql.concat(" ").split("[?]").length - 1;
    }

}
