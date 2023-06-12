package bo;

import entity.User;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    String getNextId() throws SQLException, ClassNotFoundException;

    String splitId(String currentId);

    boolean save(User user) throws SQLException, ClassNotFoundException, FileNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    List<User> getAll() throws SQLException, ClassNotFoundException;

    User searchById(String userID) throws SQLException, ClassNotFoundException;

    List<String> loadID() throws SQLException, ClassNotFoundException;

    boolean update(User user) throws SQLException, ClassNotFoundException;
}
