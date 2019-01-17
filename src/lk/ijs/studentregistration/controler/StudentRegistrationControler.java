package lk.ijs.studentregistration.controler;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijs.studentregistration.business.BOFactory;
import lk.ijs.studentregistration.business.custom.ManageBatchesBO;
import lk.ijs.studentregistration.business.custom.ManageCourceBO;
import lk.ijs.studentregistration.business.custom.ManageParentBO;
import lk.ijs.studentregistration.business.custom.ManageStudentRegistrationBO;
import lk.ijs.studentregistration.business.custom.impl.ManageBatchesBOImpl;
import lk.ijs.studentregistration.business.custom.impl.ManageStudentRegistrationBOImpl;
import lk.ijs.studentregistration.business.ValidationClass;
import lk.ijs.studentregistration.dto.EducationDTO;
import lk.ijs.studentregistration.dto.ParentDTO;
import lk.ijs.studentregistration.dto.RegistrationInfoDTO;
import lk.ijs.studentregistration.dto.StudentDTO;
import lk.ijs.studentregistration.view.utill.EducationTM;
import lk.ijs.studentregistration.view.utill.ParentTM;
import lk.ijs.studentregistration.view.utill.RegistrationInfoTM;
import lk.ijs.studentregistration.view.utill.StudentTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentRegistrationControler<T> {
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXButton btnGardinace;
    @FXML
    private JFXButton btnAddEducation;
    @FXML
    private Button btnHome;
    @FXML
    private JFXTextField txtNWI;
    @FXML
    private JFXTextField txtStudentID;
    @FXML
    private JFXTextField txtFullName;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtCity;
    @FXML
    private JFXTextField txtTelePhoneHome;
    @FXML
    private JFXTextField txtMobile;
    @FXML
    private JFXDatePicker dtpBOD;
    @FXML
    private JFXTextField txtNic;
    @FXML
    private JFXTextField txtSchool;
    @FXML
    private JFXTextField txtUniversity;
    @FXML
    private JFXTextField txtFaculty;
    @FXML
    private JFXButton btnRegistration;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private JFXRadioButton rbOther;
    @FXML
    private JFXRadioButton rbOL;
    @FXML
    private JFXComboBox cmbBatch;
    @FXML
    private JFXComboBox cmbCource;
    @FXML
    private JFXRadioButton rbAL;
    @FXML
    private JFXRadioButton rbDiploma;
    @FXML
    private JFXRadioButton rbDegree;
    @FXML
    private JFXRadioButton rbMaster;
    @FXML
    private JFXRadioButton rbOthers;
    @FXML
    private JFXRadioButton rbFmail;
    @FXML
    private JFXRadioButton rbMale;
    @FXML
    private TableView<RegistrationInfoTM> tblRegistration;

    private ToggleGroup edu= new ToggleGroup();
    private ToggleGroup gender = new ToggleGroup();
    static StudentDTO student =null;
    public static ArrayList<ParentDTO> parentDetails =null;
    public static ArrayList<EducationDTO> Qualification =null;
    public static ArrayList<RegistrationInfoDTO> registrationInfo =null;



    static ManageStudentRegistrationBO manageStudentRegistrationBO = BOFactory.getInstance().getBO(BOFactory.daoType.MANAGESTUDENT);
    static ManageBatchesBO manageBatchesBO = BOFactory.getInstance().getBO(BOFactory.daoType.MANAGEBATCH);
    static ManageParentBO manageParentBO =BOFactory.getInstance().getBO(BOFactory.daoType.MANAGEPARENT);
    static ManageCourceBO manageCourceBO= BOFactory.getInstance().getBO(BOFactory.daoType.MANAGECOURSE);

    public void initialize() throws Exception {

        rbFmail.setToggleGroup(gender);
        rbMale.setToggleGroup(gender);
        rbOthers.setToggleGroup(gender);
        rbOther.setToggleGroup(edu);
        rbDegree.setToggleGroup(edu);
        rbAL.setToggleGroup(edu);
        rbMaster.setToggleGroup(edu);
        rbDiploma.setToggleGroup(edu);
        rbOL.setToggleGroup(edu);
        if(student!=null){
            txtNic.setText(student.getNic());
            txtMobile.setText(student.getMobile());
            txtTelePhoneHome.setText(student.getPhone());
            txtFaculty.setText(student.getFaculty());
            txtUniversity.setText(student.getUniversity());
            txtEmail.setText(student.getEmail());
            txtFullName.setText(student.getFullName());
            txtNWI.setText(student.getNameWI());
            txtCity.setText(student.getCity());
            txtSchool.setText(student.getSchool());
            dtpBOD.setValue(student.getBod());
            txtAddress.setText(student.getAddress());
            if(student.getGen().equals("Male")){rbMale.setSelected(true);}else if(student.getGen().equals("Female")){rbFmail.setSelected(true);}else{rbOthers.setSelected(true);}
            if(student.getEduQ().equals("Master")){rbMaster.setSelected(true);}else if(student.getEduQ().equals("Degree")){rbDegree.setSelected(true);}else if(student.getEduQ().equals("Diploma")){
                rbDiploma.setSelected(true);}else if(student.getEduQ().equals("A/L")){rbAL.setSelected(true);}else if(student.getEduQ().equals("A/L")){rbOL.setSelected(true);}else {rbOther.setSelected(true);}
        }
        setStudentID();
        loadCourses();
        loadRegCourse();


        tblRegistration.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblRegistration.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("studentName"));
        tblRegistration.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        tblRegistration.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tblRegistration.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("batchCode"));
        tblRegistration.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("batchName"));
        tblRegistration.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("regDate"));



    }

    @FXML
    private void btnRegistration_OnAction(ActionEvent actionEvent) throws Exception {

        if(Qualification==null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, " Do You" +
                    " Have To Enter Professional Qualification !");
            ButtonType bttYes = new ButtonType("Yes");
            ButtonType bttNo = new ButtonType("No");
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(bttYes, bttNo);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().getText().equals("Yes")) {
                new Alert(Alert.AlertType.ERROR, "Please Enter" +
                        " Professional Qualification !", ButtonType.OK).showAndWait();

                return;
            }

        }
                if (registrationInfo == null || registrationInfo.size() <= 0) {
                    new Alert(Alert.AlertType.ERROR, "Please Enter StudentDTO " +
                            "Registration Cources !", ButtonType.OK).showAndWait();
                    return;
                }

                if (parentDetails == null || parentDetails.size() <= 0) {
                    new Alert(Alert.AlertType.ERROR, "Please Enter" +
                            " StudentDTO Gardians Information !", ButtonType.OK).showAndWait();
                    return;
                }
               ///////// second part

                if(Qualification==null){Qualification=new ArrayList<>();}

                boolean result =manageStudentRegistrationBO.StudentCourceRegistration(student,Qualification
                        ,parentDetails,registrationInfo);

                if (true) {
                    tblRegistration.setItems(null);
                     new Alert(Alert.AlertType.CONFIRMATION, "StudentDTO Registered Succesfully ",
                             ButtonType.OK).showAndWait();
                } else {
                     new Alert(Alert.AlertType.ERROR, "Not Register There Is A Error ",
                             ButtonType.OK).showAndWait();

                }


        reset();

    }
    @FXML
    private void btnAdd_OnAction(ActionEvent actionEvent) {

        if(student==null||parentDetails==null){
            if(student==null){
                new Alert(Alert.AlertType.ERROR,"Please Enter StudentDTO Information !",ButtonType.OK).showAndWait();
                return;
            }
            if(parentDetails==null){
                new Alert(Alert.AlertType.ERROR,"Please Enter StudentDTO Gardians Information !",ButtonType.OK).showAndWait();
                return;
            }
        }

        if(cmbCource.getSelectionModel().getSelectedItem().toString().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Select Registration Course !",ButtonType.OK).showAndWait();
            return;
        }

        if(cmbBatch.getSelectionModel().getSelectedItem().toString().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Select Registration Course BatchDTO !",ButtonType.OK).showAndWait();
            return;
        }

        String studentID =txtStudentID.getText().trim();
        String nameWI =txtNWI.getText().trim();
        String courceName =cmbCource.getSelectionModel().getSelectedItem().toString().trim();
        String courseBatchNo =cmbBatch.getSelectionModel().getSelectedItem().toString().trim();
        String batchName=null;
        String batchNo=null;
        String courceNo=null;
        try {
             batchName = manageBatchesBO.getBatchName(courseBatchNo);
             batchNo = manageBatchesBO.getBatchName(courseBatchNo);
             courceNo =manageBatchesBO.getBatchNum(courseBatchNo);
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        LocalDate regDate = LocalDate.now();
//        int numberofStudentRegistered=0;
//        int studentRegistred=0;
//
//
//        try {
//              numberofStudentRegistered = manageBatchesBO.getRegisterdStudent(batchNo);
//              studentRegistred =manageBatchesBO.getRegisterdStudent(courseBatchNo);
//        } catch (Exception e) {
//            Logger.getLogger("").log(Level.SEVERE, null, e);
//        }
//
//
//        System.out.println(numberofStudentRegistered+" : "+studentRegistred);
//
//        if(numberofStudentRegistered==studentRegistred){
//            new Alert(Alert.AlertType.ERROR,"StudentDTO Limited Over ",ButtonType.OK).showAndWait();
//            return;
//        }
        if(registrationInfo==null){
            registrationInfo= new ArrayList<>();
            registrationInfo.add(new RegistrationInfoDTO(studentID,nameWI,courceNo,courceName,batchNo,courseBatchNo,regDate));
        }else{

            if(registrationInfo.size()>0){
                for (RegistrationInfoDTO tm:registrationInfo ) {
                    if(tm.getCourseCode().equals(courceNo)){
                        new Alert(Alert.AlertType.ERROR,"Already Added ",ButtonType.OK).showAndWait();
                        return;
                    }
                }
            }
            ParentDetailsControler.studentID =studentID;
            QualificationControler.studentID=studentID;
            registrationInfo.add(new RegistrationInfoDTO(studentID,nameWI,courceNo,courceName,batchNo,courseBatchNo,regDate));

        }
        //cmbCource.setItems(c);
        loadRegCourse();
        loadCourses();
        cmbBatch.setItems(null);



    }

    @FXML
    private void btnRemove_OnAction(ActionEvent actionEvent) {

        if(tblRegistration.getSelectionModel().getSelectedItem()!=null){

            if(registrationInfo.size()==1){
                registrationInfo=null;
                tblRegistration.setItems(null);
            }else{

                ArrayList<RegistrationInfoDTO> temp = new ArrayList<>();
                for (RegistrationInfoDTO info :registrationInfo){
                    if(info.getCourseCode().equals(tblRegistration.getSelectionModel().getSelectedItem().getCourseCode())){
                       continue;
                    }
                    temp.add(info);
                }

                registrationInfo=temp;
            }


        }else{
            new Alert(Alert.AlertType.ERROR,"If you Want Remove Selected  Added CourceDTO ! ",ButtonType.OK).showAndWait();

        }

        loadRegCourse();
        loadCourses();

    }

    @FXML
    private void btnClear_OnAction(ActionEvent actionEvent) throws Exception {
        reset();
    }

    @FXML
    private void cmbBatch_onAction(ActionEvent actionEvent) {
    }

    @FXML
    private void cmbCource_onAction(ActionEvent actionEvent) {
        if(cmbCource.getSelectionModel().getSelectedItem()==null){return;}
        String courceNumber = null;
        try {
            courceNumber = manageBatchesBO.getCourceNumber(cmbCource.getSelectionModel().getSelectedItem().toString().trim());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        cmbBatch.setItems(null);
        ArrayList<String> allBatches = null;
        try {
            allBatches = manageBatchesBO.getAviableBatches(courceNumber);
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        if(allBatches==null){
            new Alert(Alert.AlertType.ERROR,"No Batches Registation Now !",ButtonType.OK).showAndWait();
            return;
        }
        ObservableList<String> list = FXCollections.observableArrayList(allBatches);
        cmbBatch.setItems(list);

    }

    @FXML
    private void btnGardinace_onAction(ActionEvent actionEvent) throws IOException {
        if(txtStudentID.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter StudentDTO Id ! ", ButtonType.OK).showAndWait();
            txtStudentID.requestFocus();
            return;
        }
        if(txtNWI.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter StudentDTO Name With initials ! ", ButtonType.OK).showAndWait();
            txtNWI.requestFocus();
            return;
        }
        if(txtFullName.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter StudentDTO full Name !  ", ButtonType.OK).showAndWait();
            txtFullName.requestFocus();
            return;
        }
        if(txtNic.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter StudentDTO NIC !  ", ButtonType.OK).showAndWait();
            txtNic.requestFocus();
            return;
        }
        if(txtAddress.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter StudentDTO Address !  ", ButtonType.OK).showAndWait();
            txtAddress.requestFocus();
            return;
        }
        if(txtCity.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter City !  ", ButtonType.OK).showAndWait();
            txtCity.requestFocus();
            return;
        }

        if(txtMobile.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Mobile Number !  ", ButtonType.OK).showAndWait();
            txtMobile.requestFocus();
            return;
        }
        try {
            if(dtpBOD.getValue().toString().equals("")){}
            System.out.println("");
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Please Enter Date Of Birth !", ButtonType.OK).showAndWait();
            dtpBOD.requestFocus();
            return;
        }
        if(txtEmail.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Your Valid Email !  ", ButtonType.OK).showAndWait();
            txtEmail.requestFocus();
            return;
        }

        if(edu.getSelectedToggle()==null){
            new Alert(Alert.AlertType.ERROR,"Please Select EducationDTO Qualification !", ButtonType.OK).showAndWait();
            return;
        }
        if(gender.getSelectedToggle()==null){
            new Alert(Alert.AlertType.ERROR,"Please Select StudentDTO Gender !", ButtonType.OK).showAndWait();
            return;
        }


        if(!ValidationClass.isValidEmailAddress(txtEmail.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Sorry Your Email Incorrect !  ", ButtonType.OK).showAndWait();
            txtEmail.requestFocus();
            return;
        }
        if(!ValidationClass.isValidPhoneNumber(txtTelePhoneHome.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Sorry Your Phone Number Incorrect !  ", ButtonType.OK).showAndWait();
            txtTelePhoneHome.requestFocus();
            return;
        }
        if(!ValidationClass.isValidPhoneNumber(txtMobile.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Sorry Your Mobile Number Incorrect !  ", ButtonType.OK).showAndWait();
            txtMobile.requestFocus();
            return;
        }
        if(!ValidationClass.validateNIC(txtNic.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Sorry Your NIC Number is Invalid !  ", ButtonType.OK).showAndWait();
            txtNic.requestFocus();
            return;
        }



        String studentID =txtStudentID.getText().trim();
        String nameWI =txtNWI.getText().trim();
        String fullName =txtFullName.getText().trim();
        String address =txtAddress.getText().trim();
        String phone =txtTelePhoneHome.getText().trim();
        String mobile =txtMobile.getText().trim();
        String gen = ((RadioButton) gender.getSelectedToggle()).getText();
        String eduQ= ((RadioButton) edu.getSelectedToggle()).getText();
        String school =txtSchool.getText().trim();
        String faculty =txtFaculty.getText().trim();
        String university =txtUniversity.getText().trim();
        String nic =txtNic.getText().trim();
        String email =txtEmail.getText().trim();
        LocalDate bod =dtpBOD.getValue();
        String city =txtCity.getText().trim();
        ParentDetailsControler.studentID=studentID;
        student= new StudentDTO(studentID,nameWI,fullName,address,email,bod,phone,mobile,gen,eduQ,school,faculty,university,nic,city);
        if(student!=null){
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/studentregistration/view/ParentsDetails.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) btnAddEducation.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.centerOnScreen();
            System.out.println("Wada");
        }

    }

    @FXML
    private void btnAddEducation_onAction(ActionEvent actionEvent) throws IOException {

        if(txtStudentID.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter StudentDTO Id ! ", ButtonType.OK).showAndWait();
            txtStudentID.requestFocus();
            return;
        }
        if(txtNWI.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter StudentDTO Name With initials ! ", ButtonType.OK).showAndWait();
            txtNWI.requestFocus();
            return;
        }
        if(txtFullName.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter StudentDTO full Name !  ", ButtonType.OK).showAndWait();
            txtFullName.requestFocus();
            return;
        }
        if(txtNic.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter StudentDTO NIC !  ", ButtonType.OK).showAndWait();
            txtNic.requestFocus();
            return;
        }
        if(txtAddress.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter StudentDTO Address !  ", ButtonType.OK).showAndWait();
            txtAddress.requestFocus();
            return;
        }
        if(txtCity.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter City !  ", ButtonType.OK).showAndWait();
            txtCity.requestFocus();
            return;
        }

        if(txtMobile.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Mobile Number !  ", ButtonType.OK).showAndWait();
            txtMobile.requestFocus();
            return;
        }
        try {
            if(dtpBOD.getValue().toString().equals("")){}
            System.out.println("");
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Please Enter Date Of Birth !", ButtonType.OK).showAndWait();
            dtpBOD.requestFocus();
            System.out.println("");
            return;
        }
        if(txtEmail.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Your Valid Email !  ", ButtonType.OK).showAndWait();
            txtEmail.requestFocus();
            return;
        }

        if(edu.getSelectedToggle()==null){
            new Alert(Alert.AlertType.ERROR,"Please Select EducationDTO Qualification !", ButtonType.OK).showAndWait();
            return;
        }
        if(gender.getSelectedToggle()==null){
            new Alert(Alert.AlertType.ERROR,"Please Select StudentDTO Gender !", ButtonType.OK).showAndWait();
            return;
        }


        if(!ValidationClass.isValidEmailAddress(txtEmail.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Sorry Your Email Incorrect !  ", ButtonType.OK).showAndWait();
            txtEmail.requestFocus();
            return;
        }
        if(!ValidationClass.isValidPhoneNumber(txtTelePhoneHome.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Sorry Your Phone Number Incorrect !  ", ButtonType.OK).showAndWait();
            txtTelePhoneHome.requestFocus();
            return;
        }
        if(!ValidationClass.isValidPhoneNumber(txtMobile.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Sorry Your Mobile Number Incorrect !  ", ButtonType.OK).showAndWait();
            txtMobile.requestFocus();
            return;
        }
        if(!ValidationClass.validateNIC(txtNic.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Sorry Your NIC Number is Invalid !  ", ButtonType.OK).showAndWait();
            txtNic.requestFocus();
            return;
        }



        String studentID =txtStudentID.getText().trim();
        String nameWI =txtNWI.getText().trim();
        String fullName =txtFullName.getText().trim();
        String address =txtAddress.getText().trim();
        String phone =txtTelePhoneHome.getText().trim();
        String mobile =txtMobile.getText().trim();
        String gen = ((RadioButton) gender.getSelectedToggle()).getText();
        String eduQ= ((RadioButton) edu.getSelectedToggle()).getText();
        String school =txtSchool.getText().trim();
        String faculty =txtFaculty.getText().trim();
        String university =txtUniversity.getText().trim();
        String nic =txtNic.getText().trim();
        String email =txtEmail.getText().trim();
        LocalDate bod =dtpBOD.getValue();
        String city =txtCity.getText().trim();
        ////////////
        ParentDetailsControler.studentID =studentID;
        QualificationControler.studentID=studentID;
        //////////
        student= new StudentDTO(studentID,nameWI,fullName,address,email,bod,phone,mobile,gen,eduQ,school,faculty,university,nic,city);
        if(student!=null){
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/studentregistration/view/EducationInfo.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) btnAddEducation.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.centerOnScreen();
        }


        //System.out.println("Wada n");
    }

    @FXML
    private void btnHome_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/studentregistration/view/Home.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnAddEducation.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
        System.out.println("Wada n");
    }


    private void setStudentID()  {
        try {
            txtStudentID.setText(manageStudentRegistrationBO.getStudentID());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
    }

    private void loadCourses(){
        ArrayList<String> allCources = null;
        try {
            allCources = manageBatchesBO.getAllCources();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        if(allCources==null){return;}
       ObservableList<String> list = FXCollections.observableArrayList(allCources);
        cmbCource.setItems(list);
    }

    private void  loadRegCourse(){
        if(registrationInfo==null){return;}
        ArrayList<RegistrationInfoTM> temp = new ArrayList<>();
        for (RegistrationInfoDTO info:registrationInfo ) {
            temp.add(new RegistrationInfoTM(info.getStudentId(),info.getStudentName(),info.getCourseCode(),info.getCourseName(),info.getBatchCode(),info.getBatchName(),info.getRegDate()));
        }

        ObservableList<RegistrationInfoTM>list=FXCollections.observableArrayList(temp);
        tblRegistration.setItems(list);
    }

    private void reset() throws Exception {

        txtNWI.clear();
        txtStudentID.clear();
        txtAddress.clear();
        txtSchool.clear();
        txtCity.clear();
        txtFullName.clear();
        txtEmail.clear();
        txtNic.clear();
        txtUniversity.clear();
        txtTelePhoneHome.clear();
        txtMobile.clear();
        edu.selectToggle(null);
        gender.selectToggle(null);
        Qualification=null;
        parentDetails=null;
        student=null;
        registrationInfo=null;
        tblRegistration.setItems(null);
        loadCourses();
        setStudentID();
        ParentDetailsControler.studentID =null;
        QualificationControler.studentID=null;

    }


}
