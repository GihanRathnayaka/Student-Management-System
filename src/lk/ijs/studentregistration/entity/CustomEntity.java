package lk.ijs.studentregistration.entity;

import java.time.LocalDate;

public class CustomEntity {
       private String batchNumber ;
       private String batchName;
       private String name;
       private String courceBatchNo;
       private LocalDate startDate;
       private LocalDate endDate;
       private int nofStudent;
       private double fee;
       private LocalDate appCloseDay;
       private String description;

    public CustomEntity() {
    }

    public CustomEntity(String batchNumber, String batchName, String name, String courceBatchNo, LocalDate startDate, LocalDate endDate, int nofStudent, double fee, LocalDate appCloseDay, String description) {
        this.batchNumber = batchNumber;
        this.batchName = batchName;
        this.name = name;
        this.courceBatchNo = courceBatchNo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nofStudent = nofStudent;
        this.fee = fee;
        this.appCloseDay = appCloseDay;
        this.setDescription(description);
    }


    private String courseCode;
    private String courseName;
    private String courseDuration;
    private LocalDate regDate;

    public CustomEntity(String batchNumber, String batchName, String courceBatchNo, String courseCode, String courseName, String courseDuration, LocalDate regDate) {
        this.batchNumber = batchNumber;
        this.batchName = batchName;
        this.courceBatchNo = courceBatchNo;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.regDate = regDate;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourceBatchNo() {
        return courceBatchNo;
    }

    public void setCourceBatchNo(String courceBatchNo) {
        this.courceBatchNo = courceBatchNo;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getNofStudent() {
        return nofStudent;
    }

    public void setNofStudent(int nofStudent) {
        this.nofStudent = nofStudent;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public LocalDate getAppCloseDay() {
        return appCloseDay;
    }

    public void setAppCloseDay(LocalDate appCloseDay) {
        this.appCloseDay = appCloseDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }
}
