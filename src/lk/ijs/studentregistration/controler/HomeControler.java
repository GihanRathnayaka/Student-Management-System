package lk.ijs.studentregistration.controler;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeControler {
    @FXML
    private JFXButton btnExit;
    @FXML
    private Button btnStudentRegistration;
    @FXML
    private Button btnCourcesRegistration;
    @FXML
    private Button btnBatchesRestration;
    @FXML
    private Button BtnStudentManagement;


    @FXML
    private void btnExit_OnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    private void btnStudentRegistration_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/studentregistration/view/StudentRegBoad.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnBatchesRestration.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    @FXML
    private void btnCourcesRegistration_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/studentregistration/view/manageCourses.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnBatchesRestration.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
        System.out.println("");
    }

    @FXML
    private void btnBatchesRestration_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/studentregistration/view/batches.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnBatchesRestration.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    @FXML
    private void BtnStudentManagement_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/studentregistration/view/StudentManagement.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnBatchesRestration.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
