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

public class StudentRegBoadControler {
    @FXML
    private Button btnHome;
    @FXML
    private JFXButton btnNewRegistration;
    @FXML
    private JFXButton btnExistingStudentRegistration;

    @FXML
    private void btnHome_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/studentregistration/view/Home.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnHome.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    @FXML
    private void btnNewRegistration_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/studentregistration/view/StudentRegistration.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnHome.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    @FXML
    private void btnExistingStudentRegistration(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/studentregistration/view/ExistingStudentRegistration.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnHome.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
