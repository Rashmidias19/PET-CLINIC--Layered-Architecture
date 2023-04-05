package model;

import dto.EmployeeSchedule;
import dto.OperationSchedule;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationScheduleModel {
    public static List<OperationSchedule> getAll() throws SQLException {
        List<OperationSchedule> data = new ArrayList<>();

        String sql = "SELECT * FROM OperationSchedule";
        ResultSet resultSet = CrudUtil.execute(sql);

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
}
