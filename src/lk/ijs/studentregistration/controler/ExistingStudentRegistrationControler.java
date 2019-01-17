package lk.ijs.studentregistration.controler;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijs.studentregistration.business.BOFactory;
import lk.ijs.studentregistration.business.custom.ManageBatchesBO;
import lk.ijs.studentregistration.business.custom.ManageCourceBO;
import lk.ijs.studentregistration.business.custom.ManageParentBO;
import lk.ijs.studentregistration.business.custom.ManageStudentRegistrationBO;
import lk.ijs.studentregistration.dto.RegistrationInfoDTO;
import lk.ijs.studentregistration.dto.StudentDTO;
import lk.ijs.studentregistration.business.custom.impl.ManageExistingStudentBOImpl;
import lk.ijs.studentregistration.view.utill.RegistrationInfoTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExistingStudentRegistrationControler {
    @FXML
    private JFXComboBox cmbCource;
    @FXML
    private JFXTextField txtStudentID;
    @FXML
    private Button btnHome;
    @FXML
    private JFXTextField txtNic;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXComboBox cmbBatch;
    @FXML
    private TableView<RegistrationInfoTM> tblRegistration;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private JFXButton btnRegistration;
    @FXML
    private JFXButton btnClear;

    public static ArrayList<RegistrationInfoDTO> registrationInfo =null;

    public void initialize(){
        loadCourses();
        loadRegCourse();


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


    static ManageStudentRegistrationBO manageStudentRegistrationBO = BOFactory.getInstance().getBO(BOFactory.daoType.MANAGESTUDENT);
    static ManageBatchesBO manageBatchesBO = BOFactory.getInstance().getBO(BOFactory.daoType.MANAGEBATCH);
    static ManageParentBO manageParentBO =BOFactory.getInstance().getBO(BOFactory.daoType.MANAGEPARENT);
    static ManageCourceBO manageCourceBO= BOFactory.getInstance().getBO(BOFactory.daoType.MANAGECOURSE);

    @FXML
    private void btnHome_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/studentregistration/view/Home.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnAdd.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
        System.out.println("");
    }

    @FXML
    private void txtStudentID_OnAction(ActionEvent actionEvent) throws Exception {

     if (!txtStudentID.getText().trim().equals("")) {

            StudentDTO student = manageStudentRegistrationBO.findStudent(txtStudentID.getText().toUpperCase());
            if (student != null) {

                System.out.println(student);
                txtStudentID.setText(student.getStudentID());
                txtNic.setText(student.getNic());
                txtName.setText(student.getFullName());
            }

        } else{
            new Alert(Alert.AlertType.ERROR, "No StudentDTO Found !", ButtonType.OK).showAndWait();
            txtName.clear();
            txtNic.clear();
            txtStudentID.clear();
            txtStudentID.requestFocus();
      }
    }

    @FXML
    private void txtNic_OnAction(ActionEvent actionEvent) {

        if (!txtNic.getText().trim().equals("")) {

            StudentDTO student = ManageExistingStudentBOImpl.getStudentProfileNIC(txtNic.getText().trim().toUpperCase());
            if (student != null) {
                txtStudentID.setText(student.getStudentID());
                txtNic.setText(student.getNic());
                txtName.setText(student.getFullName());
            }else {
                new Alert(Alert.AlertType.ERROR, "No StudentDTO Found !", ButtonType.OK).showAndWait();

            }

        } else{
            new Alert(Alert.AlertType.ERROR, "No StudentDTO Found !", ButtonType.OK).showAndWait();
            txtName.clear();
            txtNic.clear();
            txtStudentID.clear();
            txtStudentID.requestFocus();
            System.out.println("");
        }
    }

    @FXML
    private void cmbCource_OnAction(ActionEvent actionEvent) {

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
    private void btnAdd_onAction(ActionEvent actionEvent) {

        if(cmbCource.getSelectionModel().getSelectedItem().toString().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Select Registration Course !",ButtonType.OK).showAndWait();
            return;
        }

        if(cmbBatch.getSelectionModel().getSelectedItem().toString().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Select Registration Course BatchDTO !",ButtonType.OK).showAndWait();
            return;
        }


        if(txtName.getText().trim().isEmpty()||txtNic.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please type Your Reg Number And Press Enter ! ",ButtonType.OK).showAndWait();
            txtStudentID.requestFocus();
            return;
        }

        String studentID =txtStudentID.getText().trim();
        String nameWI =txtName.getText().trim();
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

        loadCourses();
        cmbBatch.setItems(null);
        loadRegCourse();
        reset();
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
            new Alert(Alert.AlertType.ERROR,"If you Want Remove Selected  Added Cource ! ",ButtonType.OK).showAndWait();

        }
    }

    @FXML
    private void btnRegistration_OnAction(ActionEvent actionEvent) {

        try {
            boolean result = manageStudentRegistrationBO.registrationExistingStudent(registrationInfo);
            if(result){
                new Alert(Alert.AlertType.CONFIRMATION,"Succesfully Sudent Registered !",ButtonType.OK).showAndWait();
            }else {
                new Alert(Alert.AlertType.ERROR,"Sudent Not Registered !",ButtonType.OK).showAndWait();

            }
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        reset();
        txtStudentID.clear();
        txtNic.clear();
        txtName.clear();
        reset();
        registrationInfo=null;
        tblRegistration.setItems(null);
    }

    @FXML
    private void btnClear_onAction(ActionEvent actionEvent) {
        reset();
        txtStudentID.clear();
        txtNic.clear();
        txtName.clear();
        reset();
        registrationInfo=null;
        tblRegistration.setItems(null);
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

    private void reset(){
       // txtStudentID.clear();
        //txtNic.clear();
        //txtName.clear();
        cmbCource.setItems(null);
        cmbBatch.setItems(null);
        loadCourses();
    }

}
