package lk.ijs.studentregistration.dto;

import java.time.LocalDate;

public class RegistrationInfoDTO implements SuperDTO {
    private String studentId;
    private String studentName;
    private String courseCode;
    private String courseName;
    private String batchCode;
    private String batchName;
    private LocalDate regDate;

    public RegistrationInfoDTO() {
    }

    public RegistrationInfoDTO(String studentId, String studentName, String courseCode, String courseName, String batchCode, String batchName, LocalDate regDate) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.batchCode = batchCode;
        this.batchName = batchName;
        this.regDate = regDate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "RegistrationInfoDTO{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", batchCode='" + batchCode + '\'' +
                ", batchName='" + batchName + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
