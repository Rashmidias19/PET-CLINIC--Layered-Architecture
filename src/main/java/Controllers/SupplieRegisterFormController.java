package Controllers;

import bo.BOFactory;
import bo.ItemBO;
import bo.impl.ItemBOImpl;
import dto.Item;
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


import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SupplieRegisterFormController implements Initializable {
    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private Label lblID;

    @FXML
    private TextField txtName;

    @FXML
    private DatePicker ManDate;

    @FXML
    private DatePicker ExpDate;

    @FXML
    private ComboBox cmbType;

    @FXML
    private TextField txtSupName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtPrice;
    ItemBO itemBO= BOFactory.getBO(BOFactory.BOTypes.ITEM);


    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        generateNextItemId();
        loadTypes();
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

    private void loadTypes() {
        ObservableList<String> obList = FXCollections.observableArrayList("Vitamin","Vaccine","Medicines");
        cmbType.setItems(obList);

    }

    private void generateNextItemId() {
        try {
            String id = itemBO.getNextId();
            lblID.setText(id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void savebtnOnAction(ActionEvent event) throws SQLException, IOException {
        String ItemID=lblID.getText();
        String Name=txtName.getText();
        String Man_Date= String.valueOf(ManDate.getValue());
        String Exp_Date= String.valueOf(ExpDate.getValue());
        String Supplier_name=txtSupName.getText();
        String Type= (String) cmbType.getValue();
        String Supplier_contact=txtContact.getText();
        String Description=txtDescription.getText();
        String Quantity= String.valueOf(Integer.parseInt(txtQuantity.getText()));
        Double Price= Double.valueOf(txtPrice.getText());

        try {
            boolean isSaved = itemBO.save(new Item(ItemID,Man_Date,Exp_Date,Supplier_name,Type,Supplier_contact,Description,Quantity,Price,Name));
            System.out.println(isSaved);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Operation saved!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/SupplieRegisterForm.fxml"))));
        stage.setTitle("VETCLOUD");
        stage.centerOnScreen();
        stage.show();

    }
}
