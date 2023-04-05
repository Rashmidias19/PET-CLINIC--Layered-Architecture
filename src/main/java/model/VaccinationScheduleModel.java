package model;

import dto.OperationSchedule;
import dto.VaccinationSchedule;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VaccinationScheduleModel {
    public static List<VaccinationSchedule> getAll() throws SQLException {
        List<VaccinationSchedule> data = new ArrayList<>();

        String sql = "SELECT * FROM VaccinationSchedule";
        ResultSet resultSet = CrudUtil.execute(sql);

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
}
