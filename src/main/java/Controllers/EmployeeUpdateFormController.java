package Controllers;

import bo.BOFactory;
import bo.EmployeeBO;
import com.jfoenix.controls.JFXComboBox;
import entity.Employee;
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
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeUpdateFormController implements Initializable {
    public AnchorPane dashboardPane;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private JFXComboBox cmbUserID;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSalary;

    @FXML
    private DatePicker date;

    @FXML
    private JFXComboBox cmbGender;

    @FXML
    private JFXComboBox cmbEmployeeID;

    @FXML
    private Circle circle;
    EmployeeBO employeeBO= BOFactory.getBO(BOFactory.BOTypes.EMPLOYEE);


    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        loadEmployeeID();
    }

    private void loadEmployeeID() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = employeeBO.loadID();

            for (String code : codes) {
                obList.add(code);
            }
            cmbEmployeeID.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
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
        ObservableList<String> obList = FXCollections.observableArrayList("Male","Female");
        cmbGender.setItems(obList);

    }


    public void savebtnOnAction(ActionEvent event) {

    }

    public void searchbtnOnAction(ActionEvent event) {
        String EmployeeID= (String) cmbEmployeeID.getValue();
        try {
            Employee employee = employeeBO.searchById(EmployeeID);
            fillEmployeeFields(employee);
            loadUserID();
            loadGender();
            loadImage(employee);

            // txtQty.requestFocus();
        } catch (SQLException | FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void loadImage(Employee employee) throws FileNotFoundException, SQLException {
        InputStream is=null;
        if(employee.getPicture()==null){
            is=new FileInputStream("E:\\PetVet\\Clinic\\src\\main\\resources\\img\\images.png");
        }else{
            is=employee.getPicture().getBinaryStream();
        }
        Image image=new Image(is);
        circle.setFill(new ImagePattern(image));
    }

    private void fillEmployeeFields(Employee employee) {
        txtID.setText(employee.getEmployeeID());
        txtName.setText(employee.getName());
        cmbUserID.setPromptText(employee.getUserID());
        date.setPromptText(employee.getDOB());
        txtNIC.setText(employee.getNIC());
        txtAge.setText(String.valueOf(employee.getAge()));
        cmbGender.setPromptText(employee.getGender());
        txtAddress.setText(employee.getAddress());
        txtSalary.setText(employee.getSalary());
        txtContact.setText(employee.getContact());
        txtEmail.setText(employee.getEmail());

    }

    public void updatebtnOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        String EmployeeID=txtID.getText();
        String Name=txtName.getText();
        String UserID= (String) cmbUserID.getValue();
        String DOB= String.valueOf(date.getValue());
        String NIC=txtNIC.getText();
        int Age=Integer.parseInt(txtAge.getText());
        String gender= (String) cmbGender.getValue();
        String address=txtAddress.getText();
        String salary=txtSalary.getText();
        String contact=txtContact.getText();
        String email=txtEmail.getText();

        try {
            boolean isUpdate = employeeBO.update(new Employee(EmployeeID,Name,UserID,DOB,NIC,Age,gender,address,salary,contact,email));
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Operation saved!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/EmployeeUpdateForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();

    }
}
