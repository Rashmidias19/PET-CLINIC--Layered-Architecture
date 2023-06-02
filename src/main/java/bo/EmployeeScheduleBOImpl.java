package bo;

import dao.CrudDAO;
import dao.impl.EmpSchedDAOImpl;
import dao.impl.EmployeeDAOImpl;
import dao.impl.EmployeeScheduleDAOImpl;
import dao.impl.util.CrudUtil;
import db.DBConnection;
import dto.Employee;
import dto.EmployeeSchedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

public class EmployeeScheduleBOImpl {

    CrudDAO<EmployeeSchedule,String, FileInputStream, File> employeeScheduleDAO =new EmployeeScheduleDAOImpl();
    CrudDAO<Employee,String,FileInputStream, File> employeeDAO =new EmployeeDAOImpl();
    CrudDAO<EmployeeSchedule,String,FileInputStream, File> empSchedDAO =new EmpSchedDAOImpl();


    public boolean save(EmployeeSchedule dto) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            boolean isSaved =employeeScheduleDAO.save(dto);
            if(isSaved) {
                boolean isUpdated = empSchedDAO.save(dto);
                if(isUpdated) {
                    con.commit();
                    return true;
                }
            }

            return false;
        } catch (SQLException | ClassNotFoundException | FileNotFoundException er) {
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }

    }

}
