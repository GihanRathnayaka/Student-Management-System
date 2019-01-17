package lk.ijs.studentregistration.business.custom;

import lk.ijs.studentregistration.business.SuperBO;
import lk.ijs.studentregistration.dto.CourceDTO;

import java.util.List;

public interface ManageCourceBO extends SuperBO {
    String getCourceNumber() throws Exception;
    List<CourceDTO> getAllCources() throws Exception;
    boolean createCource(CourceDTO cource)throws Exception;
    boolean updateCource(CourceDTO cource)throws Exception;
    boolean deleteCource(String text)throws Exception;
}
