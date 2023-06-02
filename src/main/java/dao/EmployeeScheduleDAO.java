package dao;

import dto.Employee;
import dto.EmployeeSchedule;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeScheduleDAO extends CrudDAO<EmployeeSchedule,String,FileInputStream,File> {

    @Override
    ArrayList<EmployeeSchedule> getAll() throws SQLException, ClassNotFoundException;

    @Override
    String getNextId() throws SQLException, ClassNotFoundException;

    @Override
    String splitId(String currentId);

    Employee searchByEmployeeId(String ID) throws SQLException, ClassNotFoundException;

    List<String> loadEmployeeID() throws SQLException, ClassNotFoundException;

    @Override
    boolean save(EmployeeSchedule dto) throws SQLException;

    boolean saveSchedule(EmployeeSchedule dto) throws SQLException, ClassNotFoundException;

    boolean saveEmpSched(EmployeeSchedule dto) throws SQLException, ClassNotFoundException;
}
