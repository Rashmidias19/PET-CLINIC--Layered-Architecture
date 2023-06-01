package dao;

import dto.Inhouse;
import dto.Pet;

import java.sql.SQLException;
import java.util.List;

public interface InhouseDAO extends CrudDAO<Inhouse,String> {
    List<String> loadPetID() throws SQLException, ClassNotFoundException;

    @Override
    String splitId(String currentId);

    @Override
    List<Inhouse> getAll() throws SQLException, ClassNotFoundException;

    @Override
    List<String> loadID() throws SQLException, ClassNotFoundException;

    @Override
    Inhouse searchById(String inhouseID) throws SQLException, ClassNotFoundException;

    @Override
    boolean save(Inhouse inhouse) throws SQLException, ClassNotFoundException;

    @Override
    boolean delete(String id) throws SQLException, ClassNotFoundException;

    @Override
    boolean update(Inhouse inhouse) throws SQLException, ClassNotFoundException;

    Pet searchPetById(String ID) throws SQLException, ClassNotFoundException;

    @Override
    String getNextId() throws SQLException, ClassNotFoundException;
}
