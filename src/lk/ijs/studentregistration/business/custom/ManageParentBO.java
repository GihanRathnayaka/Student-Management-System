package lk.ijs.studentregistration.business.custom;

import lk.ijs.studentregistration.business.SuperBO;
import lk.ijs.studentregistration.dto.ParentDTO;

import java.util.ArrayList;

public interface  ManageParentBO extends SuperBO {
    boolean saveParentDetails(ParentDTO parent) throws Exception;
    boolean updateParentDetails(ParentDTO parent) throws Exception;
    boolean deleteParentDetails(ParentDTO parent) throws Exception;
    ArrayList<ParentDTO>getAllParentDetails()throws Exception;
}

