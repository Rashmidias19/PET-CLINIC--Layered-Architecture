package bo;

import dto.Pet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface PetBO extends SuperBO {
    List<String> loadID() throws SQLException, ClassNotFoundException;

    String getNextId() throws SQLException, ClassNotFoundException;

    Pet searchById(String ID) throws SQLException, ClassNotFoundException;

    boolean saveWithPicture(Pet pet, FileInputStream inp, File file) throws SQLException, FileNotFoundException, ClassNotFoundException;

    boolean update(Pet pet) throws SQLException, ClassNotFoundException;

    String splitId(String currentId);

    List<Pet> getAll() throws SQLException, ClassNotFoundException;

    List<String> loadCustomerID() throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;
}
