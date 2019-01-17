package lk.ijs.studentregistration.view.utill;

import java.time.LocalDate;

public class BatchesTM {

    private String batchNumber;
    private String batchName;
    private String courceName;
    private String CourcebatchNumber;
    private LocalDate startDate;
    private LocalDate EndDate;
    private int NumberOfStudent;
    private double fee;
    private LocalDate appCloseDate;
    private String description;

    public BatchesTM() {
    }

    public BatchesTM(String batchNumber, String batchName, String courceName, String courcebatchNumber, LocalDate startDate, LocalDate endDate, int numberOfStudent, double fee, LocalDate appCloseDate, String description) {
        this.batchNumber = batchNumber;
        this.batchName = batchName;
        this.courceName = courceName;
        CourcebatchNumber = courcebatchNumber;
        this.startDate = startDate;
        EndDate = endDate;
        NumberOfStudent = numberOfStudent;
        this.fee = fee;
        this.appCloseDate = appCloseDate;
        this.description = description;
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

    public String getCourceName() {
        return courceName;
    }

    public void setCourceName(String courceName) {
        this.courceName = courceName;
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

    public LocalDate getAppCloseDate() {
        return appCloseDate;
    }

    public void setAppCloseDate(LocalDate appCloseDate) {
        this.appCloseDate = appCloseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BatchesTM{" +
                "batchNumber='" + batchNumber + '\'' +
                ", batchName='" + batchName + '\'' +
                ", courceName='" + courceName + '\'' +
                ", CourcebatchNumber='" + CourcebatchNumber + '\'' +
                ", startDate=" + startDate +
                ", EndDate=" + EndDate +
                ", NumberOfStudent=" + NumberOfStudent +
                ", fee=" + fee +
                ", appCloseDate=" + appCloseDate +
                ", description='" + description + '\'' +
                '}';
    }
}
