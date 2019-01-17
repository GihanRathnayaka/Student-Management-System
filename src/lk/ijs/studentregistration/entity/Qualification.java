package lk.ijs.studentregistration.entity;

import java.time.LocalDate;

public class Qualification extends SuperEntity {
    private int id;
    private String qualification;
    private String inistution;
    private LocalDate isuseDate;
    private String specification;
    private String description;
    private String studentID;

    public Qualification() {
    }

    public Qualification(int id, String qualification, String inistution, LocalDate isuseDate, String specification, String description, String studentID) {
        this.id = id;
        this.qualification = qualification;
        this.inistution = inistution;
        this.isuseDate = isuseDate;
        this.specification = specification;
        this.description = description;
        this.studentID = studentID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getInistution() {
        return inistution;
    }

    public void setInistution(String inistution) {
        this.inistution = inistution;
    }

    public LocalDate getIsuseDate() {
        return isuseDate;
    }

    public void setIsuseDate(LocalDate isuseDate) {
        this.isuseDate = isuseDate;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    @Override
    public String toString() {
        return "Qualification{" +
                "id=" + id +
                ", qualification='" + qualification + '\'' +
                ", inistution='" + inistution + '\'' +
                ", isuseDate=" + isuseDate +
                ", specification='" + specification + '\'' +
                ", description='" + description + '\'' +
                ", studentID='" + studentID + '\'' +
                '}';
    }
}
