package dao.impl;

import dao.CustomerDAO;
import db.DBConnection;
import dto.Customer;
import javafx.scene.control.Alert;
import dao.impl.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        List<Customer> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer");

        while (resultSet.next()) {
            data.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10)
            ));
        }
        return data;
    }

    @Override
    public List<String> loadID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT CustomerID FROM Customer");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Customer WHERE CustomerID = ?",id);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
      ResultSet rst = CrudUtil.execute("SELECT CustomerID FROM customer ORDER BY CustomerID DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("CustomerID");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }


    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Customer(CustomerID,CustTitle,CustName,NIC,DOB,age,Gender,contact,email, address)" +
                "VALUES(?, ?, ?, ?,?,?,?,?,?,?)";
        return CrudUtil.execute(
                sql,
                customer.getCustomerID(),
                customer.getCustTitle(),
                customer.getCustName(),
                customer.getNIC(),
                customer.getDOB(),
                customer.getAge(),
                customer.getGender(),
                customer.getContact(),
                customer.getEmail(),
                customer.getAddress());
    }

    @Override
    public Customer searchById(String ID) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer WHERE CustomerID =?",ID);

        if(resultSet.next()) {
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10)

            );
        }
        return null;
    }

    @Override
    public String splitId(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("C000");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "C000" + id;
        }
        return "C0001";
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
//
        String sql = "UPDATE Customer SET CustTitle = ?,  CustName = ?, NIC = ?, DOB = ?, age = ?, Gender = ?, contact = ?, email = ?, address = ? WHERE CustomerID = ?";

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1, customer.getCustTitle());
        pstm.setString(2, customer.getCustName());
        pstm.setString(3, customer.getNIC());
        pstm.setString(4, customer.getDOB());
        pstm.setInt(5, customer.getAge());
        pstm.setString(6, customer.getGender());
        pstm.setString(7, customer.getContact());
        pstm.setString(8, customer.getEmail());
        pstm.setString(9, customer.getAddress());
        pstm.setString(10, customer.getCustomerID());

        boolean isUpdated = pstm.executeUpdate() > 0;
        return isUpdated;
    }
}
