package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    @FXML
    public Button btnRegister;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private Button btnLogin;

    @FXML
    private  AnchorPane dashboardPane;

    public void loginButtonOnAction(ActionEvent event) throws IOException {

        if((txtUserName.getText().isEmpty()||txtUserName.getText().isBlank())||(txtPassword.getText().isEmpty()||txtPassword.getText().isBlank())){
            new Alert(Alert.AlertType.ERROR, "Please enter the username or password").show();
        }

        if(txtUserName.getText().matches("^(?:[^.\\s])\\S*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")){
            if(txtUserName.getText().equals("Rashmidias819@gmail.com") || txtPassword.equals("1234")){
                Stage stage = (Stage) dashboardPane.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"))));
                stage.setTitle("Item Form");
                stage.centerOnScreen();
                stage.show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Please enter correct username or password").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Please enter a valid email").show();
        }

        //else{
         //   new Alert(Alert.AlertType.ERROR, "Please enter correct username or password").show();
       // }



    }

    public void registerButtonOnAction(ActionEvent event) {
    }
}
