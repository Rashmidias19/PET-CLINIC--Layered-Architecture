package dao;

import dto.Customer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer,String,FileInputStream,File> {
//
//    @Override
//    String splitId(String currentId);
//
//    @Override
//    List<Customer> getAll() throws SQLException, ClassNotFoundException;
//
//    @Override
//    List<String> loadID() throws SQLException, ClassNotFoundException;
//
//    @Override
//    boolean delete(String id) throws SQLException, ClassNotFoundException;
//
//    @Override
//    String getNextId() throws SQLException, ClassNotFoundException;
//
//    @Override
//    boolean save(Customer customer) throws SQLException, FileNotFoundException, ClassNotFoundException;
//
//    @Override
//    Customer searchById(String ID) throws SQLException, ClassNotFoundException;
//
//    @Override
//    boolean update(Customer customer) throws SQLException, ClassNotFoundException;
}
