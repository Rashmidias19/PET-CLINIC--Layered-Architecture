package dao.impl;

import dao.InhouseDAO;
import db.DBConnection;
import entity.Inhouse;
import javafx.scene.control.Alert;
import dao.impl.util.CrudUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InhouseDAOImpl implements InhouseDAO {

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("IH000");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "IH000" + id;
        }
        return "IH0001";
    }

    @Override
    public List<Inhouse> getAll() throws SQLException, ClassNotFoundException {
        List<Inhouse> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Inhouse");

        while (resultSet.next()) {
            data.add(new Inhouse(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            ));
        }
        return data;
    }

    @Override
    public List<String> loadID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =CrudUtil.execute("SELECT InhouseID FROM Inhouse");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    @Override
    public Inhouse searchById(String inhouseID) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Inhouse WHERE InhouseID =?",inhouseID);

        if(resultSet.next()) {
            return new Inhouse(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            );
        }
        return null;
    }

    @Override
    public boolean save(Inhouse inhouse) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Inhouse(InhouseID,PetID,CustomerID,AdmittedDate,Time,DischargeDate,Description,contact)" +
                "VALUES(?, ?, ?, ?,?,?,?,?)";
        return CrudUtil.execute(
                sql,
                inhouse.getInhouseID(),
                inhouse.getPetID(),
                inhouse.getCustomerID(),
                inhouse.getAdmittedDate(),
                inhouse.getTime(),
                inhouse.getDischargeDate(),
                inhouse.getDescription(),
                inhouse.getContact());
    }

    @Override
    public boolean saveWithPicture(Inhouse dto, FileInputStream is, File fl) throws SQLException, ClassNotFoundException, FileNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Inhouse WHERE InhouseID = ?",id);
    }

    @Override
    public boolean update(Inhouse inhouse) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Inhouse SET PetID = ?,  CustomerID = ?, AdmittedDate = ?, Time = ?, DischargeDate = ?, Description = ?, Contact = ? WHERE InhouseID = ?" ;

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1, inhouse.getPetID());
        pstm.setString(2, inhouse.getCustomerID());
        pstm.setString(3, inhouse.getAdmittedDate());
        pstm.setString(4, inhouse.getTime());
        pstm.setString(5, inhouse.getDischargeDate());
        pstm.setString(6, inhouse.getDescription());
        pstm.setString(7, inhouse.getContact());
        pstm.setString(8, inhouse.getInhouseID());

        boolean isUpdated = pstm.executeUpdate() > 0;
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "yes! updated!!").show();
        }
        return isUpdated;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
       ResultSet rst = CrudUtil.execute("SELECT InhouseID FROM Inhouse ORDER BY InhouseID DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("InhouseID");
            int newCustomerId = Integer.parseInt(id.replace("IH00-", "")) + 1;
            return String.format("IH00-%03d", newCustomerId);
        } else {
            return "IH00-001";
        }
    }


}
