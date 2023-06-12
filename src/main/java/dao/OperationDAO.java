package dao;

import entity.OperationSchedule;

import java.io.File;
import java.io.FileInputStream;

public interface OperationDAO extends CrudDAO<OperationSchedule,String,FileInputStream,File> {

//    @Override
//    String getNextId() throws SQLException, ClassNotFoundException;
//
//    @Override
//    boolean save(OperationSchedule operationSchedule) throws SQLException, ClassNotFoundException;
//
//    @Override
//    String splitId(String currentId);
//
//    @Override
//    List<OperationSchedule> getAll() throws SQLException, ClassNotFoundException;

}
