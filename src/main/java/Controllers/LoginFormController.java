package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public Button btnLogin;
    public TextField txtUserName;
    public TextField txtPassword;
    public ImageView loginPane;
    public Button btnRegister;
    public AnchorPane dashboardPane;

    public void loginButtonOnAction(ActionEvent event) throws IOException {

        if(txtUserName.getText().equals("Rashmi19") && txtPassword.getText().equals("1234")){
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"))));
            stage.setTitle("Item Form");
            stage.centerOnScreen();
            stage.show();
        }
    }

    public void registerButtonOnAction(ActionEvent event) {
    }
}
