package Controllers;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

public class CustomerRegisterFormController {
    private static final String URL = "jdbc:mysql://localhost:3306/VETCLOUD";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private JFXComboBox<String> cmbTitle;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private JFXComboBox<String> cmbGender;

    @FXML
    private DatePicker Date;




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
    @FXML
    public void savebtnOnAction(ActionEvent event) throws SQLException {
        String CustomerID=txtID.getText();
        String CustTitle=cmbTitle.getValue();
        String CustName=txtName.getText();
        String NIC=txtNIC.getText();
        LocalDate DOB=Date.getValue();
        int age=Integer.parseInt(txtAge.getText());
        String Gender=cmbGender.getValue();
        String contact=txtContact.getText();
        String email=txtEmail.getText();
        String address=txtAddress.getText();

        try (Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "UPDATE Customer SET CustTitle= ?, CustName= ?, NIC = ?, DOB= ?, age= ?, Gender = ?, " +
                    "contact = ?, email =?, address = ? WHERE CustID = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, CustTitle);
            pstm.setString(2, CustName);
            pstm.setString(3, NIC);
            pstm.setDate(4, java.sql.Date.valueOf(DOB));
            pstm.setInt(5,age);
            pstm.setString(6,Gender);
            pstm.setString(7,contact);
            pstm.setString(8,email);
            pstm.setString(9,address);
            pstm.setString(10,CustomerID);

            boolean isUpdated = pstm.executeUpdate() > 0;
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "yes! updated!!").show();
            }
        }

    }
}
