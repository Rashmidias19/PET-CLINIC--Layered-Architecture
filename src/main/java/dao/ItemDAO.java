package dao;

import dto.Item;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item,String,FileInputStream,File> {
//
//    @Override
//    List<String> loadID() throws SQLException, ClassNotFoundException;
//
//    @Override
//    Item searchById(String ID) throws SQLException, ClassNotFoundException;
//
//    @Override
//    boolean delete(String id) throws SQLException, ClassNotFoundException;
//
//    @Override
//    List<Item> getAll() throws SQLException, ClassNotFoundException;
//
//    @Override
//    String getNextId() throws SQLException, ClassNotFoundException;
//
//    @Override
//    String splitId(String currentId);
//
//    @Override
//    boolean save(Item item) throws SQLException, ClassNotFoundException;
//
//    @Override
//    boolean update(Item item) throws SQLException, ClassNotFoundException;
}
