package lk.ijs.studentregistration.entity;

import java.time.LocalDate;

public class Course extends SuperEntity {
    private String courceNo;
    private LocalDate startDate;
    private String name;
    private String description;
    private String duration;

    public Course() {
    }

    public Course(String courceNo, LocalDate startDate, String name, String description, String duration) {
        this.courceNo = courceNo;
        this.startDate = startDate;
        this.name = name;
        this.description = description;
        this.duration = duration;
    }

    public String getCourceNo() {
        return courceNo;
    }

    public void setCourceNo(String courceNo) {
        this.courceNo = courceNo;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courceNo='" + courceNo + '\'' +
                ", startDate=" + startDate +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
