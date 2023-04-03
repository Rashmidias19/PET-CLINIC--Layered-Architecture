package model;

import db.DBConnection;
import dto.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CustomerModel {
    private static final String URL = "jdbc:mysql://localhost:3306/VETCLOUD";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }


    public static List<String> loadCustomerID() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT CustomerID FROM Customer");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }
    public static Customer searchById(String ID) throws SQLException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("SELECT * FROM Customer WHERE CustomerID =?");
        pstm.setString(1, ID);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return new Customer(
                    resultSet.getString(8),
                    resultSet.getString(9)

            );
        }
        return null;
    }
}