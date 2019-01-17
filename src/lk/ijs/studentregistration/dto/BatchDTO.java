package lk.ijs.studentregistration.dto;

import java.time.LocalDate;

public class BatchDTO implements SuperDTO{
    private String batchNumber;
    private String batchName;
    private String courceNumber;
    private String CourcebatchNumber;
    private LocalDate startDate;
    private LocalDate EndDate;
    private int NumberOfStudent;
    private double fee;
    private LocalDate appCloseDate;
    private String description;

    public BatchDTO() {
    }

    public BatchDTO(String batchNumber, String batchName, String courceNumber, String courcebatchNumber, LocalDate startDate, LocalDate endDate, int numberOfStudent, double fee, LocalDate appCloseDate, String description) {
        this.setBatchNumber(batchNumber);
        this.setBatchName(batchName);
        this.setCourceNumber(courceNumber);
        setCourcebatchNumber(courcebatchNumber);
        this.setStartDate(startDate);
        setEndDate(endDate);
        setNumberOfStudent(numberOfStudent);
        this.setFee(fee);
        this.setAppCloseDate(appCloseDate);
        this.setDescription(description);
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

    public String getCourceNumber() {
        return courceNumber;
    }

    public void setCourceNumber(String courceNumber) {
        this.courceNumber = courceNumber;
    }

    public String getCourcebatchNumber() {
        return CourcebatchNumber;
    }

    public void setCourcebatchNumber(String courcebatchNumber) {
        CourcebatchNumber = courcebatchNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return EndDate;
    }

    public void setEndDate(LocalDate endDate) {
        EndDate = endDate;
    }

    public int getNumberOfStudent() {
        return NumberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        NumberOfStudent = numberOfStudent;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BatchDTO{" +
                "batchNumber='" + getBatchNumber() + '\'' +
                ", batchName='" + getBatchName() + '\'' +
                ", courceNumber='" + getCourceNumber() + '\'' +
                ", CourcebatchNumber='" + getCourcebatchNumber() + '\'' +
                ", startDate=" + getStartDate() +
                ", EndDate=" + getEndDate() +
                ", NumberOfStudent=" + getNumberOfStudent() +
                ", fee=" + getFee() +
                ", appCloseDate=" + getAppCloseDate() +
                ", description='" + getDescription() + '\'' +
                '}';
    }

    public LocalDate getAppCloseDate() {
        return appCloseDate;
    }

    public void setAppCloseDate(LocalDate appCloseDate) {
        this.appCloseDate = appCloseDate;
    }
}
