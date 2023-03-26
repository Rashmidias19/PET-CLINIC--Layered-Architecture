package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeScheduleFormController {
    public AnchorPane dashboardPane;
    public void backbtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ScheduleManagementForm.fxml"))));
        stage.setTitle("Item Form");
        stage.centerOnScreen();
        stage.show();
    }
}
