package dao.impl;

import dao.OperationDAO;
import db.DBConnection;
import dto.OperationSchedule;
import dto.Pet;
import dao.impl.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationDAOImpl implements OperationDAO {

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT OperationID FROM operationschedule ORDER BY OperationID DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("OperationID");
            int newCustomerId = Integer.parseInt(id.replace("OP00-", "")) + 1;
            return String.format("OP00-%03d", newCustomerId);
        } else {
            return "OP00-001";
        }
    }

    @Override
    public boolean save(OperationSchedule operationSchedule) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO OperationSchedule(OperationID,PetID,CustomerID,Date,Time,Description,Hours,Contact)" +
                "VALUES(?, ?, ?, ?,?,?,?,?)";
        return CrudUtil.execute(
                sql,
                operationSchedule.getOperationID(),
                operationSchedule.getPetID(),
                operationSchedule.getCustomerID(),
                operationSchedule.getDate(),
                operationSchedule.getTime(),
                operationSchedule.getDescription(),
                operationSchedule.getHours(),
                operationSchedule.getContact());
    }

    @Override
    public OperationSchedule searchById(String ID) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(OperationSchedule dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("OP000");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "OP000" + id;
        }
        return "OP0001";
    }

    @Override
    public List<OperationSchedule> getAll() throws SQLException, ClassNotFoundException {
        List<OperationSchedule> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM OperationSchedule");

        while (resultSet.next()) {
            data.add(new OperationSchedule(
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
        return null;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Pet searchPetById(String ID) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Pet WHERE PetID =?",ID);

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
                    resultSet.getString(10)
            );
        }
        return null;
    }

    @Override
    public List<String> loadPetID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT PetID FROM Pet");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }
}
