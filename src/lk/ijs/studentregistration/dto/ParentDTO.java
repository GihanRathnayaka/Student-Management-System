package lk.ijs.studentregistration.dto;

public class ParentDTO implements SuperDTO{
    private String name;
    private String mobile1;
    private String mobile2;
    private String email;
    private String designation;
    private String workingPlace;
    private String address;
    private String studentID ;

    public ParentDTO() {
    }

    public ParentDTO(String name, String mobile1, String mobile2, String email, String designation, String workingPlace, String address) {
        this.setName(name);
        this.setMobile1(mobile1);
        this.setMobile2(mobile2);
        this.setEmail(email);
        this.setDesignation(designation);
        this.setWorkingPlace(workingPlace);
        this.setAddress(address);
    }

    public ParentDTO(String name, String mobile1, String mobile2, String email, String designation, String workingPlace, String address, String studentID) {
        this.name = name;
        this.mobile1 = mobile1;
        this.mobile2 = mobile2;
        this.email = email;
        this.designation = designation;
        this.workingPlace = workingPlace;
        this.address = address;
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getWorkingPlace() {
        return workingPlace;
    }

    public void setWorkingPlace(String workingPlace) {
        this.workingPlace = workingPlace;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    @Override
    public String toString() {
        return "ParentDTO{" +
                "name='" + name + '\'' +
                ", mobile1='" + mobile1 + '\'' +
                ", mobile2='" + mobile2 + '\'' +
                ", email='" + email + '\'' +
                ", designation='" + designation + '\'' +
                ", workingPlace='" + workingPlace + '\'' +
                ", address='" + address + '\'' +
                ", studentID='" + studentID + '\'' +
                '}';
    }
}
