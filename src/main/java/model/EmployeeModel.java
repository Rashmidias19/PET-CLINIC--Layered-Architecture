package model;

import db.DBConnection;
import dto.Customer;
import dto.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    public static Employee searchById(String ID) throws SQLException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("SELECT * FROM Employee WHERE EmployeeID =?");
        pstm.setString(1, ID);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return new Employee(
                    resultSet.getString(2)
            );
        }
        return null;
    }

    public static List<String> loadEmployeeID() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT EmployeeID FROM Employee");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

}
