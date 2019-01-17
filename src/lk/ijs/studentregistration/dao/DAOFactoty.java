package lk.ijs.studentregistration.dao;

import lk.ijs.studentregistration.dao.custom.impl.*;

public class DAOFactoty {

    private static  DAOFactoty daoFactoty;

    public  enum DAOType{
        STUDENT,PARENT,Qualification,BATCHSTUDENT,COURSE,BATCH,QUARY
    }

   private DAOFactoty(){
   }

   public static DAOFactoty getInstance(){
        if(daoFactoty==null){
           daoFactoty= new DAOFactoty();
        }
        return daoFactoty;
   }

   public <T extends SuperDAO>T daoType(DAOType daoType){

        switch (daoType){
            case STUDENT: return (T) new StudentRegistrationDAOImpl();
            case PARENT: return (T) new ParentImpl();
            case Qualification: return (T) new QualificationImpl();
            case BATCHSTUDENT: return (T) new StudentBacthImpl();
            case COURSE:return (T) new CourceDAOImpl();
            case BATCH:return (T) new BatchDAOImpl();
            case QUARY:return (T) new QuaryDAOImpl();
            default: return  null;
        }
   }


}
