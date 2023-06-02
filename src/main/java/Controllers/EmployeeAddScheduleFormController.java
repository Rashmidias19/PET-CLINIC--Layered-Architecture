package Controllers;

import com.jfoenix.controls.JFXComboBox;
import dao.CrudDAO;
import dao.EmpSchedDAO;
import dao.EmployeeDAO;
import dao.EmployeeScheduleDAO;
import dao.impl.EmpSchedDAOImpl;
import dao.impl.EmployeeDAOImpl;
import dao.impl.EmployeeScheduleDAOImpl;
import dto.Employee;
import dto.EmployeeSchedule;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeAddScheduleFormController implements Initializable {
    public AnchorPane dashboardPane;
    @FXML
    private Label lblID;

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
    CrudDAO<EmployeeSchedule,String, FileInputStream, File> employeeScheduleDAO=new EmployeeScheduleDAOImpl();
    CrudDAO<Employee,String, FileInputStream, File> employeeDAO=new EmployeeDAOImpl();
    CrudDAO<EmployeeSchedule,String, FileInputStream, File> empSchedDAO=new EmpSchedDAOImpl();

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        loadShift();
        loadEmployeeID();
        generateNextEmpSchedId();

    }

    private void generateNextEmpSchedId() {
        try {
            String id = employeeScheduleDAO.getNextId();
            lblID.setText(id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
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

    private void loadEmployeeID() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = employeeDAO.loadID();

            for (String code : codes) {
                obList.add(code);
            }
            cmbEmployeeID.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
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
            Employee employee = employeeDAO.searchById(ID);
            fillEmployeeFields(employee);

            // txtQty.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void savebtnOnAction(ActionEvent event) throws SQLException, IOException {
        String ScheduleID=lblID.getText();
        String EmployeeID= (String) cmbEmployeeID.getValue();
        String Name=lblName.getText();
        String Date= String.valueOf(date.getValue());
        String Time= time.getText();
        String WorkTime=worktime.getText();
        String Shift= (String) cmbShift.getValue();
        String OT=Ot.getText();

        boolean isAdd = false;
        try {
            //gdfgfdhfgh
            isAdd = employeeScheduleDAO.save(new EmployeeSchedule(ScheduleID, EmployeeID,Name,Date,Time,WorkTime,Shift,OT));
            if(isAdd) {
                new Alert(Alert.AlertType.CONFIRMATION, "Schedule added").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Not added").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error").show();
        }
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/EmployeeAddScheduleForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();
    }

    public void backbtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/EmployeeScheduleManagementForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();
    }
}
