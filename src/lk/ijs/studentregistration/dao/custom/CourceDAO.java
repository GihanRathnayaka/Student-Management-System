package lk.ijs.studentregistration.dao.custom;

import lk.ijs.studentregistration.dao.CurdDAO;
import lk.ijs.studentregistration.entity.Course;

import java.util.ArrayList;
import java.util.List;

public interface CourceDAO extends CurdDAO<Course,String> {
     String getCourceNumber(String name)throws Exception;
     String setCourceNumber()throws Exception;
     List<String> getAllCources() throws Exception;
}
