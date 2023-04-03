package model;

import db.DBConnection;
import dto.Item;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ItemModel {
    private static final String URL = "jdbc:mysql://localhost:3306/VETCLOUD";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }


    public static List<String> loadItemID() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT ItemID FROM Item");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static Item searchById(String ID) throws SQLException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("SELECT * FROM Item WHERE ItemID =?");
        pstm.setString(1, ID);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return new Item(

                    resultSet.getString(10),
                    resultSet.getDouble(9)
            );
        }
        return null;
    }
}
