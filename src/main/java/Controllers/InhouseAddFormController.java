package Controllers;

import com.jfoenix.controls.JFXComboBox;
import dao.CrudDAO;
import dao.InhouseDAO;
import dao.impl.EmployeeDAOImpl;
import dao.impl.InhouseDAOImpl;
import dao.impl.PetDAOImpl;
import dto.Employee;
import dto.Inhouse;
import dto.Pet;
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
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class InhouseAddFormController implements Initializable {

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private Label lblID;

    @FXML
    private TextField txtDescription;

    @FXML
    private DatePicker AdDate;

    @FXML
    private DatePicker DisDate;

    @FXML
    private TextField time;

    @FXML
    private Label lblCustomerID;

    @FXML
    private Label lblContact;


    @FXML
    private Label lblTotal;

    @FXML
    private JFXComboBox cmbPetID;
    CrudDAO<Inhouse,String, FileInputStream, File> inhouseDAO =new InhouseDAOImpl();
    CrudDAO<Pet,String, FileInputStream, File> petDAO =new PetDAOImpl();

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        generateNextInId();
        loadPetID();

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

    private void loadPetID() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = petDAO.loadID();

            for (String code : codes) {
                obList.add(code);
            }
            cmbPetID.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

    }

    private void generateNextInId() {
        try {
            String id = inhouseDAO.getNextId();
            lblID.setText(id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void savebtnOnAction(ActionEvent event) throws SQLException, IOException {
        String InhouseID=lblID.getText();
        String PetID= (String) cmbPetID.getValue();
        String CustomerID= lblCustomerID.getText();
        String AdmittedDate= String.valueOf(AdDate.getValue());
        String Time= time.getText();
        String DischargeDate= String.valueOf(DisDate.getValue());
        String contact=lblContact.getText();
        String Description=txtDescription.getText();

        try {
            boolean isSaved = inhouseDAO.save(new Inhouse(InhouseID,PetID,CustomerID,AdmittedDate,Time,DischargeDate,Description,contact));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer saved!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/InhouseAddForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();

    }


    public void cmbPetIDOnAction(ActionEvent event) {
        String ID = (String) cmbPetID.getValue();
        try {
            Pet pet = petDAO.searchById(ID);
            fillPetFields(pet);

            // txtQty.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }


    private void fillPetFields(Pet pet) throws SQLException {
        lblCustomerID.setText(String.valueOf(pet.getCustomerID()));
        lblContact.setText(String.valueOf(pet.getContact()));

    }





}
