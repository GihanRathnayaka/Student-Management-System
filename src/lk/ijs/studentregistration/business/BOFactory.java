package lk.ijs.studentregistration.business;

import lk.ijs.studentregistration.business.custom.impl.ManageBatchesBOImpl;
import lk.ijs.studentregistration.business.custom.impl.ManageCourceBOImpl;
import lk.ijs.studentregistration.business.custom.impl.ManageParentBOImpl;
import lk.ijs.studentregistration.business.custom.impl.ManageStudentRegistrationBOImpl;

public class BOFactory {

    public enum daoType{
        MANAGEBATCH,MANAGECOURSE,MANAGESTUDENT,STUDENTBATCH,MANAGEPARENT
    }
    private static BOFactory boFactory;
    private BOFactory(){
    }

    public static BOFactory getInstance(){
        if(boFactory==null){
            boFactory= new BOFactory();
        }
        return boFactory;
    }

    public <T extends SuperBO>T getBO(daoType daoType){
        switch (daoType){
            case MANAGEBATCH:return (T) new ManageBatchesBOImpl();
            case MANAGECOURSE:return (T) new ManageCourceBOImpl();
            case MANAGEPARENT: return  (T) new ManageParentBOImpl();
            case MANAGESTUDENT:return (T) new ManageStudentRegistrationBOImpl();
            //case MANAGESTUDENT:return (T) new ManageStudentRegistrationBOImpl();
            default:return null;
        }
    }

}
