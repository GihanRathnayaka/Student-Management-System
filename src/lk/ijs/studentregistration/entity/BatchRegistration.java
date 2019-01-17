package lk.ijs.studentregistration.entity;

import java.time.LocalDate;

public class BatchRegistration extends SuperEntity {

    private int id;
    private String studentID;
    private String batchNumber;
    private String courseBatchNo;
    private LocalDate regDate;

    public BatchRegistration() {
    }

    public BatchRegistration(int id, String studentID, String batchNumber, String courseBatchNo, LocalDate regDate) {
        this.id = id;
        this.studentID = studentID;
        this.batchNumber = batchNumber;
        this.courseBatchNo = courseBatchNo;
        this.regDate = regDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getCourseBatchNo() {
        return courseBatchNo;
    }

    public void setCourseBatchNo(String courseBatchNo) {
        this.courseBatchNo = courseBatchNo;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "BatchRegistration{" +
                "id=" + id +
                ", studentID='" + studentID + '\'' +
                ", batchNumber='" + batchNumber + '\'' +
                ", courseBatchNo='" + courseBatchNo + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
