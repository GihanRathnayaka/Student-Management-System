package lk.ijs.studentregistration.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CurdDAO<T,ID> extends SuperDAO{

    boolean save(T entity) throws Exception;
    boolean update(T entity) throws Exception;
    boolean delete(ID key) throws Exception;
    List<T> findAll() throws Exception;
    T find(ID key) throws Exception;

}
