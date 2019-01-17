package lk.ijs.studentregistration.view.utill;

import java.time.LocalDate;

public class StudentTM {
    private String studentID ;
    private String nameWI ;
    private String fullName ;
    private String address ;
    private String phone ;
    private String mobile ;
    private String gen ;
    private String eduQ;
    private String school ;
    private String faculty;
    private String university ;
    private String nic ;
    private String email ;
    private LocalDate bod ;
    private String city ;

    public StudentTM() {
    }

    public StudentTM(String studentID, String nameWI, String fullName, String address, String phone, String mobile, String gen, String eduQ, String school, String faculty, String university, String nic, String email, LocalDate bod, String city) {
        this.studentID = studentID;
        this.nameWI = nameWI;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.mobile = mobile;
        this.gen = gen;
        this.eduQ = eduQ;
        this.school = school;
        this.faculty = faculty;
        this.university = university;
        this.nic = nic;
        this.email = email;
        this.bod = bod;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "StudentTM{" +
                "studentID='" + studentID + '\'' +
                ", nameWI='" + nameWI + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gen='" + gen + '\'' +
                ", eduQ='" + eduQ + '\'' +
                ", school='" + school + '\'' +
                ", faculty='" + faculty + '\'' +
                ", university='" + university + '\'' +
                ", nic='" + nic + '\'' +
                ", email='" + email + '\'' +
                ", bod=" + bod +
                ", city='" + city + '\'' +
                '}';
    }
}
