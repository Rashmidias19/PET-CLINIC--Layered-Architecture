package Controllers;

import bo.BOFactory;
import bo.PetBO;
import bo.impl.PetBOImpl;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PetUpdateFormController implements Initializable {

    public AnchorPane dashboardPane;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtBreed;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtContact;

    @FXML
    private DatePicker date;

    @FXML
    private JFXComboBox cmbPetID;

    @FXML
    private JFXComboBox cmbSpecies;

    @FXML
    private JFXComboBox cmbGender;

    @FXML
    private JFXComboBox cmbCustID;

   @FXML
    private Circle circle;
    private Connection conn;
    PetBO petBO= BOFactory.getBO(BOFactory.BOTypes.PET);

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        loadPetID();
    }

    private void loadPetID() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = petBO.loadID();

            for (String code : codes) {
                obList.add(code);
            }
            cmbPetID.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void loadCustID() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = petBO.loadCustomerID();

            for (String code : codes) {
                obList.add(code);
            }
            cmbCustID.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

    }
    private void loadGender() {
        ObservableList<String> obList = FXCollections.observableArrayList("Male","Female");
        cmbGender.setItems(obList);

    }
    private void loadTypes() {
        ObservableList<String> obList = FXCollections.observableArrayList("Dog","Cat","Bird","Other");
        cmbSpecies.setItems(obList);

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
    public void searchbtnOnAction(ActionEvent event) throws FileNotFoundException {
        String PetID= (String) cmbPetID.getValue();
        try {
            Pet pet = petBO.searchById(PetID);
            fillPetFields(pet);
            loadCustID();
            loadTypes();
            loadGender();
            loadImage(pet);

            // txtQty.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

    }

    private void loadImage(Pet pet) throws FileNotFoundException, SQLException {
        InputStream is=null;
        if(pet.getPicture()==null){
            is=new FileInputStream("E:\\PetVet\\Clinic\\src\\main\\resources\\img\\images.png");

        }else{
            is=pet.getPicture().getBinaryStream();
        }
        Image image=new Image(is);
        circle.setFill(new ImagePattern(image));
    }

        private void fillPetFields(Pet pet) {
        txtID.setText(pet.getPetID());
        txtName.setText(pet.getName());
        cmbCustID.setPromptText(pet.getCustomerID());
        cmbSpecies.setPromptText(pet.getType());
        txtBreed.setText(pet.getBreed());
        cmbGender.setPromptText(pet.getGender());
        date.setPromptText(pet.getDOB());
        txtAge.setText(String.valueOf(pet.getAge()));
        txtAddress.setText(pet.getAddress());
        txtContact.setText(pet.getContact());

    }


    public void btnUpdateOnAction(ActionEvent event) throws SQLException, IOException {
        String PetID=txtID.getText();
        String Name=txtName.getText();
        String CustomerID= (String) cmbCustID.getValue();
        String Type= (String) cmbSpecies.getValue();
        String Breed=txtBreed.getText();
        String Gender= (String) cmbGender.getValue();
        String DOB= String.valueOf(LocalDate.parse(String.valueOf(date.getValue())));
        int age=Integer.parseInt(txtAge.getText());
        String address=txtAddress.getText();
        String contact=txtContact.getText();

        try {
            boolean isSaved = petBO.update(new Pet(PetID,Name,CustomerID,Type,Breed,Gender,DOB,age,address,contact));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Pet Updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/PetUpdateForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();

    }

    public void cmbPetIDOnAction(ActionEvent event) {
    }
}
