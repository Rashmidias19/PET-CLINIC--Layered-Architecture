package Controllers;


import bo.*;
import bo.impl.*;
import dto.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    public AnchorPane dashboardPane;
    @FXML
    private Label lblPet;

    @FXML
    private Label lblEmployee;

    @FXML
    private Label lblCustomers;

    @FXML
    private Label lblUsers;

    @FXML
    private Label lblInhouse;

    @FXML
    private Label lblItem;

    private Connection con;
    UserBO userBO=BOFactory.getBO(BOFactory.BOTypes.USER);
    CustomerBO customerBO=BOFactory.getBO(BOFactory.BOTypes.CUSTOMER);
    PetBO petBO=BOFactory.getBO(BOFactory.BOTypes.PET);
    EmployeeBO employeeBO=BOFactory.getBO(BOFactory.BOTypes.EMPLOYEE);
    ItemBO itemBO=BOFactory.getBO(BOFactory.BOTypes.ITEM);
    InhouseBO inhouseBO=BOFactory.getBO(BOFactory.BOTypes.INHOUSE);
    @SneakyThrows
    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        loadValues();
    }



    private void loadValues() throws SQLException{
       try{
           List<User> userList= userBO.getAll();
           List<Customer> customerList= customerBO.getAll();
           List<Pet> petList= petBO.getAll();
           List<Employee> employeeList= employeeBO.getAll();
           List<Item> itemList= itemBO.getAll();
           List<Inhouse> inhouseList= inhouseBO.getAll();
           lblUsers.setText(String.valueOf(userList.size()));
           lblCustomers.setText(String.valueOf(customerList.size()));
           lblPet.setText(String.valueOf(petList.size()));
           lblEmployee.setText(String.valueOf(employeeList.size()));
           lblItem.setText(String.valueOf(itemList.size()));
           lblInhouse.setText(String.valueOf(inhouseList.size()));


       }catch (SQLException | ClassNotFoundException e){
           new Alert(Alert.AlertType.ERROR, "something happend!").show();
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



}
