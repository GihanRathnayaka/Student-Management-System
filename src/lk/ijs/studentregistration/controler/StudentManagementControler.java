package lk.ijs.studentregistration.controler;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijs.studentregistration.business.BOFactory;
import lk.ijs.studentregistration.business.custom.ManageStudentRegistrationBO;
import lk.ijs.studentregistration.dto.StudentDTO;
import lk.ijs.studentregistration.dto.SuperDTO;
import lk.ijs.studentregistration.entity.CustomEntity;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentManagementControler {
    @FXML
    private Button btnHome;
    @FXML
    private JFXTextField txtStudentID;
    @FXML
    private TableView<StudentDTO> tblStudent;
    @FXML
    private TableView<CustomEntity> tblReg_Courses;

    static ManageStudentRegistrationBO manageStudentRegistrationBO = BOFactory.getInstance().getBO(BOFactory.daoType.MANAGESTUDENT);

    public void initialize(){
        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("studentID"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nameWI"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("email"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("bod"));
        tblStudent.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblStudent.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tblStudent.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("phone"));
        tblStudent.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("gen"));
        tblStudent.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("eduQ"));
        tblStudent.getColumns().get(11).setCellValueFactory(new PropertyValueFactory<>("school"));
        tblStudent.getColumns().get(12).setCellValueFactory(new PropertyValueFactory<>("university"));
        tblStudent.getColumns().get(13).setCellValueFactory(new PropertyValueFactory<>("faculty"));
        tblStudent.getColumns().get(14).setCellValueFactory(new PropertyValueFactory<>("city"));
        loadAllStudent();

        tblReg_Courses.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        tblReg_Courses.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tblReg_Courses.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("courseDuration"));
        tblReg_Courses.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("batchNumber"));
        tblReg_Courses.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("batchName"));
        tblReg_Courses.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("courceBatchNo"));
        tblReg_Courses.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("regDate"));


        tblStudent.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StudentDTO>() {
            @Override
            public void changed(ObservableValue<? extends StudentDTO> observable, StudentDTO oldValue, StudentDTO student) {
                if(student==null){return;}
                String id = student.getStudentID();
                try {
                    List<CustomEntity>list =manageStudentRegistrationBO.studentCoursesInformation(id);
                    ObservableList<CustomEntity> e = FXCollections.observableArrayList(list);
                    tblReg_Courses.setItems(e);
                } catch (Exception e) {
                    Logger.getLogger("").log(Level.SEVERE, null, e);
                }
            }
        });

    }


    @FXML
    private void btnHome_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijs/studentregistration/view/Home.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) btnHome.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
        System.out.println("wada");
    }

    @FXML
    private void txtStudentID_OnAction(ActionEvent actionEvent) {

    }

    private void loadAllStudent(){
        try {
            List<StudentDTO> students = manageStudentRegistrationBO.AllRegisteredStudent();
            ObservableList<StudentDTO>temp = FXCollections.observableArrayList(students);
            tblStudent.setItems(temp);

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void txtStudentID_OnKeyType(KeyEvent keyEvent) {

        List<StudentDTO> students = null;
        try {
            students = manageStudentRegistrationBO.searchStudentCoursesInformation(txtStudentID.getText().toUpperCase().trim()+"%");
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        if(students==null){return;}
        ObservableList<StudentDTO>temp = FXCollections.observableArrayList(students);
        tblStudent.setItems(temp);

    }
}
