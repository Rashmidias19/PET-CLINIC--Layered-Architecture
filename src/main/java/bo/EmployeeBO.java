package bo;

import dto.Employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    List<String> loadID() throws SQLException, ClassNotFoundException;

    String getNextId() throws SQLException, ClassNotFoundException;

    String splitId(String currentId);

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    List<String> loadUserID() throws SQLException, ClassNotFoundException;

    Employee searchById(String ID) throws SQLException, ClassNotFoundException;

    boolean saveWithPicture(Employee employee, FileInputStream inp, File file) throws SQLException, FileNotFoundException, ClassNotFoundException;

    List<Employee> getAll() throws SQLException, ClassNotFoundException;

    boolean update(Employee employee) throws SQLException, ClassNotFoundException;
}
