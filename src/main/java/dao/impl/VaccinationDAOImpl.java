package dao.impl;

import dao.VaccinationDAO;
import entity.VaccinationSchedule;
import dao.impl.util.CrudUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VaccinationDAOImpl implements VaccinationDAO {

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT VaccinationID FROM vaccinationschedule ORDER BY VaccinationID DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("VaccinationID");
            int newCustomerId = Integer.parseInt(id.replace("V00-", "")) + 1;
            return String.format("V00-%03d", newCustomerId);
        } else {
            return "V00-001";
        }
    }

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("V000");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "V000" + id;
        }
        return "V0001";
    }

    @Override
    public List<VaccinationSchedule> getAll() throws SQLException, ClassNotFoundException {
        List<VaccinationSchedule> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM VaccinationSchedule");

        while (resultSet.next()) {
            data.add(new VaccinationSchedule(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
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
    public boolean save(VaccinationSchedule vaccinationSchedule) throws SQLException, ClassNotFoundException {
        String sql =  "INSERT INTO Vaccinationschedule(VaccinationID, PetID,CustomerID,Date,Time,Description,Contact)" +
                "VALUES(?, ?, ?, ?,?,?,?)";
        return CrudUtil.execute(
                sql,
                vaccinationSchedule.getVaccinationID(),
                vaccinationSchedule.getPetID(),
                vaccinationSchedule.getCustomerID(),
                vaccinationSchedule.getDate(),
                vaccinationSchedule.getTime(),
                vaccinationSchedule.getDescription(),
                vaccinationSchedule.getContact());
    }

    @Override
    public boolean saveWithPicture(VaccinationSchedule dto, FileInputStream is, File fl) throws SQLException, ClassNotFoundException, FileNotFoundException {
        return false;
    }

    @Override
    public VaccinationSchedule searchById(String ID) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(VaccinationSchedule dto) throws SQLException, ClassNotFoundException {
        return false;
    }
}
