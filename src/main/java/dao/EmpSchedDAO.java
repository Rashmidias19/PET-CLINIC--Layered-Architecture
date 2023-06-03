package dao;

import dto.EmployeeSchedule;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;

public interface EmpSchedDAO extends CrudDAO<EmployeeSchedule,String, FileInputStream, File> {
//    @Override
//    boolean save(EmployeeSchedule dto) throws SQLException, ClassNotFoundException;
}
