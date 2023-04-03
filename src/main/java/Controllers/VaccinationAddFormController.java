package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;




public class VaccinationAddFormController implements Initializable{
        public AnchorPane dashboardPane;
        private static final String URL = "jdbc:mysql://localhost:3306/VETCLOUD";
        private static final Properties props = new Properties();

        static {
            props.setProperty("user", "root");
            props.setProperty("password", "1234");
        }
        public void petbtnOnAction(ActionEvent event) throws IOException {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/PetManagementForm.fxml"))));
            stage.setTitle("Item Form");
            stage.centerOnScreen();
            stage.show();
        }

        public void customerbtnOnAction(ActionEvent event) throws IOException {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CustomerManagementForm.fxml"))));
            stage.setTitle("Item Form");
            stage.centerOnScreen();
            stage.show();
        }

        public void usersbtnOnAction(ActionEvent event) throws IOException {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/UserManagementForm.fxml"))));
            stage.setTitle("Item Form");
            stage.centerOnScreen();
            stage.show();
        }

        public void employeebtnOnAction(ActionEvent event) throws IOException {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/EmployeeManagementForm.fxml"))));
            stage.setTitle("Item Form");
            stage.centerOnScreen();
            stage.show();
        }

        public void suppliesbtnOnAction(ActionEvent event) throws IOException {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/SupplieManagementForm.fxml"))));
            stage.setTitle("Item Form");
            stage.centerOnScreen();
            stage.show();
        }

        public void billingbtnOnAction(ActionEvent event) throws IOException {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/BillingManagementForm.fxml"))));
            stage.setTitle("Item Form");
            stage.centerOnScreen();
            stage.show();
        }

        public void inhousebtnOnAction(ActionEvent event) throws IOException {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/InhouseManagementForm.fxml"))));
            stage.setTitle("Item Form");
            stage.centerOnScreen();
            stage.show();
        }

        public void logoutbtnOnAction(ActionEvent event) throws IOException {
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
            stage.setTitle("Item Form");
            stage.centerOnScreen();
            stage.show();
        }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {


    }
}

