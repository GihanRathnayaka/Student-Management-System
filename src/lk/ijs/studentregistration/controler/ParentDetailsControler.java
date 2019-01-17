package lk.ijs.studentregistration.controler;

import com.jfoenix.controls.JFXButton;
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
import lk.ijs.studentregistration.dto.ParentDTO;
import lk.ijs.studentregistration.view.utill.ParentTM;
import lk.ijs.studentregistration.business.ValidationClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class  ParentDetailsControler<T> {
    public static String studentID;
    @FXML
    private Button btnHome;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtMobile1;
    @FXML
    private JFXTextField txtMobile2;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtDesignation;
    @FXML
    private JFXTextField txtWorkingPlace;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private TableView<ParentTM> tblParent;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnRmove;
    @FXML
    private JFXButton btnSave;

    private int saveNotSave =0;

    public void initialize(){
        loadParentDetails();
        tblParent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblParent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("mobile1"));
        tblParent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("mobile2"));
        tblParent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("email"));
        tblParent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("designation"));
        tblParent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("workingPlace"));
        tblParent.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("address"));

        tblParent.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ParentTM>() {
            @Override
            public void changed(ObservableValue<? extends ParentTM> observable, ParentTM oldValue, ParentTM parent) {
                if(parent==null){return;}
                txtName.setText(parent.getName());
                txtAddress.setText(parent.getAddress());
                txtDesignation.setText(parent.getDesignation());
                txtEmail.setText(parent.getEmail());
                txtMobile1.setText(parent.getMobile1());
                txtMobile2.setText(parent.getMobile2());
                txtWorkingPlace.setText(parent.getWorkingPlace());
                btnAdd.setText("Update");
            }
        });

    }

    @FXML
    private void btnHome_onAction(ActionEvent actionEvent) throws IOException {


        if((saveNotSave==0 ) && StudentRegistrationControler.parentDetails==null){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do Need Exit Without Save ParentDTO Details !");

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
        System.out.println("Wada");
    }

    @FXML
    private void btnAdd_onAction(ActionEvent actionEvent) {

        if(btnAdd.getText().equals("Update")){
            if (txtName.getText().trim().isEmpty() || txtAddress.getText().isEmpty()) {
                if (txtName.getText().trim().isEmpty()) {
                    txtName.requestFocus();
                    new Alert(Alert.AlertType.ERROR, "Enter ParentDTO name !", ButtonType.OK).showAndWait();
                    System.out.println("");

                    return;
                }
                if (txtAddress.getText().trim().isEmpty()) {
                    txtAddress.requestFocus();
                    new Alert(Alert.AlertType.ERROR, "Enter Address !", ButtonType.OK).showAndWait();
                    return;
                }
            }

            if (!ValidationClass.isValidEmailAddress(txtEmail.getText().trim())) {
                new Alert(Alert.AlertType.ERROR, "Sorry  Email Incorrect !  ", ButtonType.OK).showAndWait();
                txtEmail.requestFocus();
                return;
            }
            if (!ValidationClass.isValidPhoneNumber(txtMobile1.getText().trim())) {
                new Alert(Alert.AlertType.ERROR, "Sorry  Phone Number Incorrect !  ", ButtonType.OK).showAndWait();
                txtMobile1.requestFocus();
                return;
            }
            System.out.println("");

            if (!txtMobile2.getText().trim().isEmpty()) {
                if (!ValidationClass.isValidPhoneNumber(txtMobile2.getText().trim())) {
                    new Alert(Alert.AlertType.ERROR, "Sorry Second Mobile Number Incorrect !  ", ButtonType.OK).showAndWait();
                    txtMobile2.requestFocus();
                    System.out.println("");
                    return;
                }
            }


            String name = txtName.getText().trim();
            String address = txtAddress.getText().trim();
            String mobile1 = txtMobile1.getText().trim();
            String mobile2 = txtMobile2.getText().trim();
            String workPlace = txtWorkingPlace.getText().trim();
            String designation = txtDesignation.getText().trim();
            String email = txtEmail.getText().trim();

            ArrayList<ParentDTO> list = new ArrayList<>();

            if(StudentRegistrationControler.parentDetails.size()==1){
                list.add(new ParentDTO(name, mobile1, mobile2, email, designation, workPlace, address,studentID));
                StudentRegistrationControler.parentDetails =list;
            }else {

                ArrayList<ParentDTO> tem =StudentRegistrationControler.parentDetails;
                ArrayList<ParentDTO> tempary =new ArrayList<>();

                for (ParentDTO tm : tem) {
                    if (tm.getName().equals(name) || tm.getMobile1().equals(mobile1)) {
                       tempary.add(new ParentDTO(name, mobile1, mobile2, email, designation, workPlace, address,studentID));
                        continue;
                    }
                    tempary.add(tm);
                }
                StudentRegistrationControler.parentDetails=tempary;

                System.out.println("");
            }

            new Alert(Alert.AlertType.CONFIRMATION, "Details Updated !", ButtonType.OK).showAndWait();
            loadParentDetails();
            btnAdd.setText("Add");
        }else {

            if (txtName.getText().trim().isEmpty() || txtAddress.getText().isEmpty()) {
                if (txtName.getText().trim().isEmpty()) {
                    txtName.requestFocus();
                    new Alert(Alert.AlertType.ERROR, "Enter ParentDTO name !", ButtonType.OK).showAndWait();
                    return;
                }
                if (txtAddress.getText().trim().isEmpty()) {
                    txtAddress.requestFocus();
                    new Alert(Alert.AlertType.ERROR, "Enter Address !", ButtonType.OK).showAndWait();
                    return;
                }
            }

            if (!ValidationClass.isValidEmailAddress(txtEmail.getText().trim())) {
                new Alert(Alert.AlertType.ERROR, "Sorry  Email Incorrect !  ", ButtonType.OK).showAndWait();
                txtEmail.requestFocus();
                return;
            }
            if (!ValidationClass.isValidPhoneNumber(txtMobile1.getText().trim())) {
                new Alert(Alert.AlertType.ERROR, "Sorry  Phone Number Incorrect !  ", ButtonType.OK).showAndWait();
                txtMobile1.requestFocus();
                return;
            }
            if (!txtMobile2.getText().trim().isEmpty()) {
                if (!ValidationClass.isValidPhoneNumber(txtMobile2.getText().trim())) {
                    new Alert(Alert.AlertType.ERROR, "Sorry Second Mobile Number Incorrect !  ", ButtonType.OK).showAndWait();
                    txtMobile2.requestFocus();
                    return;
                }
            }


            String name = txtName.getText().trim();
            String address = txtAddress.getText().trim();
            String mobile1 = txtMobile1.getText().trim();
            String mobile2 = txtMobile2.getText().trim();
            String workPlace = txtWorkingPlace.getText().trim();
            String designation = txtDesignation.getText().trim();
            String email = txtEmail.getText().trim();

            if(StudentRegistrationControler.parentDetails==null) {
                StudentRegistrationControler.parentDetails = new ArrayList<>();
            }
            StudentRegistrationControler.parentDetails.add(new ParentDTO(name, mobile1, mobile2, email, designation, workPlace, address,studentID));
            if (StudentRegistrationControler.parentDetails.size() > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Details Added !", ButtonType.OK).showAndWait();
            }

        }
        ++ saveNotSave;
        txtEmail.clear();
        txtDesignation.clear();
        txtWorkingPlace.clear();
        txtMobile2.clear();
        txtMobile1.clear();
        txtAddress.clear();
        txtName.clear();
        loadParentDetails();


    }

    @FXML
    private void btnRmove_onAction(ActionEvent actionEvent) {

        if(tblParent.getSelectionModel().getSelectedItem()!=null){
            ParentTM parent = tblParent.getSelectionModel().getSelectedItem();

            for (ParentDTO tm:StudentRegistrationControler.parentDetails ) {
                if(tm.getName().equals(txtName.getText().trim())){
                    if(StudentRegistrationControler.parentDetails.size()==1){
                        StudentRegistrationControler.parentDetails=null;
                        saveNotSave=0;
                        tblParent.getItems().clear();
                        btnAdd.setText("Add");
                        return;
                    }
                    continue;
                }
                StudentRegistrationControler.parentDetails.add(tm);
            }
           StudentRegistrationControler.parentDetails=null;
        }else{
            new Alert(Alert.AlertType.ERROR, "No Table Value Seleted to Remove !  ", ButtonType.OK).showAndWait();
            return;

        }
        tblParent.getItems().clear();

        if(StudentRegistrationControler.parentDetails.size()>0){
            loadParentDetails();
        }else {
            StudentRegistrationControler.parentDetails=null;
        }
        StudentRegistrationControler.parentDetails=null;
        -- saveNotSave;
        txtEmail.clear();
        txtDesignation.clear();
        txtWorkingPlace.clear();
        txtMobile2.clear();
        txtMobile1.clear();
        txtAddress.clear();
        txtName.clear();


    }

    @FXML
    private void btnSave_OnAction(ActionEvent actionEvent) throws IOException {

        if(StudentRegistrationControler.parentDetails==null){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do Need Exit Without Save ParentDTO Details !");
            ButtonType bttYes = new ButtonType("Yes");
            ButtonType bttNo = new ButtonType("No");
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(bttYes, bttNo);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if(buttonType.get().getText().equals("Yes")){
                StudentRegistrationControler.parentDetails=null;
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Please Enter Your Details !", ButtonType.OK).showAndWait();
                return;

            }

        }

        new Alert(Alert.AlertType.CONFIRMATION, "Information Saved  !", ButtonType.OK).showAndWait();

        tblParent.setItems(null);
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/studentregistration/view/StudentRegistration.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnAdd.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
        System.out.println("Wada");

    }

    private void loadParentDetails(){
        if(StudentRegistrationControler.parentDetails ==null){return;}
        if(StudentRegistrationControler.parentDetails!=null||StudentRegistrationControler.parentDetails.size()>0){
            ArrayList<ParentTM>temp = new ArrayList<>();
            for (ParentDTO d:StudentRegistrationControler.parentDetails) {
                temp.add(new ParentTM(d.getName(),d.getMobile1(),d.getMobile2(),d.getEmail(),d.getDesignation(),d.getWorkingPlace(),d.getAddress(),d.getStudentID()));
            }
            ObservableList<ParentTM> list = FXCollections.observableArrayList(temp);
            tblParent.setItems(list);
        }

    }
}
