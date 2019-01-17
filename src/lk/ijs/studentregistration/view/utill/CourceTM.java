package lk.ijs.studentregistration.view.utill;

import java.time.LocalDate;

public class CourceTM {
    private String courceID;
    private LocalDate regdate;
    private String name;
    private String description;
    private String duration;

    public CourceTM() {
    }

    public CourceTM(String courceID, LocalDate regdate, String name, String description, String duration) {
        this.courceID = courceID;
        this.regdate = regdate;
        this.name = name;
        this.description = description;
        this.duration = duration;
    }

    public String getCourceID() {
        return courceID;
    }

    public void setCourceID(String courceID) {
        this.courceID = courceID;
    }

    public LocalDate getRegdate() {
        return regdate;
    }

    public void setRegdate(LocalDate regdate) {
        this.regdate = regdate;
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
        return "CourceTM{" +
                "courceID='" + courceID + '\'' +
                ", regdate=" + regdate +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
