package Controllers;

import com.jfoenix.controls.JFXComboBox;
import dto.Customer;
import dto.Employee;
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
import model.CustomerModel;
import model.EmployeeModel;
import model.PetModel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class EmployeeAddScheduleFormController implements Initializable {
    public AnchorPane dashboardPane;
    private static final String URL = "jdbc:mysql://localhost:3306/VETCLOUD";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }


    @FXML
    private TextField txtID;

    @FXML
    private JFXComboBox cmbEmployeeID;


    @FXML
    private Label lblName;


    @FXML
    private DatePicker date;

    @FXML
    private TextField time;

    @FXML
    private TextField worktime;

    @FXML
    private JFXComboBox cmbShift;

    @FXML
    private TextField Ot;




    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        loadShift();
        loadEmployeeID();


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

    private void loadEmployeeID() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = EmployeeModel.loadEmployeeID();

            for (String code : codes) {
                obList.add(code);
            }
            cmbEmployeeID.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

    }

    private void loadShift() {
        ObservableList<String> obList = FXCollections.observableArrayList("Day","Night");
        cmbShift.setItems(obList);

    }

    private void fillEmployeeFields(Employee employee) {
        lblName.setText(String.valueOf(employee.getName()));
    }

    public void cmbIDOnAction(ActionEvent event) {
        String ID = (String) cmbEmployeeID.getValue();
        try {
            Employee employee = EmployeeModel.searchById(ID);
            fillEmployeeFields(employee);;

            // txtQty.requestFocus();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void savebtnOnAction(ActionEvent event) throws SQLException {
        String ScheduleID=txtID.getText();
        String EmployeeID= (String) cmbEmployeeID.getValue();
        String Name=lblName.getText();
        LocalDate Date= date.getValue();
        String Time= time.getText();
        String WorkTime=worktime.getText();
        String Shift= (String) cmbShift.getValue();
        String OT=Ot.getText();

        try (Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "INSERT INTO EmployeeSchedule(ScheduleID,EmployeeID,Name,Date,Time,WorkTime,Shift,OT)" +
                    "VALUES(?, ?, ?, ?,?,?,?,?)";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1,ScheduleID);
            pstm.setString(2, EmployeeID);
            pstm.setString(3, Name);
            pstm.setDate(4, java.sql.Date.valueOf(Date));
            pstm.setTime(5, java.sql.Time.valueOf(Time));
            pstm.setString(6,WorkTime);
            pstm.setString(7,Shift);
            pstm.setString(8,OT);


            int affectedRows = pstm.executeUpdate();
            if (affectedRows > 0) {
                new Alert(Alert.AlertType.CONFIRMATION,
                        "huree!! customer added :)")
                        .show();
            }

        }
    }
}
