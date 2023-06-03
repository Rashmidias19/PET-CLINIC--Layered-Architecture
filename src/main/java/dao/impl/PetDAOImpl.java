package dao.impl;

import dao.PetDAO;
import db.DBConnection;
import dto.Pet;
import javafx.scene.control.Alert;
import dao.impl.util.CrudUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAOImpl implements PetDAO {

    @Override
    public List<String> loadID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT PetID FROM Pet");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT PetID FROM pet ORDER BY PetID DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("PetID");
            int newCustomerId = Integer.parseInt(id.replace("P00-", "")) + 1;
            return String.format("P00-%03d", newCustomerId);
        } else {
            return "P00-001";
        }
    }

    @Override
    public boolean save(Pet dto) throws SQLException, ClassNotFoundException, FileNotFoundException {
        return false;
    }


    @Override
    public Pet searchById(String ID) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("SELECT * FROM Pet WHERE PetID =?");
        pstm.setString(1, ID);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return new Pet(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getInt(8),
                    resultSet.getString(9),
                    resultSet.getString(10),
                    resultSet.getBlob(11)
            );
        }
        return null;
    }

    @Override
    public boolean saveWithPicture(Pet pet, FileInputStream inp, File file) throws SQLException, FileNotFoundException, ClassNotFoundException {
        String sql = "INSERT INTO Pet(PetID,Name,CustomerID,Type ,Breed,Gender,DOB,age,address,contact,picture )" +
                "VALUES(?, ?, ?, ?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1, pet.getPetID());
        pstm.setString(2, pet.getName());
        pstm.setString(3, pet.getCustomerID());
        pstm.setString(4, pet.getType());
        pstm.setString(5, pet.getBreed());
        pstm.setString(6, pet.getGender());
        pstm.setDate(7, Date.valueOf(pet.getDOB()));
        pstm.setInt(8, pet.getAge());
        pstm.setString(9, pet.getAddress());
        pstm.setString(10, pet.getContact());
        inp = new FileInputStream(file);
        pstm.setBinaryStream(11, (InputStream) inp, (int) file.length());
        int affectedRows = pstm.executeUpdate();
        boolean isSaved=false;
        if (affectedRows > 0) {
            isSaved=true;
        }
        return isSaved;
    }

    @Override
    public boolean update(Pet pet) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE Pet SET Name = ?, CustomerID = ?, Type = ?, Breed = ?, Gender = ?, DOB = ?, age = ?, address = ?, contact = ? WHERE PetID = ?");

        pstm.setString(1, pet.getName());
        pstm.setString(2, pet.getCustomerID());
        pstm.setString(3, pet.getType());
        pstm.setString(4, pet.getBreed());
        pstm.setString(5, pet.getGender());
        pstm.setString(6, pet.getDOB());
        pstm.setInt(7, pet.getAge());
        pstm.setString(8, pet.getAddress());
        pstm.setString(9, pet.getContact());
        pstm.setString(10, pet.getPetID());

        return pstm.executeUpdate() > 0;
    }


    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("P000");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "P000" + id;
        }
        return "P0001";
    }

    @Override
    public List<Pet> getAll() throws SQLException, ClassNotFoundException {
        List<Pet> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Pet");

        while (resultSet.next()) {
            data.add(new Pet(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getInt(8),
                    resultSet.getString(9),
                    resultSet.getString(10),
                    resultSet.getBlob(11)
            ));
        }
        return data;
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Pet WHERE PetID = ?",id);
    }
}
