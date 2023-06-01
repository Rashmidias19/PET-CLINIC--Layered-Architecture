package dao;

import dto.OperationSchedule;
import dto.Pet;

import java.sql.SQLException;
import java.util.List;

public interface OperationDAO extends CrudDAO<OperationSchedule,String> {

    @Override
    String getNextId() throws SQLException, ClassNotFoundException;

    @Override
    boolean save(OperationSchedule operationSchedule) throws SQLException, ClassNotFoundException;

    @Override
    String splitId(String currentId);

    @Override
    List<OperationSchedule> getAll() throws SQLException, ClassNotFoundException;

    Pet searchPetById(String ID) throws SQLException, ClassNotFoundException;

    List<String> loadPetID() throws SQLException, ClassNotFoundException;
}
