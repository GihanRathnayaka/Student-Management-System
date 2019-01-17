package lk.ijs.studentregistration.controler;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import lk.ijs.studentregistration.dto.EducationDTO;
import lk.ijs.studentregistration.view.utill.EducationTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class QualificationControler<T> {
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;
    @FXML
    private JFXTextField txtQualification;
    @FXML
    private JFXTextField txtInstitute;
    @FXML
    private JFXDatePicker dtpAwardDate;
    @FXML
    private JFXTextField txtSpecilize;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private Button btnHome;
    @FXML
    private JFXButton btnSave;
    @FXML
    private TableView<EducationTM> tblProfesional;

    private int Qsize=0;
    public static String studentID;

    public void initialize(){

        tblProfesional.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Qualification"));
        tblProfesional.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("inistution"));
        tblProfesional.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("isuseDate"));
        tblProfesional.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("specification"));
        tblProfesional.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("description"));
        loadEduQulification();

        tblProfesional.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EducationTM>() {
            @Override
            public void changed(ObservableValue<? extends EducationTM> observable, EducationTM oldValue, EducationTM edu) {
                if(edu==null){return;}
                txtQualification.setText(edu.getQualification());
                txtInstitute.setText(edu.getInistution());
                txtDescription.setText(edu.getDescription());
                txtQualification.setText(edu.getQualification());
                dtpAwardDate.setValue(edu.getIsuseDate());
                btnAdd.setText("Update");
                txtQualification.setEditable(false);
            }
        });


    }

    @FXML
    private void btnAdd_onAction(ActionEvent actionEvent) {

        if(btnAdd.getText().equals("Update")) {

            if (txtQualification.getText().trim().isEmpty() || txtInstitute.getText().isEmpty()) {
                if (txtQualification.getText().isEmpty()) {
                    txtQualification.requestFocus();
                    new Alert(Alert.AlertType.ERROR, "Please Enter Qualification", ButtonType.OK).showAndWait();
                    System.out.println("Error");
                    return;
                }
                if (txtInstitute.getText().isEmpty()) {
                    txtInstitute.requestFocus();
                    new Alert(Alert.AlertType.ERROR, "Please Enter Institute !", ButtonType.OK).showAndWait();
                    return;
                }
            }
            try {
                if (dtpAwardDate.getValue().toString().equals("")) {
                }
                System.out.println("");
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Awarded Date !", ButtonType.OK).showAndWait();
                dtpAwardDate.requestFocus();
                return;
            }


            String Qualification = txtQualification.getText().trim();
            LocalDate isuseDate = dtpAwardDate.getValue();
            String inistution = txtInstitute.getText().trim();
            String specification = txtSpecilize.getText().trim();
            String description = txtDescription.getText().trim();

            ArrayList<EducationDTO> temp = new ArrayList<>();

            if(StudentRegistrationControler.Qualification.size()>0){

                for (EducationDTO tm:StudentRegistrationControler.Qualification) {
                    if(tm.getQualification().equals(txtQualification.getText().trim())){
                      temp.add(new EducationDTO(Qualification,inistution,isuseDate,specification,description,studentID)) ;
                      continue;
                    }
                    temp.add(tm);
                }
                StudentRegistrationControler.Qualification=temp;
            }

            new Alert(Alert.AlertType.CONFIRMATION, "Details Update !", ButtonType.OK).showAndWait();
            txtInstitute.clear();
            txtQualification.clear();
            txtDescription.clear();
            txtSpecilize.clear();
            dtpAwardDate.setValue(null);
            btnAdd.setText("Save");
            txtQualification.setEditable(true);
            loadEduQulification();


        }else {
            if (txtQualification.getText().trim().isEmpty() || txtInstitute.getText().isEmpty()) {
                if (txtQualification.getText().isEmpty()) {
                    txtQualification.requestFocus();
                    new Alert(Alert.AlertType.ERROR, "Please Enter Qualification", ButtonType.OK).showAndWait();
                    return;
                }
                if (txtInstitute.getText().isEmpty()) {
                    txtInstitute.requestFocus();
                    new Alert(Alert.AlertType.ERROR, "Please Enter Institute !", ButtonType.OK).showAndWait();
                    return;
                }
            }
            try {
                if (dtpAwardDate.getValue().toString().equals("")) {
                }
                System.out.println("");
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Awarded Date !", ButtonType.OK).showAndWait();
                dtpAwardDate.requestFocus();
                System.out.println("");
                return;
            }


            String Qualification = txtQualification.getText().trim();
            LocalDate isuseDate = dtpAwardDate.getValue();
            String inistution = txtInstitute.getText().trim();
            String specification = txtSpecilize.getText().trim();
            String description = txtDescription.getText().trim();

            if (StudentRegistrationControler.Qualification == null) {
                StudentRegistrationControler.Qualification = new ArrayList<>();
                StudentRegistrationControler.Qualification.add(new EducationDTO(Qualification, inistution, isuseDate, specification, description,studentID));
            } else {
                StudentRegistrationControler.Qualification.add(new EducationDTO(Qualification, inistution, isuseDate, specification, description,studentID));
            }

            new Alert(Alert.AlertType.CONFIRMATION, "Details Added !", ButtonType.OK).showAndWait();
            ++Qsize;
        }
        loadEduQulification();
        txtInstitute.clear();
        txtQualification.clear();
        txtDescription.clear();
        txtSpecilize.clear();
        dtpAwardDate.setValue(null);
        btnAdd.setText("Save");

    }

    @FXML
    private void btnRemove_OnAction(ActionEvent actionEvent) {

        if(tblProfesional.getSelectionModel().getSelectedItem()!=null){

            if(StudentRegistrationControler.Qualification.size()==1){
                StudentRegistrationControler.Qualification=null;
                tblProfesional.setItems(null);
            }else{
                ArrayList<EducationDTO> temp = new ArrayList<>();
                for (EducationDTO tm:StudentRegistrationControler.Qualification) {

                    if(tm.getQualification().equals(txtQualification.getText().trim())){
                        --Qsize;
                        continue;
                    }
                    temp.add(tm);
                }

            }

        }

        loadEduQulification();
        txtInstitute.clear();
        txtQualification.clear();
        txtDescription.clear();
        txtSpecilize.clear();
        dtpAwardDate.setValue(null);
        btnAdd.setText("Save");
        txtQualification.setEditable(true);
        loadEduQulification();

    }

    @FXML
    private void btnHome_onAction(ActionEvent actionEvent) throws IOException {


        if((Qsize==0 ) && StudentRegistrationControler.Qualification==null){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do You Need Exit Without Saving StudentDTO Professional Qualification !");

            ButtonType bttYes = new ButtonType("Yes");
            ButtonType bttNo = new ButtonType("No");
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(bttYes, bttNo);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if(buttonType.get().getText().equals("Yes")){

                StudentRegistrationControler.parentDetails=null;
            }else {
                return;
            }

        }

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/studentregistration/view/StudentRegistration.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnAdd.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
        System.out.println("Wada nee");
    }

    @FXML
    private void btnSave_OnAction(ActionEvent actionEvent) throws IOException {

        if(StudentRegistrationControler.Qualification==null){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do Need Exit Without Qualification !");
            ButtonType bttYes = new ButtonType("Yes");
            ButtonType bttNo = new ButtonType("No");
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(bttYes, bttNo);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if(buttonType.get().getText().equals("Yes")){
                StudentRegistrationControler.parentDetails=null;
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Please Enter StudentDTO Qualification  Details !", ButtonType.OK).showAndWait();
                return;

            }

        }

        new Alert(Alert.AlertType.CONFIRMATION, "Information Saved  !", ButtonType.OK).showAndWait();

        tblProfesional.setItems(null);
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/studentregistration/view/StudentRegistration.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnAdd.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();


    }

    private void loadEduQulification() {
        if (StudentRegistrationControler.Qualification != null) {

            ArrayList<EducationTM> tms = new ArrayList<>();

            for (EducationDTO d: StudentRegistrationControler.Qualification ) {
                tms.add(new EducationTM(d.getQualification(),d.getInistution(),d.getIsuseDate(),d.getSpecification(),d.getDescription(),d.getStudentID()));
//                Qualification, inistution, isuseDate, specification, description,studentID
            }

            ObservableList<EducationTM> observableList = FXCollections.observableArrayList(tms);
            tblProfesional.setItems(observableList);
        }
    }
}
