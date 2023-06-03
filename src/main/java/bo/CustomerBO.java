package bo;

import dto.Customer;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    String splitId(String currentId);

    List<Customer> getAll() throws SQLException, ClassNotFoundException;

    List<String> loadID() throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    String getNextId() throws SQLException, ClassNotFoundException;

    boolean save(Customer customer) throws SQLException, FileNotFoundException, ClassNotFoundException;

    Customer searchById(String ID) throws SQLException, ClassNotFoundException;

    boolean update(Customer customer) throws SQLException, ClassNotFoundException;
}
