package dao;

import entity.Pet;

import java.io.File;
import java.io.FileInputStream;

public interface PetDAO extends CrudDAO<Pet,String,FileInputStream, File > {
//
//    @Override
//    List<String> loadID() throws SQLException, ClassNotFoundException;
//
//    @Override
//    String getNextId() throws SQLException, ClassNotFoundException;
//
//    @Override
//    Pet searchById(String ID) throws SQLException, ClassNotFoundException;
//
//    @Override
//    boolean saveWithPicture(Pet pet, FileInputStream inp, File file) throws SQLException, FileNotFoundException, ClassNotFoundException;
//
//    @Override
//    boolean update(Pet pet) throws SQLException, ClassNotFoundException;
//
//    @Override
//    String splitId(String currentId);
//
//    @Override
//    List<Pet> getAll() throws SQLException, ClassNotFoundException;
//
//    @Override
//    boolean delete(String id) throws SQLException, ClassNotFoundException;
}
