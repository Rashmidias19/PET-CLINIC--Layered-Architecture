package Controllers;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.BillModel;
import model.CustomerModel;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class CustomerRegisterFormController implements Initializable {
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
    private Label lblID;

    @FXML
    private TextField txtName;


    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtGender;

    @FXML
    private DatePicker date;

    @FXML
    private ComboBox cmbTitle;

    @FXML
    private ComboBox cmbGender;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       generateNextCustomerId();
        loadGender();
        loadTitles();
    }

    private void loadGender() {
        ObservableList<String> obList = FXCollections.observableArrayList("Male","Female","Other");
        cmbGender.setItems(obList);

    }

    private void loadTitles() {
            ObservableList<String> obList = FXCollections.observableArrayList("Miss","Mr","Mrs");
            cmbTitle.setItems(obList);

    }


    public void petbtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/PetManagementForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();
    }

    public void customerbtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CustomerManagementForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();
    }

    public void usersbtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/UserManagementForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();
    }

    public void employeebtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/EmployeeManagementForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();
    }

    public void suppliesbtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/SupplieManagementForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();
    }

    public void billingbtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/BillingManagementForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();
    }

    public void inhousebtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/InhouseManagementForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();
    }

    public void logoutbtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();
    }

    private void generateNextCustomerId() {
        try {
            String id = CustomerModel.getNextCustomerId();
            lblID.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    public void savebtnOnAction(ActionEvent event) throws SQLException {

        if(txtEmail.getText().matches("^(?:[^.\\s])\\S*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")) {
            if(Integer.parseInt(txtAge.getText())>17 && Integer.parseInt(txtAge.getText())<100 ){
                String CustomerID=lblID.getText();
                 String CustTitle= (String) cmbTitle.getValue();
                 String CustName=txtName.getText();
                 String NIC=txtNIC.getText();
                 LocalDate DOB=date.getValue();
                 int age=Integer.parseInt(txtAge.getText());
                 String Gender=(String)cmbGender.getValue();
                 String contact=txtContact.getText();
                 String email = txtEmail.getText();
                 String address=txtAddress.getText();

        try (Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "INSERT INTO Customer(CustomerID,CustTitle,CustName,NIC,DOB,age,Gender,contact,email, address)" +
                    "VALUES(?, ?, ?, ?,?,?,?,?,?,?)";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1,CustomerID);
            pstm.setString(2, CustTitle);
            pstm.setString(3, CustName);
            pstm.setString(4, NIC);
            pstm.setDate(5, java.sql.Date.valueOf(DOB));
            pstm.setInt(6,age);
            pstm.setString(7,Gender);
            pstm.setString(8,contact);
            pstm.setString(9,email);
            pstm.setString(10,address);


            int affectedRows = pstm.executeUpdate();
            if (affectedRows > 0) {
                new Alert(Alert.AlertType.CONFIRMATION,
                        "huree!! customer added :)")
                        .show();
            }

        }
        }else {
                new Alert(Alert.AlertType.ERROR, "Please enter a valid age between 18-100").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Please enter a valid email").show();

        }

    }

    }

