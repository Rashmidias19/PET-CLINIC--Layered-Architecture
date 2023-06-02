package dao;

import dto.Pet;
import dto.VaccinationSchedule;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;

public interface VaccinationDAO extends CrudDAO<VaccinationSchedule,String,FileInputStream, File> {
    List<String> loadPetID() throws SQLException, ClassNotFoundException;

    Pet searchPetById(String ID) throws SQLException, ClassNotFoundException;

    @Override
    String getNextId() throws SQLException, ClassNotFoundException;

    @Override
    String splitId(String currentId);

    @Override
    List<VaccinationSchedule> getAll() throws SQLException, ClassNotFoundException;

    @Override
    boolean save(VaccinationSchedule vaccinationSchedule) throws SQLException, ClassNotFoundException;
}
