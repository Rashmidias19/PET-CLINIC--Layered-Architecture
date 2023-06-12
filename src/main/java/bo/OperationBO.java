package bo;

import entity.OperationSchedule;
import entity.Pet;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface OperationBO extends SuperBO {
    String getNextId() throws SQLException, ClassNotFoundException;

    boolean save(OperationSchedule operationSchedule) throws SQLException, ClassNotFoundException, FileNotFoundException;

    String splitId(String currentId);

    List<OperationSchedule> getAll() throws SQLException, ClassNotFoundException;

    Pet searchPetById(String ID) throws SQLException, ClassNotFoundException;

    List<String> loadPetID() throws SQLException, ClassNotFoundException;
}
