package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CustomerModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class PetRegistrationFormController implements Initializable {
    private static final String URL = "jdbc:mysql://localhost:3306/VETCLOUD";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;


    @FXML
    private TextField txtAge;


    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtBreed;

    @FXML
    private TextField txtContact;

    @FXML
    private DatePicker date;

    @FXML
    private ComboBox cmbSpecies;

    @FXML
    private ComboBox cmbGender;

    @FXML
    private ComboBox cmbCustomID;


    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        loadGender();
        loadCustID();
        loadSpecies();
    }

    public void petbtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/PetManagementForm.fxml"))));
        stage.setTitle("Item Form");
        stage.centerOnScreen();
        stage.show();
    }
    private void loadGender() {
        ObservableList<String> obList = FXCollections.observableArrayList("Male","Female");
        cmbGender.setItems(obList);

    }
    private void loadSpecies() {
        ObservableList<String> obList = FXCollections.observableArrayList("Dog","Cat","Bird","Other");
        cmbSpecies.setItems(obList);

    }
    private void loadCustID() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = CustomerModel.loadCustomerID();

            for (String code : codes) {
                obList.add(code);
            }
            cmbCustomID.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

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

    public void savebtnOnAction(ActionEvent event) throws SQLException {
        String PetID=txtID.getText();
        String Name=txtName.getText();
        String CustomerID= (String) cmbCustomID.getValue();
        String Type= (String) cmbSpecies.getValue();
        String Breed=txtBreed.getText();
        String Gender= (String) cmbGender.getValue();
        LocalDate DOB=date.getValue();
        int age=Integer.parseInt(txtAge.getText());
        String address=txtAddress.getText();
        String contact=txtContact.getText();

        try (Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "INSERT INTO Pet(PetID,Name,CustomerID,Type ,Breed,Gender,DOB,age,address,contact )" +
                    "VALUES(?, ?, ?, ?,?,?,?,?,?,?)";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1,PetID);
            pstm.setString(2, Name);
            pstm.setString(3, CustomerID);
            pstm.setString(4, Type);
            pstm.setString(5,Breed);
            pstm.setString(6,Gender);
            pstm.setDate(7, java.sql.Date.valueOf(DOB));
            pstm.setInt(8,age);
            pstm.setString(9,address);
            pstm.setString(10,contact);


            int affectedRows = pstm.executeUpdate();
            if (affectedRows > 0) {
                new Alert(Alert.AlertType.CONFIRMATION,
                        "huree!! customer added :)")
                        .show();
            }

        }

    }
}
