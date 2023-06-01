package dao.impl;

import dao.EmployeeDAO;
import db.DBConnection;
import dto.Employee;
import javafx.scene.control.Alert;
import dao.impl.util.CrudUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public List<String> loadID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT EmployeeID FROM Employee");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT EmployeeID FROM Employee ORDER BY EmployeeID DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("EmployeeID");
            int newCustomerId = Integer.parseInt(id.replace("E00-", "")) + 1;
            return String.format("E00-%03d", newCustomerId);
        } else {
            return "E00-001";
        }
    }

    @Override
    public boolean save(Employee dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String splitId(String currentId) {
       return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Employee WHERE EmployeeID = ?",id);
    }

    @Override
    public List<String> loadUserID() throws SQLException, ClassNotFoundException {
       ResultSet resultSet = CrudUtil.execute("SELECT UserID FROM User");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }


    @Override
    public Employee searchById(String ID) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Employee WHERE EmployeeID =?",ID);

        if(resultSet.next()) {
            return new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10),
                    resultSet.getString(11),
                    resultSet.getBlob(12)
            );
        }
        return null;
    }

    @Override
    public boolean save(Employee employee, FileInputStream inp, File file) throws SQLException, FileNotFoundException, ClassNotFoundException {
        String sql = "INSERT INTO Employee(EmployeeID,Name,UserID,DOB,NIC,Age,Gender,address,Salary,contact,email,picture)" +
                "VALUES(?, ?, ?, ?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1, employee.getEmployeeID());
        pstm.setString(2, employee.getName());
        pstm.setString(3, employee.getUserID());
        pstm.setString(4,employee.getDOB());
        pstm.setString(5, employee.getNIC());
        pstm.setInt(6,employee.getAge());
        pstm.setString(7,employee.getGender());
        pstm.setString(8, employee.getAddress());
        pstm.setDouble(9, Double.parseDouble(employee.getSalary()));
        pstm.setString(10, employee.getContact());
        pstm.setString(11,employee.getEmail());
        inp = new FileInputStream(file);
        pstm.setBinaryStream(12, (InputStream) inp, (int) file.length());
        int affectedRows = pstm.executeUpdate();
        boolean isSaved=false;
        if (affectedRows > 0) {
            isSaved=true;
        }
        return isSaved;

    }

    @Override
    public List<Employee> getAll() throws SQLException, ClassNotFoundException {
        List<Employee> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Employee");

        while (resultSet.next()) {
            data.add(new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10),
                    resultSet.getString(11),
                    resultSet.getBlob(12)
            ));
        }
        return data;
    }
    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Employee SET Name = ?,  UserID = ?, DOB = ?, NIC = ?, Age = ?, gender = ?, address = ?, salary = ?, contact = ?, email = ? WHERE EmployeeID = ?" ;

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1, employee.getName());
        pstm.setString(2, employee.getUserID());
        pstm.setString(3, employee.getDOB());
        pstm.setString(4,employee.getNIC());
        pstm.setInt(5,employee.getAge());
        pstm.setString(6,employee.getGender());
        pstm.setString(7,employee.getAddress());
        pstm.setString(8,employee.getSalary());
        pstm.setString(9,employee.getContact());
        pstm.setString(10,employee.getEmail());
        pstm.setString(11,employee.getEmployeeID());

        boolean isUpdated = pstm.executeUpdate() > 0;
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "yes! updated!!").show();
        }
        return isUpdated;
    }


}
