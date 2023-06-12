package dao;

import entity.Employee;

import java.io.File;
import java.io.FileInputStream;

public interface EmployeeDAO extends CrudDAO<Employee,String,FileInputStream,File> {

//    @Override
//    List<String> loadID() throws SQLException, ClassNotFoundException;
//
//    @Override
//    String getNextId() throws SQLException, ClassNotFoundException;
//
//    @Override
//    String splitId(String currentId);
//
//    @Override
//    boolean delete(String id) throws SQLException, ClassNotFoundException;
//
//    @Override
//    Employee searchById(String ID) throws SQLException, ClassNotFoundException;
//
//    @Override
//    boolean saveWithPicture(Employee employee, FileInputStream inp, File file) throws SQLException, FileNotFoundException, ClassNotFoundException;
//    @Override
//    List<Employee> getAll() throws SQLException, ClassNotFoundException;
//
//    @Override
//    boolean update(Employee employee) throws SQLException, ClassNotFoundException;
}
