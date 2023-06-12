package dao.impl;

import dao.BillDAO;
import entity.Bill;
import dao.impl.util.CrudUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDAOImpl implements BillDAO {

    @Override
    public List<Bill> getAll() throws SQLException, ClassNotFoundException {
        List<Bill> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Bill");

        while (resultSet.next()) {
            data.add(new Bill(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            ));
        }
        return data;
    }


    @Override
    public List<String> loadID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT BillID FROM Bill");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Bill WHERE BillID = ?",id);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
       ResultSet rst = CrudUtil.execute("SELECT BillID FROM Bill ORDER BY BillID DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("BillID");
            int newCustomerId = Integer.parseInt(id.replace("B00-", "")) + 1;
            return String.format("B00-%03d", newCustomerId);
        } else {
            return "B00-001";
        }

    }

    @Override
    public String splitId(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("B000");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "B000" + id;
        }
        return "B0001";
    }

    @Override
    public boolean save(Bill bill) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Bill(BillID, CustomerID, Date, Time, Amount, Contact, email, Description) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                bill.getBillID(),
                bill.getCustomerID(),
                bill.getDate(),
                bill.getTime(),
                bill.getAmount(),
                bill.getContact(),
                bill.getEmail(),
                bill.getDescription());
    }

    @Override
    public boolean saveWithPicture(Bill dto, FileInputStream is, File fl) throws SQLException, ClassNotFoundException, FileNotFoundException {
        return false;
    }

    @Override
    public Bill searchById(String ID) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Bill dto) throws SQLException, ClassNotFoundException {
        return false;
    }


}
