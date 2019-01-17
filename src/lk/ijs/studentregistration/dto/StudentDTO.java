package lk.ijs.studentregistration.dto;

import java.time.LocalDate;

public class StudentDTO implements SuperDTO{
    private String studentID;
    private String nameWI;
    private String fullName;
    private String address;
    private String email;
    private LocalDate bod;
    private String nic;
    private String mobile;
    private String phone;
    private String gen;
    private String eduQ;
    private String school;
    private String university;
    private String faculty;
    private String city;

    public StudentDTO() {
    }

    public StudentDTO(String studentID, String nameWI, String fullName, String address, String email, LocalDate bod, String nic, String mobile, String phone, String gen, String eduQ, String school, String university, String faculty, String city) {
        this.setStudentID(studentID);
        this.setNameWI(nameWI);
        this.setFullName(fullName);
        this.setAddress(address);
        this.setEmail(email);
        this.setBod(bod);
        this.setNic(nic);
        this.setMobile(mobile);
        this.setPhone(phone);
        this.setGen(gen);
        this.setEduQ(eduQ);
        this.setSchool(school);
        this.setUniversity(university);
        this.setFaculty(faculty);
        this.setCity(city);
    }


    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getNameWI() {
        return nameWI;
    }

    public void setNameWI(String nameWI) {
        this.nameWI = nameWI;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBod() {
        return bod;
    }

    public void setBod(LocalDate bod) {
        this.bod = bod;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getEduQ() {
        return eduQ;
    }

    public void setEduQ(String eduQ) {
        this.eduQ = eduQ;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "studentID='" + studentID + '\'' +
                ", nameWI='" + nameWI + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", bod=" + bod +
                ", nic='" + nic + '\'' +
                ", mobile='" + mobile + '\'' +
                ", phone='" + phone + '\'' +
                ", gen='" + gen + '\'' +
                ", eduQ='" + eduQ + '\'' +
                ", school='" + school + '\'' +
                ", university='" + university + '\'' +
                ", faculty='" + faculty + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
