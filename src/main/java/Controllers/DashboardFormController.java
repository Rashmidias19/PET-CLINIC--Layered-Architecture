package Controllers;


import dao.*;
import dao.impl.*;
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
    UserDAO userDAO =new UserDAOImpl();
    CustomerDAO customerDAO =new CustomerDAOImpl();
    PetDAO petDAO =new PetDAOImpl();
    EmployeeDAO employeeDAO =new EmployeeDAOImpl();
    ItemDAO itemDAO =new ItemDAOImpl();
    InhouseDAO inhouseDAO =new InhouseDAOImpl();
    @SneakyThrows
    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        loadValues();
    }



    private void loadValues() throws SQLException{
       try{
           List<User> userList= userDAO.getAll();
           List<Customer> customerList= customerDAO.getAll();
           List<Pet> petList= petDAO.getAll();
           List<Employee> employeeList= employeeDAO.getAll();
           List<Item> itemList= itemDAO.getAll();
           List<Inhouse> inhouseList= inhouseDAO.getAll();
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
