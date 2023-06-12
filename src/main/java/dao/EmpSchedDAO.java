package dao;

import entity.EmployeeSchedule;

import java.io.File;
import java.io.FileInputStream;

public interface EmpSchedDAO extends CrudDAO<EmployeeSchedule,String, FileInputStream, File> {
//    @Override
//    boolean save(EmployeeSchedule dto) throws SQLException, ClassNotFoundException;
}
