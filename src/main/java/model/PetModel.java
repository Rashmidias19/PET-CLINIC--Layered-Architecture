//package model;
//
//import db.DBConnection;
//import dto.Customer;
//import dto.Item;
//import dto.Pet;
//import javafx.scene.control.Alert;
//import dao.impl.util.CrudUtil;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.sql.*;
//import java.dao.impl.util.ArrayList;
//import java.dao.impl.util.List;
//import java.dao.impl.util.Properties;
//
//public class PetModel {
//
//    public List<String> loadPetID() throws SQLException, ClassNotFoundException {
//        Connection con = DBConnection.getInstance().getConnection();
//        ResultSet resultSet = con.createStatement().executeQuery("SELECT PetID FROM Pet");
//
//        List<String> data = new ArrayList<>();
//
//        while (resultSet.next()) {
//            data.add(resultSet.getString(1));
//        }
//        return data;
//    }
//
//    public List<String> loadCustomerID() throws SQLException, ClassNotFoundException {
//        Connection con = DBConnection.getInstance().getConnection();
//        ResultSet resultSet = con.createStatement().executeQuery("SELECT CustomerID FROM Customer");
//
//        List<String> data = new ArrayList<>();
//
//        while (resultSet.next()) {
//            data.add(resultSet.getString(1));
//        }
//        return data;
//    }
//
//    public static Pet searchById(String ID) throws SQLException, ClassNotFoundException {
//        PreparedStatement pstm = DBConnection.getInstance().getConnection()
//                .prepareStatement("SELECT * FROM Pet WHERE PetID =?");
//        pstm.setString(1, ID);
//        ResultSet resultSet = pstm.executeQuery();
//
//        if(resultSet.next()) {
//            return new Pet(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    resultSet.getString(5),
//                    resultSet.getString(6),
//                    resultSet.getString(7),
//                    resultSet.getInt(8),
//                    resultSet.getString(9),
//                    resultSet.getString(10)
//            );
//        }
//        return null;
//    }
//
//    public static List<Pet> getAll() throws SQLException, ClassNotFoundException {
//        List<Pet> data = new ArrayList<>();
//
//        String sql = "SELECT * FROM Pet";
//        ResultSet resultSet = CrudUtil.execute(sql);
//
//        while (resultSet.next()) {
//            data.add(new Pet(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    resultSet.getString(5),
//                    resultSet.getString(6),
//                    resultSet.getString(7),
//                    resultSet.getInt(8),
//                    resultSet.getString(9),
//                    resultSet.getString(10),
//                    resultSet.getBlob(11)
//            ));
//        }
//        return data;
//    }
//
//    public static String getNextPetId() throws SQLException, ClassNotFoundException {
//        Connection con = DBConnection.getInstance().getConnection();
//
//        String sql = "SELECT PetID FROM pet ORDER BY PetID DESC LIMIT 1";
//
//        ResultSet resultSet = con.createStatement().executeQuery(sql);
//
//        if (resultSet.next()) {
//            return splitPetId(resultSet.getString(1));
//        }
//        return splitPetId(null);
//    }
//
//    private static String splitPetId(String currentId) {
//        if(currentId != null) {
//            String[] strings = currentId.split("P000");
//            int id = Integer.parseInt(strings[1]);
//            id++;
//            return "P000" + id;
//        }
//        return "P0001";
//    }
//
//    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
//        return CrudUtil.execute("DELETE FROM Pet WHERE PetID = ?",id);
//    }
//
//    public static void save(Pet pet, FileInputStream inp, File file) throws SQLException, FileNotFoundException, ClassNotFoundException {
//        String sql = "INSERT INTO Pet(PetID,Name,CustomerID,Type ,Breed,Gender,DOB,age,address,contact,picture )" +
//                "VALUES(?, ?, ?, ?,?,?,?,?,?,?,?)";
//        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        pstm.setString(1, pet.getPetID());
//        pstm.setString(2, pet.getName());
//        pstm.setString(3, pet.getCustomerID());
//        pstm.setString(4, pet.getType());
//        pstm.setString(5, pet.getBreed());
//        pstm.setString(6, pet.getGender());
//        pstm.setDate(7, Date.valueOf(pet.getDOB()));
//        pstm.setInt(8, pet.getAge());
//        pstm.setString(9, pet.getAddress());
//        pstm.setString(10, pet.getContact());
//        inp = new FileInputStream(file);
//        pstm.setBinaryStream(11, (InputStream) inp, (int) file.length());
//        int affectedRows = pstm.executeUpdate();
//        if (affectedRows > 0) {
//            new Alert(Alert.AlertType.CONFIRMATION,
//                    "Pet added :)")
//                    .show();
//        }
//    }
//
//    public static boolean update(Pet pet) throws SQLException, ClassNotFoundException {
//        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE Pet SET Name = ?, CustomerID = ?, Type = ?, Breed = ?, Gender = ?, DOB = ?, age = ?, address = ?, contact = ? WHERE PetID = ?");
//
//        pstm.setString(1, pet.getName());
//        pstm.setString(2, pet.getCustomerID());
//        pstm.setString(3, pet.getType());
//        pstm.setString(4, pet.getBreed());
//        pstm.setString(5, pet.getGender());
//        pstm.setString(6, pet.getDOB());
//        pstm.setInt(7, pet.getAge());
//        pstm.setString(8, pet.getAddress());
//        pstm.setString(9, pet.getContact());
//        pstm.setString(10, pet.getPetID());
//
//        return pstm.executeUpdate() > 0;
//    }
//
//
//}
