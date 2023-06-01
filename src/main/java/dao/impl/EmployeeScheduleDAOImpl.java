package dao.impl;

import dao.EmployeeScheduleDAO;
import db.DBConnection;
import dto.Employee;
import dto.EmployeeSchedule;
import dao.impl.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeScheduleDAOImpl implements EmployeeScheduleDAO {

    @Override
    public  ArrayList<EmployeeSchedule> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeSchedule> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM EmployeeSchedule");

        while (resultSet.next()) {
            data.add(new EmployeeSchedule(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)

            ));
        }
        return data;
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
        ResultSet rst = CrudUtil.execute("SELECT ScheduleID FROM employeeschedule ORDER BY ScheduleID DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("ScheduleID");
            int newCustomerId = Integer.parseInt(id.replace("SH00-", "")) + 1;
            return String.format("SH00-%03d", newCustomerId);
        } else {
            return "SH00-001";
        }
    }

    @Override
    public boolean save(EmployeeSchedule dto) throws SQLException, ClassNotFoundException {
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

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("ES000");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "ES000" + id;
        }
        return "ES0001";
    }

    @Override
    public Employee searchByEmployeeId(String ID) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("SELECT * FROM Employee WHERE EmployeeID =?");
        pstm.setString(1, ID);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10),
                    resultSet.getString(11),
                    resultSet.getBlob(12)
            );
        }
        return null;
    }

    @Override
    public List<String> loadEmployeeID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT EmployeeID FROM Employee");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    @Override
    public boolean addSchedule(EmployeeSchedule dto) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            boolean isSaved =saveSchedule(dto);
            if(isSaved) {
                boolean isUpdated = saveEmpSched(dto);
                if(isUpdated) {
                    con.commit();
                    return true;
                }
            }

            return false;
        } catch (SQLException | ClassNotFoundException er) {
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }

    }

    @Override
    public boolean saveSchedule(EmployeeSchedule dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO EmployeeSchedule(ScheduleID,EmployeeID,Name,Date,Time,WorkTime,Shift,OT) VALUES(?, ?, ?, ?,?,?,?,?)"
        + dto.getScheduleID(),dto.getEmployeeID(),dto.getName(),dto.getDate(),dto.getTime(),dto.getWorkTime(),dto.getShift(),dto.getOT());


    }

    @Override
    public boolean saveEmpSched(EmployeeSchedule dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO EmpSched(ScheduleID,EmployeeID) VALUES(?, ?)",dto.getScheduleID(),dto.getEmployeeID());
    }
}