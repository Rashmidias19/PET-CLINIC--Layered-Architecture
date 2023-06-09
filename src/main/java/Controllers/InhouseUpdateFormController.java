package Controllers;

import bo.BOFactory;
import bo.InhouseBO;
import com.jfoenix.controls.JFXComboBox;
import entity.Inhouse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class InhouseUpdateFormController implements Initializable {
    public AnchorPane dashboardPane;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtDescription;

    @FXML
    private JFXComboBox cmbPetID;

    @FXML
    private JFXComboBox cmbInhouseID;

    @FXML
    private TextField time;

    @FXML
    private Label lblCustID;

    @FXML
    private Label lblContact;

    @FXML
    private DatePicker AdDate;

    @FXML
    private DatePicker DisDate;
    InhouseBO inhouseBO= BOFactory.getBO(BOFactory.BOTypes.INHOUSE);



    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        loadInhouseID();
    }

    private void loadInhouseID() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = inhouseBO.loadID();

            for (String code : codes) {
                obList.add(code);
            }
            cmbInhouseID.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void loadPetID() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = inhouseBO.loadPetID();

            for (String code : codes) {
                obList.add(code);
            }
            cmbPetID.setItems(obList);
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


    public void btnSearchOnAction(ActionEvent event) {
        String InhouseID= (String) cmbInhouseID.getValue();
        try {
            Inhouse inhouse = inhouseBO.searchById(InhouseID);
            fillInhouseFields(inhouse);
            loadPetID();

            // txtQty.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void fillInhouseFields(Inhouse inhouse) {
        txtID.setText(inhouse.getInhouseID());
        cmbPetID.setPromptText(inhouse.getPetID());
        lblCustID.setText(inhouse.getCustomerID());
        AdDate.setPromptText(inhouse.getAdmittedDate());
        time.setText(inhouse.getTime());
        DisDate.setPromptText(inhouse.getDischargeDate());
        txtDescription.setText(inhouse.getDescription());
        lblContact.setText(inhouse.getContact());
    }

    public void btnUpdateOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        String InhouseID=txtID.getText();
        String PetID= (String) cmbPetID.getValue();
        String CustomerID=lblCustID.getText();
        String AdmittedDate= String.valueOf(AdDate.getValue());
        String Time=time.getText();
        String DischargeDate= String.valueOf(DisDate.getValue());
        String Description=txtDescription.getText();
        String Contact=lblContact.getText();

        try {
            boolean isUpdate = inhouseBO.update(new Inhouse(InhouseID,PetID,CustomerID,AdmittedDate,Time,DischargeDate,Description,Contact));
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Inhouse saved!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }

        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/InhouseUpdateForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();

    }
}
