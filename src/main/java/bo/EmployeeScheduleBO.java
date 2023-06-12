package bo;

import entity.Employee;
import entity.EmployeeSchedule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeScheduleBO extends SuperBO {
    boolean save(EmployeeSchedule dto) throws SQLException;

    ArrayList<EmployeeSchedule> getAll() throws SQLException, ClassNotFoundException;

    String getNextId() throws SQLException, ClassNotFoundException;

    String splitId(String currentId);

    Employee searchByEmployeeId(String ID) throws SQLException, ClassNotFoundException;

    List<String> loadEmployeeID() throws SQLException, ClassNotFoundException;
}
