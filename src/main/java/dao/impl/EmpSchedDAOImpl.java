package dao.impl;

import dao.EmpSchedDAO;
import dao.impl.util.CrudUtil;
import dto.EmployeeSchedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class EmpSchedDAOImpl implements EmpSchedDAO {

    @Override
    public String splitId(String currentId) {
        return null;
    }

    @Override
    public List<EmployeeSchedule> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> loadID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(EmployeeSchedule dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO EmpSched(ScheduleID,EmployeeID) VALUES(?, ?)",dto.getScheduleID(),dto.getEmployeeID());
    }

    @Override
    public boolean saveWithPicture(EmployeeSchedule dto, FileInputStream is, File fl) throws SQLException, ClassNotFoundException, FileNotFoundException {
        return false;
    }

    @Override
    public EmployeeSchedule searchById(String ID) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(EmployeeSchedule dto) throws SQLException, ClassNotFoundException {
        return false;
    }

}
