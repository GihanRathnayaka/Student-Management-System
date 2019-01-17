package lk.ijs.studentregistration.controler;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import lk.ijs.studentregistration.business.BOFactory;
import lk.ijs.studentregistration.business.custom.ManageBatchesBO;
import lk.ijs.studentregistration.business.custom.ManageCourceBO;
import lk.ijs.studentregistration.dto.BatchDTO;
import lk.ijs.studentregistration.entity.CustomEntity;
import lk.ijs.studentregistration.view.utill.BatchesTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BatchesControler<T> {
    @FXML
    private  JFXDatePicker dptAppCloseDay;
    @FXML
    private JFXDatePicker dtpStartDate;
    @FXML
    private  JFXTextField txtBatchName;
    @FXML
    private  JFXComboBox cmbCourcename;
    @FXML
    private  JFXTextField txtCourceBatchNo;
    @FXML
    private  JFXTextField txtBatchNo;
    @FXML
    private JFXDatePicker dptEndDate;
    @FXML
    private  JFXTextField txtNumberofStudent;
    @FXML
    private  JFXTextField txtFee;
    @FXML
    private  JFXTextField txtDescription;
    @FXML
    private  JFXButton btnSave;
    @FXML
    private  JFXButton btnUpdate;
    @FXML
    private  JFXButton btnDelete;
    @FXML
    private  JFXButton btnClear;
    @FXML
    private  Button btnHome;
    @FXML
    private  TableView<BatchesTM> tblBatches;

    private static ManageBatchesBO manageBatchesBO =BOFactory.getInstance().getBO(BOFactory.daoType.MANAGEBATCH);
    private static ManageCourceBO manageCourceBO=BOFactory.getInstance().getBO(BOFactory.daoType.MANAGECOURSE);

    public void initialize(){
        loadCources();
        setBatchNumber();
        loadAllBatches();
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        tblBatches.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("batchNumber"));
        tblBatches.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("batchName"));
        tblBatches.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("courceName"));
        tblBatches.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("CourcebatchNumber"));
        tblBatches.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tblBatches.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("EndDate"));
        tblBatches.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("NumberOfStudent"));
        tblBatches.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("fee"));
        tblBatches.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("appCloseDate"));
        tblBatches.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("description"));

        tblBatches.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BatchesTM>() {
            @Override
            public void changed(ObservableValue<? extends BatchesTM> observable, BatchesTM oldValue, BatchesTM batch) {
                if(batch==null){return;}
                txtBatchNo.setText(batch.getBatchNumber());
                txtBatchName.setText(batch.getBatchName());
                txtCourceBatchNo.setText(batch.getCourcebatchNumber());
                txtFee.setText(batch.getFee()+"");
                txtNumberofStudent.setText(batch.getNumberOfStudent()+"");
                txtDescription.setText(batch.getDescription());
                dtpStartDate.setValue(batch.getStartDate());
                dptEndDate.setValue(batch.getEndDate());
                dptAppCloseDay.setValue(batch.getAppCloseDate());
                cmbCourcename.setPromptText(batch.getCourceName());
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
                btnSave.setDisable(true);
            }
        });


    }

    @FXML
    private  void btnSave_OnAction(ActionEvent actionEvent) {
        if(txtBatchName.getText().trim().isEmpty()||cmbCourcename.getSelectionModel().getSelectedItem().toString().isEmpty()||txtCourceBatchNo.getText().trim().isEmpty()){

            if(txtBatchName.getText().trim().isEmpty()){
                new Alert(Alert.AlertType.ERROR,"Please Enter The BatchDTO Name!", ButtonType.OK).showAndWait();
                txtBatchName.requestFocus();
                return;
            }
            if(cmbCourcename.getSelectionModel().getSelectedItem().toString().isEmpty()){
                new Alert(Alert.AlertType.ERROR,"Please Select CourceDTO !", ButtonType.OK).showAndWait();
                cmbCourcename.requestFocus();
                return;
            }
            if(txtCourceBatchNo.getText().trim().toString().isEmpty()){
                new Alert(Alert.AlertType.ERROR,"Please Select CourceDTO Number !", ButtonType.OK).showAndWait();
                txtCourceBatchNo.requestFocus();
                return;
            }

        }
        try { if(dtpStartDate.getValue().toString().trim().equals("")){}}catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Please Enter The Start Date !", ButtonType.OK).showAndWait();
            dtpStartDate.requestFocus();
            return;
        }
        try { if(dptEndDate.getValue().toString().equals("")){}}catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Please Enter The END Date !", ButtonType.OK).showAndWait();
            dptEndDate.requestFocus();

            System.out.println("");
            return;
        }
        try { if(dptAppCloseDay.getValue().toString().equals("")){}}catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Please Enter The Application Close Date !", ButtonType.OK).showAndWait();
            dptAppCloseDay.requestFocus();
            return;
        }
        try {

            Integer.parseInt(txtNumberofStudent.getText().trim());

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Please Enter Number Of StudentDTO For Cources !", ButtonType.OK).showAndWait();
            txtNumberofStudent.requestFocus();
            return;
        }

        try {
            Double.parseDouble(txtFee.getText().trim());

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Please Enter The CourceDTO Fee !", ButtonType.OK).showAndWait();
            txtFee.requestFocus();
            return;
        }


        String batchNo =txtBatchNo.getText().trim();
        String batchName =txtBatchName.getText().trim();
        String batchCurceNo =txtCourceBatchNo.getText().trim();
        String description =txtDescription.getText().trim();
        int numberStudent =Integer.parseInt(txtNumberofStudent.getText().trim());
        double fee = Double.parseDouble(txtFee.getText().trim());
        LocalDate startDate =dtpStartDate.getValue();
        LocalDate endDate =dptEndDate.getValue();
        LocalDate appcloseDate =dptAppCloseDay.getValue();
        String courceNumber  = null;
        try {
            courceNumber = manageBatchesBO.getCourceNumber(cmbCourcename.getSelectionModel().getSelectedItem().toString());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        boolean result = false; //ManageBatchesBOImpl.createBatch();
        try {
            result = manageBatchesBO.createBatch(new BatchDTO(batchNo,batchName,courceNumber,batchCurceNo,startDate,endDate,numberStudent,fee,appcloseDate,description));
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        if(result){
            new Alert(Alert.AlertType.CONFIRMATION,"Succesfully Batch Created !",ButtonType.OK).showAndWait();

        }else {
            new Alert(Alert.AlertType.ERROR,"BatchDTO not Created Error !",ButtonType.OK).showAndWait();

        }
        reset();
    }

    @FXML
    private  void btnUpdate_OnAction(ActionEvent actionEvent)  {

        if(txtBatchName.getText().trim().isEmpty()||txtCourceBatchNo.getText().trim().isEmpty()){

            if(txtBatchName.getText().trim().isEmpty()){
                new Alert(Alert.AlertType.ERROR,"Please Enter The BatchDTO Name!", ButtonType.OK).showAndWait();
                txtBatchName.requestFocus();
                return;
            }
            if(cmbCourcename.getSelectionModel().getSelectedItem().toString().isEmpty()){
                new Alert(Alert.AlertType.ERROR,"Please Select CourceDTO !", ButtonType.OK).showAndWait();
                cmbCourcename.requestFocus();
                return;
            }
            if(txtCourceBatchNo.getText().trim().toString().isEmpty()){
                new Alert(Alert.AlertType.ERROR,"Please Select CourceDTO Number !", ButtonType.OK).showAndWait();
                txtCourceBatchNo.requestFocus();
                return;
            }

        }
        try { if(dtpStartDate.getValue().toString().equals("")){}}catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Please Enter The Start Date !", ButtonType.OK).showAndWait();
            dtpStartDate.requestFocus();
            System.out.println("");
            return;
        }
        try { if(dptEndDate.getValue().toString().equals("")){}}catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Please Enter The END Date !", ButtonType.OK).showAndWait();
            System.out.println("");
            dptEndDate.requestFocus();
            System.out.println("");
            return;
        }
        try { if(dptAppCloseDay.getValue().toString().equals("")){}}catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Please Enter The Application Close Date !", ButtonType.OK).showAndWait();
            dptAppCloseDay.requestFocus();
            System.out.println("");
            return;
        }
        try {

            Integer.parseInt(txtNumberofStudent.getText().trim());

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Please Enter Number Of StudentDTO For Cources !", ButtonType.OK).showAndWait();
            txtNumberofStudent.requestFocus();
            System.out.println("");
            return;
        }

        try {
            Double.parseDouble(txtFee.getText().trim());

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Please Enter The CourceDTO Fee !", ButtonType.OK).showAndWait();
            System.out.println("");
            txtFee.requestFocus();
            return;
        }


        String batchNo =txtBatchNo.getText().trim();
        String batchName =txtBatchName.getText().trim();
        String batchCurceNo =txtCourceBatchNo.getText().trim();
        String description =txtDescription.getText().trim();
        int numberStudent =Integer.parseInt(txtNumberofStudent.getText().trim());
        double fee = Double.parseDouble(txtFee.getText().trim());
        LocalDate startDate =dtpStartDate.getValue();
        LocalDate endDate =dptEndDate.getValue();
        LocalDate appcloseDate =dptAppCloseDay.getValue();
        String courceNumber  = null; //ManageBatchesBOImpl.getCourceNumber();
        try {
            courceNumber = manageBatchesBO.getCourceNumber(cmbCourcename.getPromptText().trim());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

        boolean result = false;
        try {
            result = manageBatchesBO.updatBatch(new BatchDTO(batchNo,batchName,courceNumber,batchCurceNo,startDate,endDate,numberStudent,fee,appcloseDate,description));
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        new Alert(Alert.AlertType.CONFIRMATION,"Succesfully BatchDTO Update !",ButtonType.OK).showAndWait();
        if(result){

        }else {
        new Alert(Alert.AlertType.ERROR, "BatchDTO not Update Error !", ButtonType.OK).showAndWait();
            }
        reset();
    }

    @FXML
    private  void btnDelete_OnAction(ActionEvent actionEvent)  {
        if(btnSave.isDisable()){
            boolean result= false;// ManageBatchesBOImpl.deleteBatch(txtBatchNo.getText());
            try {
                result = manageBatchesBO.deleteBatch(txtBatchNo.getText().trim());
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
            if(result){
                new Alert(Alert.AlertType.CONFIRMATION,"Succesfully BatchDTO Deleted !",ButtonType.OK).showAndWait();

            }else {
                new Alert(Alert.AlertType.ERROR,"BatchDTO not Deleted Error !",ButtonType.OK).showAndWait();

            }
            reset();
        }
    }

    @FXML
    private  void btnClear_onAction(ActionEvent actionEvent) {
        reset();
    }

    @FXML
    private  void btnHome_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/studentregistration/view/Home.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnClear.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();/////////////////////////////
    }

    public void cmbCourcename_OnAction(ActionEvent actionEvent)  {
        try {
            txtCourceBatchNo.setText( manageBatchesBO.getCourceBatchNumber(cmbCourcename.getSelectionModel().getSelectedItem().toString()+"%"));
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        // cmbCourcename.setPromptText("CourceDTO Name");
    }


    private void loadCources(){
        List<String> allCources = null;
        try {
            allCources = manageBatchesBO.getAllCources();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        if(allCources==null){return;}
        ObservableList<String> list = FXCollections.observableArrayList(allCources);
        cmbCourcename.setItems(list);

    }

    private void setBatchNumber(){
        try {
            txtBatchNo.setText(manageBatchesBO.getBatchNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllBatches()  {
        ArrayList<CustomEntity>tm = null;
        try {
            tm = manageBatchesBO.AllBatches();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

        if(tm==null){tblBatches.setItems(null); return;}
        ArrayList<BatchesTM>temp = new ArrayList<>();
        for (CustomEntity d:tm) {

            temp.add(new BatchesTM(d.getBatchNumber(),d.getBatchName(),d.getName(),
                    d.getCourceBatchNo(),d.getStartDate(),d.getEndDate(),d.getNofStudent(),d.getFee(),d.getAppCloseDay(),d.getDescription()));
        }
        ObservableList<BatchesTM> batchesDTOS = FXCollections.observableArrayList(temp);
        tblBatches.setItems(batchesDTOS);

    }

    private void reset(){
        txtCourceBatchNo.setText("");
        txtBatchName.setText("");
        txtFee.setText("");
        txtNumberofStudent.clear();
        txtBatchName.clear();
        txtDescription.clear();
        setBatchNumber();
        loadAllBatches();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);
        cmbCourcename.setPromptText("CourceDTO Name");
        dptAppCloseDay.setValue(null);
        dptEndDate.setValue(null);
        dtpStartDate.setValue(null);
        dtpStartDate.setValue(null);

    }


}
