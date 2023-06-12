package bo;

import entity.Inhouse;
import entity.Pet;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface InhouseBO extends SuperBO {
    List<String> loadPetID() throws SQLException, ClassNotFoundException;

    String splitId(String currentId);

    List<Inhouse> getAll() throws SQLException, ClassNotFoundException;

    List<String> loadID() throws SQLException, ClassNotFoundException;

    Inhouse searchById(String inhouseID) throws SQLException, ClassNotFoundException;

    boolean save(Inhouse inhouse) throws SQLException, ClassNotFoundException, FileNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean update(Inhouse inhouse) throws SQLException, ClassNotFoundException;

    Pet searchPetById(String ID) throws SQLException, ClassNotFoundException;

    String getNextId() throws SQLException, ClassNotFoundException;
}
