package dao;

import dto.Bill;
import dto.Customer;
import dto.Item;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BillDAO extends CrudDAO<Bill,String,FileInputStream,File> {

    @Override
    List<Bill> getAll() throws SQLException, ClassNotFoundException;

    @Override
    List<String> loadID() throws SQLException, ClassNotFoundException;

    @Override
    boolean delete(String id) throws SQLException, ClassNotFoundException;

    ArrayList<String> loadCustomerID() throws SQLException, ClassNotFoundException;

    List<String> loadItemID() throws SQLException, ClassNotFoundException;

    @Override
    String getNextId() throws SQLException, ClassNotFoundException;

    @Override
    String splitId(String currentId);

    @Override
    boolean save(Bill bill) throws SQLException, ClassNotFoundException;

    Item searchByItemId(String ID) throws SQLException, ClassNotFoundException;

    Customer searchByCustomerId(String ID) throws SQLException, ClassNotFoundException;
}
