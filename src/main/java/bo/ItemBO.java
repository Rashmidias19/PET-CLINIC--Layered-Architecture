package bo;

import entity.Item;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    List<String> loadID() throws SQLException, ClassNotFoundException;

    Item searchById(String ID) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    List<Item> getAll() throws SQLException, ClassNotFoundException;

    String getNextId() throws SQLException, ClassNotFoundException;

    String splitId(String currentId);

    boolean save(Item item) throws SQLException, ClassNotFoundException, FileNotFoundException;

    boolean update(Item item) throws SQLException, ClassNotFoundException;
}
