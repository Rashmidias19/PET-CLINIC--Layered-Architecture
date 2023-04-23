package model;

import db.DBConnection;
import dto.Bill;
import dto.Item;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillModel {

    public static List<Bill> getAll() throws SQLException {
        List<Bill> data = new ArrayList<>();

        String sql = "SELECT * FROM Bill";
        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()) {
            data.add(new Bill(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            ));
        }
        return data;
    }

    public static List<String> loadBillID() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT BillID FROM Bill");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static String getNextBillId() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();

        String sql = "SELECT BillID FROM Bill ORDER BY BillID DESC LIMIT 1";

        ResultSet resultSet = con.createStatement().executeQuery(sql);

        if (resultSet.next()) {
            return splitBillId(resultSet.getString(1));
        }
        return splitBillId(null);
    }

    private static String splitBillId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("B000");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "B000" + id;
        }
        return "B0001";
    }
}
