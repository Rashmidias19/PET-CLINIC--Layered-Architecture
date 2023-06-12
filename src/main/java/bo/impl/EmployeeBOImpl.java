package bo.impl;

import bo.EmployeeBO;
import dao.DAOFactory;
import dao.EmployeeDAO;
import dao.UserDAO;
import entity.Employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    UserDAO userDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public List<String> loadID() throws SQLException, ClassNotFoundException{
        return employeeDAO.loadID();
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException{
        return employeeDAO.getNextId();
    }

    @Override
    public String splitId(String currentId){
        return employeeDAO.splitId(currentId);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException{
        return employeeDAO.delete(id);
    }

    @Override
    public List<String> loadUserID() throws SQLException, ClassNotFoundException{
        return userDAO.loadID();
    }

    @Override
    public Employee searchById(String ID) throws SQLException, ClassNotFoundException{
        return employeeDAO.searchById(ID);
    }

    @Override
    public boolean saveWithPicture(Employee employee, FileInputStream inp, File file) throws SQLException, FileNotFoundException, ClassNotFoundException{
        return employeeDAO.saveWithPicture(employee, inp, file);
    }

    @Override
    public List<Employee> getAll() throws SQLException, ClassNotFoundException{
        ArrayList<Employee> list = new ArrayList<>();
        ArrayList<Employee> all = (ArrayList<Employee>) employeeDAO.getAll();
        for (Employee dto:all) {
            list.add(new Employee(dto.getEmployeeID(), dto.getName(), dto.getUserID(),dto.getDOB(), dto.getNIC(),
                    dto.getAge(), dto.getGender(), dto.getAddress(), dto.getSalary(), dto.getContact(), dto.getEmail()));
        }
        return list;
    }

    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException{
       return employeeDAO.update(employee);
    }
}
