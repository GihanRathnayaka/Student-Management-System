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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijs.studentregistration.business.BOFactory;
import lk.ijs.studentregistration.business.custom.ManageBatchesBO;
import lk.ijs.studentregistration.business.custom.ManageCourceBO;
import lk.ijs.studentregistration.dto.CourceDTO;
import lk.ijs.studentregistration.view.utill.CourceTM;
import lk.ijs.studentregistration.business.custom.impl.ManageCourceBOImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageCourseControler<T>  {
    @FXML
    private Button btnHome;
    @FXML
    private JFXTextField txtDuration;
    @FXML
    private DatePicker dpRegDatea;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXTextField txtCourceNumer;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXDatePicker dpRegDate;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private TableView<CourceTM> tblCources;


    ManageCourceBO manageCourceBO = BOFactory.getInstance().getBO(BOFactory.daoType.MANAGECOURSE);
    ManageBatchesBO manageBatchesBO = BOFactory.getInstance().getBO(BOFactory.daoType.MANAGEBATCH);


    public void initialize()  {
        setCourceNumber();
        loadAllCources();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        tblCources.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("courceID"));
        tblCources.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCources.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("regdate"));
        tblCources.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblCources.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("duration"));

        tblCources.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CourceTM>() {
            @Override
            public void changed(ObservableValue<? extends CourceTM> observable, CourceTM oldValue, CourceTM cource) {
                if(cource==null){return;}
                txtCourceNumer.setText(cource.getCourceID());
                txtDescription.setText(cource.getDescription());
                txtName.setText(cource.getName());
                dpRegDatea.setValue(cource.getRegdate());
                txtDuration.setText(cource.getDuration());
                btnSave.setDisable(true);
                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);
            }
        });

    }

    @FXML
    private void btnUpdate(ActionEvent actionEvent) {
        try {
            if(dpRegDatea.getValue().toString().equals("")){}
            System.out.println("");
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Please Enter Register Date !", ButtonType.OK).showAndWait();
            dpRegDatea.requestFocus();
            return;
        }

        if(txtCourceNumer.getText().trim().isEmpty()||txtName.getText().trim().isEmpty()){
            if(txtName.getText().trim().isEmpty()){
                txtName.requestFocus();
                new Alert(Alert.AlertType.ERROR,"Please Enter CourceDTO Name !", ButtonType.OK).showAndWait();
                return;
            }
            if(txtCourceNumer.getText().trim().isEmpty()){
                txtCourceNumer.requestFocus();
                new Alert(Alert.AlertType.ERROR,"Please Enter CourceDTO Number !", ButtonType.OK).showAndWait();
                return;
            }
            System.out.println("");

        }

        String cno = txtCourceNumer.getText().trim();
        String name =txtName.getText().trim();
        String description=  txtDescription.getText().trim();
        String duration  =txtDuration.getText().trim();
        LocalDate regDate =dpRegDatea.getValue();

        boolean result = false;
        try {
            result = manageCourceBO.updateCource(new CourceDTO(cno,regDate,name,description,duration));
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

        if(result){
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully CourceDTO Updated !",ButtonType.OK).showAndWait();
        }else {  new Alert(Alert.AlertType.ERROR," CourceDTO Not  Update Error !",ButtonType.OK).showAndWait();
        }


        txtDescription.setText("");
        txtName.clear();
        txtDuration.clear();
        txtCourceNumer.clear();
        dpRegDatea.setValue(null);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(false);
        dpRegDatea.setValue(null);
        setCourceNumber();
        loadAllCources();
    }

    @FXML
    private void btnDelete_OnAction(ActionEvent actionEvent) {

        if(txtCourceNumer.getText().trim().equals("")||txtName.getText().trim().equals("")){
            new Alert(Alert.AlertType.ERROR," Select CourceDTO For Remove !",ButtonType.OK).showAndWait();
            return;
        }

        boolean result= false;
        try {
            result = manageCourceBO.deleteCource(txtCourceNumer.getText().trim());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        if(result){
            new Alert(Alert.AlertType.CONFIRMATION,"CourceDTO Succesfully Deleted !",ButtonType.OK).showAndWait();
        }else{
            new Alert(Alert.AlertType.ERROR,"Error CourceDTO Not Deleted !",ButtonType.OK).showAndWait();

        }


        txtDescription.setText("");
        txtName.clear();
        txtDuration.clear();
        txtCourceNumer.clear();
        dpRegDatea.setValue(null);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(false);
        setCourceNumber();
        loadAllCources();
    }

    @FXML
    private void btnClear_OnAction(ActionEvent actionEvent) throws Exception {
        txtCourceNumer.clear();
        txtName.clear();
        txtDuration.clear();
        txtDescription.clear();
        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        setCourceNumber();
        loadAllCources();
    }

    @FXML
    private void btnSave_onAction(ActionEvent actionEvent)  {

        try {
            if(dpRegDatea.getValue().toString().equals("")){}

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Please Enter Register Date !", ButtonType.OK).showAndWait();
            dpRegDatea.requestFocus();
            return;
        }

        if(txtCourceNumer.getText().trim().isEmpty()||txtName.getText().trim().isEmpty()){
            if(txtName.getText().trim().isEmpty()){
                txtName.requestFocus();
                new Alert(Alert.AlertType.ERROR,"Please Enter CourceDTO Name !", ButtonType.OK).showAndWait();
                return;
            }
            if(txtCourceNumer.getText().trim().isEmpty()){
                txtCourceNumer.requestFocus();
                new Alert(Alert.AlertType.ERROR,"Please Enter CourceDTO Number !", ButtonType.OK).showAndWait();
                return;
            }


        }

        String cno = txtCourceNumer.getText().trim();
        String name =txtName.getText().trim();
        String description=  txtDescription.getText().trim();
        String duration  =txtDuration.getText().trim();
        LocalDate regDate =dpRegDatea.getValue();

        boolean result = false;
        try {
            result = manageCourceBO.createCource(new CourceDTO(cno,regDate,name,description,duration));
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

        if(result){
          new Alert(Alert.AlertType.CONFIRMATION,"Successfully CourceDTO Created !",ButtonType.OK).showAndWait();
            }else {
            new Alert(Alert.AlertType.ERROR," CourceDTO Not  Created Error !",ButtonType.OK).showAndWait();
          }


        txtDescription.setText("");
        txtName.clear();
        txtDuration.clear();
        txtCourceNumer.clear();
        dpRegDatea.setValue(null);
        setCourceNumber();
        loadAllCources();

    }
    @FXML
    private void btnHome_onAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/studentregistration/view/Home.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnClear.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    private void setCourceNumber()  {
        try {
            txtCourceNumer.setText(manageCourceBO.getCourceNumber());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
    }

    private void loadAllCources()  {
        List<CourceDTO> tms = null;
        try {
            tms = manageCourceBO.getAllCources();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        if (tms==null){return;}
        ArrayList<CourceTM>temp = new ArrayList<>();
        for (CourceDTO tm:tms ) {
            temp.add(new CourceTM(tm.getCourceID(),tm.getRegdate(),tm.getName(),tm.getDescription(),tm.getDuration()));
        }
        ObservableList<CourceTM>ob =FXCollections.observableArrayList(temp);
        tblCources.setItems(ob);
    }


}
