package dao;

import entity.User;

import java.io.File;
import java.io.FileInputStream;

public interface UserDAO extends CrudDAO<User,String,FileInputStream, File> {
//    @Override
//    String getNextId() throws SQLException, ClassNotFoundException;
//
//    @Override
//    String splitId(String currentId);
//
//    @Override
//    boolean save(User user) throws SQLException, ClassNotFoundException;
//
//    @Override
//    boolean delete(String id) throws SQLException, ClassNotFoundException;
//
//    @Override
//    List<User> getAll() throws SQLException, ClassNotFoundException;
//
//    @Override
//    User searchById(String userID) throws SQLException, ClassNotFoundException;
//
//    @Override
//    List<String> loadID() throws SQLException, ClassNotFoundException;
//
//    @Override
//    boolean update(User user) throws SQLException, ClassNotFoundException;
}
