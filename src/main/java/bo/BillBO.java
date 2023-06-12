package bo;

import entity.Bill;
import entity.Customer;
import entity.Item;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BillBO extends SuperBO {
    List<String> loadID() throws SQLException, ClassNotFoundException;

    List<Bill> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    ArrayList<String> loadCustomerID() throws SQLException, ClassNotFoundException;

    List<String> loadItemID() throws SQLException, ClassNotFoundException;

    String getNextId() throws SQLException, ClassNotFoundException;

    String splitId(String currentId);

    boolean save(Bill bill) throws SQLException, ClassNotFoundException, FileNotFoundException;

    Item searchByItemId(String ID) throws SQLException, ClassNotFoundException;

    Customer searchByCustomerId(String ID) throws SQLException, ClassNotFoundException;
}
