package Controllers;

import com.jfoenix.controls.JFXComboBox;
import dto.Customer;
import dto.Pet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CustomerModel;
import model.PetModel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class CustomerUpdateFormController implements Initializable {

    private static final String URL = "jdbc:mysql://localhost:3306/VETCLOUD";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public AnchorPane dashboardPane;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private JFXComboBox cmbTitle;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private DatePicker date;

    @FXML
    private JFXComboBox cmbCustID;

    @FXML
    private JFXComboBox cmbGender;




    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        loadCustID();
    }

    private void loadCustID() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = CustomerModel.loadCustomerID();

            for (String code : codes) {
                obList.add(code);
            }
            cmbCustID.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
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
    public void searchbtnOnAction(ActionEvent event) {
        String CustID= (String) cmbCustID.getValue();
        try {
            Customer customer = CustomerModel.searchById(CustID);
            fillCustFields(customer);
            loadCustID();
            loadTitles();
            loadGender();

            // txtQty.requestFocus();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void fillCustFields(Customer customer) {
        txtID.setText(customer.getCustomerID());
        cmbTitle.setPromptText(customer.getCustTitle());
        txtName.setText(customer.getCustName());
        txtNIC.setText(customer.getNIC());
        date.setPromptText(customer.getDOB());
        txtAge.setText(String.valueOf(customer.getAge()));
        cmbGender.setPromptText(customer.getGender());
        txtContact.setText(customer.getContact());
        txtEmail.setText(customer.getEmail());
        txtAddress.setText(customer.getAddress());

    }

    public void btnUpdateOnAction(ActionEvent event) throws SQLException, IOException {
        String CustomerID=txtID.getText();
        String CustTitle= (String) cmbTitle.getValue();
        String CustName=txtName.getText();
        String NIC=txtNIC.getText();
        String DOB= String.valueOf(date.getValue());
        int age=Integer.parseInt(txtAge.getText());
        String Gender= (String) cmbGender.getValue();
        String contact=txtContact.getText();
        String email=txtEmail.getText();
        String address=txtAddress.getText();


        try (Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "UPDATE Customer SET CustTitle = ?,  CustName = ?, NIC = ?, DOB = ?, age = ?, Gender = ?, contact = ?, email = ?, address = ? WHERE CustomerID = ?" ;

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, CustTitle);
            pstm.setString(2, CustName);
            pstm.setString(3, NIC);
            pstm.setString(4,DOB);
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
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CustomerUpdateForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();

    }
}
