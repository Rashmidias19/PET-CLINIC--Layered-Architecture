package Controllers;

import bo.BOFactory;
import bo.EmployeeBO;
import bo.impl.EmployeeBOImpl;
import dto.Employee;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeRegisterFormController implements Initializable {
    public AnchorPane dashboardPane;

    @FXML
    private Label lblID;

    @FXML
    private TextField txtName;


    @FXML
    private TextField txtAge;


    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtContact;

    @FXML
    private DatePicker date;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtEmail;

    @FXML
    private ComboBox cmbGender;

    @FXML
    private ComboBox cmbUserID;

    @FXML
    private TextField txtImage;

    private FileChooser fileChooser;

    private File file;

    private Desktop desktop=Desktop.getDesktop();
    EmployeeBO employeeBO= BOFactory.getBO(BOFactory.BOTypes.EMPLOYEE);

    private FileInputStream inp;

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        generateNextEmpId();
        loadGender();
        loadUserID();

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

    private void loadGender() {
        ObservableList<String> obList = FXCollections.observableArrayList("Male", "Female");
        cmbGender.setItems(obList);

    }

    private void loadUserID() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = employeeBO.loadUserID();

            for (String code : codes) {
                obList.add(code);
            }
            cmbUserID.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

    }

    private void generateNextEmpId() {
        try {
            String id = employeeBO.getNextId();
            lblID.setText(id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void savebtnOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {

        if (txtEmail.getText().matches("^(?:[^.\\s])\\S*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")) {
            if (Integer.parseInt(txtAge.getText()) > 19 && Integer.parseInt(txtAge.getText()) < 70) {

                String EmployeeID = lblID.getText();
                String Name = txtName.getText();
                String UserID = (String) cmbUserID.getValue();
                LocalDate DOB = date.getValue();
                String NIC = txtNIC.getText();
                int Age = Integer.parseInt(txtAge.getText());
                String Gender = (String) cmbGender.getValue();
                String address = txtAddress.getText();
                String Salary = txtSalary.getText();
                String contact = txtContact.getText();
                String email = txtEmail.getText();

                try {
                    boolean isSaved = employeeBO.saveWithPicture(new Employee(EmployeeID,Name,UserID,DOB,NIC,Age,Gender,address,Salary,contact,email),inp,file);
                    if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!").show();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
                }

                }else{
                    new Alert(Alert.AlertType.ERROR, "Please enter a valid age between 20-70").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please enter a valid email").show();

            }
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/EmployeeRegisterForm.fxml"))));
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

