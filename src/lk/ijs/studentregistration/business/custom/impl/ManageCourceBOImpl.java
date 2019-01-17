package lk.ijs.studentregistration.business.custom.impl;

import lk.ijs.studentregistration.business.Converter;
import lk.ijs.studentregistration.business.custom.ManageCourceBO;
import lk.ijs.studentregistration.dao.DAOFactoty;
import lk.ijs.studentregistration.dao.custom.CourceDAO;
import lk.ijs.studentregistration.dto.CourceDTO;
import lk.ijs.studentregistration.entity.Course;


import java.util.List;

public class ManageCourceBOImpl implements ManageCourceBO {

    private static CourceDAO courseFactory =  DAOFactoty.getInstance().daoType(DAOFactoty.DAOType.COURSE);

    public String getCourceNumber() throws Exception {
            String txtPart = courseFactory.setCourceNumber();
            if (!txtPart.equals(null)) {
                return "CNO" + (1 + Integer.parseInt(txtPart.substring(3)));
            }
            return "CNO1";

    }

    public List<CourceDTO> getAllCources() throws Exception {

        List<Course> all = courseFactory.findAll();
        if(all==null){return null;}
        return Converter.getDTOList(all);
    }

    public  boolean createCource(CourceDTO cource) throws Exception {
         Course c = Converter.getEntity(cource);
         return courseFactory.save(c);

    }


    public  boolean updateCource(CourceDTO cource) throws Exception {
       return courseFactory.update(Converter.getEntity(cource));

    }

    public  boolean deleteCource(String text) throws Exception {
        return courseFactory.delete(text);
    }
}