package lk.ijs.studentregistration.business;

import lk.ijs.studentregistration.dto.*;
import lk.ijs.studentregistration.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public static Batch get(BatchDTO dto){
        return new Batch(dto.getBatchNumber(),dto.getBatchName(),
                dto.getCourcebatchNumber(),dto.getStartDate(),
                dto.getEndDate(),dto.getNumberOfStudent(),dto.getDescription(),
                dto.getCourceNumber(),dto.getAppCloseDate(),dto.getFee());
    }

    public static ArrayList<BatchDTO>list(ArrayList<Batch> batch){
        ArrayList<BatchDTO> list = new ArrayList<>();
        for ( Batch dto:batch) {
            list.add(new BatchDTO(dto.getBatchNumber(),dto.getBatchName(),
                    dto.getCourceBatchNo(),dto.getCourceBatchNo(),
                    dto.getStartDate(),dto.getEndDate(),dto.getNofStudent(),dto.getFee(),dto.getAppCloseDay(),dto.getDescription()));
        }
        return list;
    }


    public static Parent ParentDTOConvert(ParentDTO p){
      return new Parent(1,p.getName(),p.getMobile1(),p.getMobile2(),p.getEmail(),p.getDesignation(),p.getDesignation(),p.getAddress(),p.getStudentID());
    }





    public static <T extends SuperEntity> T getEntity(SuperDTO dto) {
        if (dto instanceof StudentDTO) {
            StudentDTO s = (StudentDTO) dto;
            return (T) new Student(s.getStudentID(), s.getNameWI(), s.getFullName(),s.getAddress(),s.getEmail(),s.getBod(),s.getNic(),
                    s.getMobile(),s.getPhone(), s.getGen(),s.getEduQ(),s.getSchool(),s.getUniversity(),s.getFaculty(),s.getCity());
        } else if (dto instanceof ParentDTO) {
            ParentDTO p = (ParentDTO) dto;
            return (T) new Parent(1,p.getName(),p.getMobile1(),p.getMobile2(),p.getEmail(),p.getDesignation(),p.getDesignation(),p.getAddress(),p.getStudentID());
        }else if (dto instanceof EducationDTO) {
            EducationDTO q = (EducationDTO) dto;
            return (T) new Qualification(1, q.getQualification(), q.getInistution(), q.getIsuseDate(), q.getSpecification(), q.getDescription(), q.getStudentID());
        }else if (dto instanceof RegistrationInfoDTO) {
            RegistrationInfoDTO r = (RegistrationInfoDTO) dto;
            return (T) new BatchRegistration(1,r.getStudentId(),r.getBatchCode(),r.getBatchName(),r.getRegDate()) ;
        }
        else if (dto instanceof BatchDTO) {
           BatchDTO b = (BatchDTO) dto;
            return (T) new Batch(b.getBatchNumber(),b.getBatchName(),b.getCourcebatchNumber(),b.getStartDate(),b.getEndDate(),b.getNumberOfStudent(),b.getDescription(),b.getCourceNumber(),b.getAppCloseDate(),b.getFee()) ;
        } else if (dto instanceof CourceDTO) {
            CourceDTO c = (CourceDTO) dto;
            return (T) new Course(c.getCourceID(),c.getRegdate(),c.getName(),c.getDescription(),c.getDuration()) ;
        }
        else {//String courceNo, LocalDate startDate, String name, String description, String duration
            throw new RuntimeException("This DTO can't be converted to an entity");
        }
    }



    public static <T extends SuperEntity> List<T> getEntityList(List<? extends SuperDTO> dtos) {
        return dtos.stream().map(Converter::<T>getEntity).collect(Collectors.toList());

    }
    public static <T extends SuperDTO> List<T> getDTOList(List<? extends SuperEntity> entities) {
        return entities.stream().map(Converter::<T>getDTO).collect(Collectors.toList());
    }

    public static <T extends SuperDTO> T getDTO(SuperEntity entity) {
        if (entity instanceof Course) {
            Course c = (Course) entity;
            return (T) new CourceDTO(c.getCourceNo(),c.getStartDate(),c.getName(),c.getDescription(),c.getDuration());
        } else if (entity instanceof Student) {
            Student s = (Student) entity;
            return (T) new StudentDTO(s.getStudentID(),s.getNameWI(),s.getFullName(),s.getAddress(),s.getEmail(),s.getBod(),
                    s.getNic(),s.getPhone(),s.getMobile(),s.getGen(),s.getEduQ(),s.getSchool(),
                    s.getUniversity(),s.getFaculty(),s.getCity());

        } else {
            throw new RuntimeException("This entity can't be converted to a DTO");
        }
    }
    //studentID,nameWI,fullName,address,email,bod,phone,mobile,gen,eduQ,school,faculty,university,nic,city
}
