package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PetManagementFormController {
    public AnchorPane dashboardPane;
    public void petbtnOnAction(ActionEvent event) {
    }

    public void customerbtnOnAction(ActionEvent event) {
    }

    public void usersbtnOnAction(ActionEvent event) {
    }

    public void employeebtnOnAction(ActionEvent event) {
    }

    public void suppliesbtnOnAction(ActionEvent event) {
    }

    public void billingbtnOnAction(ActionEvent event) {
    }

    public void inhousebtnOnAction(ActionEvent event) {
    }

    public void logoutbtnOnAction(ActionEvent event) {
    }

    public void petRegisterbtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/PetRegisterForm.fxml"))));
        stage.setTitle("Item Form");
        stage.centerOnScreen();
        stage.show();
    }

    public void petViewbtnOnAction(ActionEvent event) {
    }

    public void petUpdatebtnOnAction(ActionEvent event) {
    }

    public void petDeletebtnOnAction(ActionEvent event) {
    }


}
