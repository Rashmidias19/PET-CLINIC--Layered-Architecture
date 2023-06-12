package dao.impl;

import dao.UserDAO;
import db.DBConnection;
import entity.User;
import dao.impl.util.CrudUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT UserID FROM user ORDER BY UserID DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("UserID");
            int newCustomerId = Integer.parseInt(id.replace("U00-", "")) + 1;
            return String.format("U00-%03d", newCustomerId);
        } else {
            return "U00-001";
        }
    }

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("U000");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "U000" + id;
        }
        return "U0001";
    }

    @Override
    public boolean save(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO User(UserID,UserName,password,email)" +
                "VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                user.getUserID(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail());
    }

    @Override
    public boolean saveWithPicture(User dto, FileInputStream is, File fl) throws SQLException, ClassNotFoundException, FileNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM User WHERE UserID = ?",id);
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        List<User> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM User");

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

    @Override
    public User searchById(String userID) throws SQLException, ClassNotFoundException {
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

    @Override
    public List<String> loadID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT UserID FROM User");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    @Override
    public boolean update(User dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE User SET UserName = ?,  Password = ?, email = ? WHERE UserID = ?",
                dto.getUserName(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getUserID()
         );
    }
}
