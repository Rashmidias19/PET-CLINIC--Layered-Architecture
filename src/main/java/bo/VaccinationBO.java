package bo;

import entity.Pet;
import entity.VaccinationSchedule;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface VaccinationBO extends SuperBO {
    List<String> loadPetID() throws SQLException, ClassNotFoundException;

    Pet searchPetById(String ID) throws SQLException, ClassNotFoundException;

    String getNextId() throws SQLException, ClassNotFoundException;

    String splitId(String currentId);

    List<VaccinationSchedule> getAll() throws SQLException, ClassNotFoundException;

    boolean save(VaccinationSchedule vaccinationSchedule) throws SQLException, ClassNotFoundException, FileNotFoundException;
}
