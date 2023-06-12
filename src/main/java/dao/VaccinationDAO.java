package dao;

import entity.VaccinationSchedule;

import java.io.File;
import java.io.FileInputStream;

public interface VaccinationDAO extends CrudDAO<VaccinationSchedule,String,FileInputStream, File> {
//
//    @Override
//    String getNextId() throws SQLException, ClassNotFoundException;
//
//    @Override
//    String splitId(String currentId);
//
//    @Override
//    List<VaccinationSchedule> getAll() throws SQLException, ClassNotFoundException;
//
//    @Override
//    boolean save(VaccinationSchedule vaccinationSchedule) throws SQLException, ClassNotFoundException;
}
