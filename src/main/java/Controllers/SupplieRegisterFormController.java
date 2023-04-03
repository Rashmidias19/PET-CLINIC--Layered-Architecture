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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;

public class SupplieRegisterFormController implements Initializable {
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
    private DatePicker ManDate;

    @FXML
    private DatePicker ExpDate;

    @FXML
    private ComboBox cmbType;

    @FXML
    private TextField txtSupName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQuantity;



    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        loadTypes();
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

    private void loadTypes() {
        ObservableList<String> obList = FXCollections.observableArrayList("Vitamin","Vaccine","Medicines");
        cmbType.setItems(obList);

    }

    public void savebtnOnAction(ActionEvent event) throws SQLException {
        String ItemID=txtID.getText();
        String Name=txtName.getText();
        LocalDate Man_Date=ManDate.getValue();
        LocalDate Exp_Date=ExpDate.getValue();
        String Supplier_name=txtSupName.getText();
        String Type= (String) cmbType.getValue();
        String Supplier_contact=txtContact.getText();
        String Description=txtDescription.getText();
        String Quantity=txtQuantity.getText();



        try (Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "INSERT INTO Item(ItemID,Name,Man_Date,Exp_Date,Supplier_name,Type,Supplier_contact,Description,Quantity)" +
                    "VALUES(?, ?, ?, ?,?,?,?,?,?)";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1,ItemID);
            pstm.setString(2, Name);
            pstm.setDate(3, java.sql.Date.valueOf(Man_Date));
            pstm.setDate(4, java.sql.Date.valueOf(Exp_Date));
            pstm.setString(5,Supplier_name);
            pstm.setString(6,Type);
            pstm.setString(7,Supplier_contact);
            pstm.setString(8,Description);
            pstm.setString(9, Quantity);


            int affectedRows = pstm.executeUpdate();
            if (affectedRows > 0) {
                new Alert(Alert.AlertType.CONFIRMATION,
                        "huree!! customer added :)")
                        .show();
            }

        }

    }
}
