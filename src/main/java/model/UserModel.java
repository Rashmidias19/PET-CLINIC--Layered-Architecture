package model;

import db.DBConnection;
import dto.Customer;
import dto.User;
import javafx.scene.control.Alert;
import util.CrudUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserModel {

    public static List<String> loadUserID() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT UserID FROM User");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static List<User> getAll() throws SQLException, ClassNotFoundException {
        List<User> data = new ArrayList<>();

        String sql = "SELECT * FROM User";
        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()) {
            data.add(new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return data;
    }

    public static User searchById(String userID) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("SELECT * FROM User WHERE UserID =?");
        pstm.setString(1, userID);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            );
        }
        return null;
    }
    public static String getNextUserId() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();

        String sql = "SELECT UserID FROM user ORDER BY UserID DESC LIMIT 1";

        ResultSet resultSet = con.createStatement().executeQuery(sql);

        if (resultSet.next()) {
            return splitUserId(resultSet.getString(1));
        }
        return splitUserId(null);
    }

    private static String splitUserId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("U000");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "U000" + id;
        }
        return "U0001";
    }

    public static boolean save(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO User(UserID,UserName,password,email)" +
                            "VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                user.getUserID(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail());
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM User WHERE UserID = ?",id);
    }

    public static void update(User user) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE User SET UserName = ?,  Password = ?, email = ? WHERE UserID = ?" ;

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setString(1, user.getUserName());
            pstm.setString(2, user.getPassword());
            pstm.setString(3, user.getEmail());
            pstm.setString(4,user.getEmail());


        boolean isUpdated = pstm.executeUpdate() > 0;
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "yes! updated!!").show();
        }
    }
}
