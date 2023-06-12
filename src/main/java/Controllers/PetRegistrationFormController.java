package Controllers;

import bo.BOFactory;
import bo.PetBO;
import entity.Pet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PetRegistrationFormController implements Initializable {
    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private Label lblID;

    @FXML
    private TextField txtName;


    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtImage;

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
    private Label lblImg;

    @FXML
    private ComboBox cmbCustomerID;

    private FileChooser fileChooser;

    private File file;

    private Desktop desktop=Desktop.getDesktop();

    private FileInputStream inp;

    private FileInputStream fsp;

    private Circle circle;
    PetBO petBO= BOFactory.getBO(BOFactory.BOTypes.PET);


    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        generateNextPetId();
        loadGender();
        loadCustID();
        loadSpecies();
    }

    public void petbtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/PetManagementForm.fxml"))));
        stage.setTitle("VETCLOUD");
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
            List<String> codes = petBO.loadCustomerID();

            for (String code : codes) {
                obList.add(code);
            }
            cmbCustomerID.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

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
    private void generateNextPetId() {
        try {
            String id = petBO.getNextId();
            lblID.setText(id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void savebtnOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        String PetID=lblID.getText();
        String Name=txtName.getText();
        String CustomerID= (String) cmbCustomerID.getValue();
        String Type= (String) cmbSpecies.getValue();
        String Breed=txtBreed.getText();
        String Gender= (String) cmbGender.getValue();
        String DOB= String.valueOf(LocalDate.parse(String.valueOf(date.getValue())));
        int age=Integer.parseInt(txtAge.getText());
        String address=txtAddress.getText();
        String contact=txtContact.getText();

        try {
            boolean isSaved = petBO.saveWithPicture(new Pet(PetID,Name,CustomerID,Type,Breed,Gender,DOB,age,address,contact),inp,file);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Pet Saved!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }

        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/PetRegisterForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();

    }

    public void btnAddOnAction(ActionEvent event) throws FileNotFoundException {

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif","*.")
        );
        fileChooser.setTitle("Choose File");
        file = fileChooser.showOpenDialog(null);
        inp=new FileInputStream(file);
        if (file != null){
            try {
                desktop.open(file);
                txtImage.setText(file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
