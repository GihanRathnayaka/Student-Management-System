package lk.ijs.studentregistration.entity;

import java.time.LocalDate;

public class Batch extends SuperEntity {
    private String batchNumber;
    private String batchName;
    private String courceBatchNo;
    private LocalDate startDate;
    private LocalDate endDate;
    private int nofStudent;
    private String description;
    private String courceNo;
    private LocalDate appCloseDay;
    private double fee;

    public Batch() {
    }

    public Batch(String batchNumber, String batchName, String courceBatchNo, LocalDate startDate, LocalDate endDate, int nofStudent, String description, String courceNo, LocalDate appCloseDay, double fee) {
        this.batchNumber = batchNumber;
        this.batchName = batchName;
        this.courceBatchNo = courceBatchNo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nofStudent = nofStudent;
        this.description = description;
        this.courceNo = courceNo;
        this.appCloseDay = appCloseDay;
        this.fee = fee;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourceNo() {
        return courceNo;
    }

    public void setCourceNo(String courceNo) {
        this.courceNo = courceNo;
    }

    public LocalDate getAppCloseDay() {
        return appCloseDay;
    }

    public void setAppCloseDay(LocalDate appCloseDay) {
        this.appCloseDay = appCloseDay;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "BatchDTO{" +
                "batchNumber='" + batchNumber + '\'' +
                ", batchName='" + batchName + '\'' +
                ", courceBatchNo='" + courceBatchNo + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", nofStudent=" + nofStudent +
                ", description='" + description + '\'' +
                ", courceNo='" + courceNo + '\'' +
                ", appCloseDay=" + appCloseDay +
                ", fee=" + fee +
                '}';
    }
}
