package dao.impl;

import dao.ItemDAO;
import db.DBConnection;
import dto.Item;
import javafx.scene.control.Alert;
import dao.impl.util.CrudUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public List<String> loadID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT ItemID FROM Item");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    @Override
    public Item searchById(String ID) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Item WHERE ItemID =?",ID);

        if(resultSet.next()) {
            return new Item(

                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getDouble(9),
                    resultSet.getString(10)
            );
        }
        return null;
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Item WHERE ItemID = ?",id);
    }

    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        List<Item> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Item");

        while (resultSet.next()) {
            data.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getDouble(9),
                    resultSet.getString(10)

            ));
        }
        return data;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT ItemID FROM item ORDER BY ItemID DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("ItemID");
            int newCustomerId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newCustomerId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("I000");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "I000" + id;
        }
        return "I0001";
    }


    @Override
    public boolean save(Item item) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Item(ItemID,Name,Man_Date,Exp_Date,Supplier_name,Type,Supplier_contact,Description,Quantity,Price)" +
                "VALUES(?, ?, ?, ?,?,?,?,?,?,?)";
        return CrudUtil.execute(
                sql,
                item.getItemID(),
                item.getName(),
                item.getMan_Date(),
                item.getExp_Date(),
                item.getSupplier_name(),
                item.getType(),
                item.getSupplier_contact(),
                item.getDescription(),
                item.getQuantity(),
                item.getPrice());
    }

    @Override
    public boolean saveWithPicture(Item dto, FileInputStream is, File fl) throws SQLException, ClassNotFoundException, FileNotFoundException {
        return false;
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Item SET Name = ?,  Man_Date = ?, Exp_Date = ?, Supplier_name = ?, Type = ?, Supplier_contact = ?, Description = ?, Quantity = ?, Price = ? WHERE ItemID = ?" ;

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1, item.getName());
        pstm.setString(2, item.getMan_Date());
        pstm.setString(3, item.getExp_Date());
        pstm.setString(4,item.getSupplier_name());
        pstm.setString(5,item.getType());
        pstm.setString(6,item.getSupplier_contact());
        pstm.setString(7,item.getDescription());
        pstm.setString(8,item.getQuantity());
        pstm.setDouble(9,item.getPrice());
        pstm.setString(10,item.getItemID());

        boolean isUpdated = pstm.executeUpdate() > 0;
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "yes! updated!!").show();
        }
        return isUpdated;
    }
}
