package bo.impl;

import bo.EmployeeScheduleBO;
import dao.*;
import db.DBConnection;
import entity.Employee;
import entity.EmployeeSchedule;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeScheduleBOImpl implements EmployeeScheduleBO {

    EmployeeScheduleDAO employeeScheduleDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE_SCHEDULE);
    EmployeeDAO employeeDAO =DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    EmpSchedDAO empSchedDAO =DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPSCHED);


    @Override
    public boolean save(EmployeeSchedule dto) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            boolean isSaved =employeeScheduleDAO.save(dto);
            System.out.println(isSaved);
            if(isSaved) {
                boolean isUpdated = empSchedDAO.save(dto);
                System.out.println(isUpdated);
                if(isUpdated) {
                    System.out.println("updated");
                    con.commit();
                    return true;
                }
            }

            return false;
        } catch (SQLException | ClassNotFoundException | FileNotFoundException er) {
            System.out.println(er);
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }

    }


    @Override
    public ArrayList<EmployeeSchedule> getAll() throws SQLException, ClassNotFoundException{
        ArrayList<EmployeeSchedule> list = new ArrayList<>();
        ArrayList<EmployeeSchedule> all = (ArrayList<EmployeeSchedule>) employeeScheduleDAO.getAll();
        for (EmployeeSchedule dto:all) {
            list.add(new EmployeeSchedule(dto.getScheduleID(),dto.getName(), dto.getDate(), dto.getTime(), dto.getWorkTime(),
                    dto.getShift(), dto.getOT(), dto.getEmployeeID()));
        }
        return list;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException{
        return employeeScheduleDAO.getNextId();
    }

    @Override
    public String splitId(String currentId){
        return employeeScheduleDAO.splitId(currentId);
    }

    @Override
    public Employee searchByEmployeeId(String ID) throws SQLException, ClassNotFoundException{
        return employeeDAO.searchById(ID);
    }

    @Override
    public List<String> loadEmployeeID() throws SQLException, ClassNotFoundException{
        return employeeDAO.loadID();
    }


}
