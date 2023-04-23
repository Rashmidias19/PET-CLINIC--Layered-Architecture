package Controllers;

import db.DBConnection;
import dto.Customer;
import dto.Item;
import dto.tm.CartTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.BillModel;
import model.CustomerModel;
import model.ItemModel;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class BillAddFormController implements Initializable {
    private static final String URL = "jdbc:mysql://localhost:3306/VETCLOUD";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private Label lblID;

    @FXML
    private TextField txtDescription;


    @FXML
    private Label lblName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblPrice;

    @FXML
    private ComboBox cmbCustomID;

    @FXML
    private ComboBox cmbItemID;

    @FXML
    private ComboBox cmbQuantity;

    @FXML
    private TableColumn<?, ?> colItemID;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private Label lblContact;

    @FXML
    private Label lblEmail;

    @FXML
    private TableView<CartTM> tblOrder;

    private ObservableList<CartTM> obList = FXCollections.observableArrayList();




    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        generateNextBillId();
        loadCustID();
        loadItemID();
        loadQuantity();
        setCellValueFactory();
        setOrderDateTime();
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

    private void setOrderDateTime() {
        lblDate.setText(String.valueOf(LocalDate.now()));
        lblTime.setText(String.valueOf(LocalTime.now()));
    }


    void setCellValueFactory() {
        colItemID.setCellValueFactory(new PropertyValueFactory<>("ItemID"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));

    }

    private void loadQuantity() {
        ObservableList<Integer> obList = FXCollections.observableArrayList();
        int num[]=new int[100];
        for (int i = 1; i <=100 ; i++) {
            num[i-1]=i;
        }
        for (int number:num) {
            obList.add(number);
        }
        cmbQuantity.setItems(obList);

    }

    private void loadCustID() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = CustomerModel.loadCustomerID();

            for (String code : codes) {
                obList.add(code);
            }
            cmbCustomID.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

    }

    private void loadItemID() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = ItemModel.loadItemID();

            for (String code : codes) {
                obList.add(code);
            }
            cmbItemID.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

    }

    private void generateNextBillId() {
        try {
            String id = BillModel.getNextBillId();
            lblID.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void savebtnOnAction(ActionEvent event) throws SQLException {
        cmbCustomerOnAction(event);
        String BillID=lblID.getText();
        String CustomerID= (String) cmbCustomID.getValue();
        LocalDate Date= LocalDate.parse(lblDate.getText());
        LocalTime Time= LocalTime.parse(lblTime.getText());
        double Amount= Double.parseDouble(lblTotal.getText());
        String contact=lblContact.getText();
        String email=lblEmail.getText();
        String Description=txtDescription.getText();

        try (Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "INSERT INTO Bill(BillID,CustomerID,Date,Time,Amount,contact,email,Description)" +
                    "VALUES(?, ?, ?, ?,?,?,?,?)";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1,BillID);
            pstm.setString(2, CustomerID);
            pstm.setDate(3, java.sql.Date.valueOf(Date));
            pstm.setTime(4, java.sql.Time.valueOf(Time));
            pstm.setDouble(5,Amount);
            pstm.setString(6,contact);
            pstm.setString(7,email);
            pstm.setString(8,Description);


            int affectedRows = pstm.executeUpdate();
            if (affectedRows > 0) {
                new Alert(Alert.AlertType.CONFIRMATION,
                        "huree!! customer added :)")
                        .show();
            }

        }


    }

    private void fillItemFields(Item item) {
        lblPrice.setText(String.valueOf(item.getPrice()));
        lblName.setText(String.valueOf(item.getName()));
    }
    @FXML
    public void selectbtnOnAction(ActionEvent event) {

        String ItemID = (String) cmbItemID.getValue();
        String Name=lblName.getText();
        int Quantity = (int) cmbQuantity.getValue();
        double Price=Double.parseDouble(lblPrice.getText());

        CartTM tm = new CartTM(ItemID, Name, Quantity, Price);

        obList.add(tm);
        tblOrder.setItems(obList);
        //calculateNetTotal();
        double netTotal = 0.0;
        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            double total = Quantity*Price;
            netTotal += total;
        }
        lblTotal.setText(String.valueOf(netTotal));

    }



    public void backbtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/BillingManagementForm.fxml"))));
        stage.setTitle("Item Form");
        stage.centerOnScreen();
        stage.show();
    }

    public void cmbItemOnAction(ActionEvent event) {
        String ID = (String) cmbItemID.getValue();
        try {
            Item item = ItemModel.searchById(ID);
            fillItemFields(item);

           // txtQty.requestFocus();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void fillCustomerFields(Customer customer) {
        lblContact.setText(String.valueOf(customer.getContact()));
        lblEmail.setText(String.valueOf(customer.getEmail()));
    }

    public void cmbCustomerOnAction(ActionEvent event) {
        String ID = (String) cmbCustomID.getValue();
        try {
             Customer customer = CustomerModel.searchById(ID);
             fillCustomerFields(customer);

            // txtQty.requestFocus();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

}
