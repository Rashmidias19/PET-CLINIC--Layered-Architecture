package bo.impl;

import bo.CustomerBO;
import dao.CustomerDAO;
import dao.DAOFactory;
import dao.impl.CustomerDAOImpl;
import dto.Bill;
import dto.Customer;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public String splitId(String currentId){
        return customerDAO.splitId(currentId);
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException{
        ArrayList<Customer> list = new ArrayList<>();
        ArrayList<Customer> all = (ArrayList<Customer>) customerDAO.getAll();
        for (Customer customer:all) {
            list.add(new Customer(customer.getCustomerID(),customer.getCustTitle(), customer.getCustName(), customer.getNIC(), customer.getDOB(),
                    customer.getAge(), customer.getGender(), customer.getContact(), customer.getEmail(), customer.getAddress()));
        }
        return list;
    }


    @Override
    public List<String> loadID() throws SQLException, ClassNotFoundException{
        return customerDAO.loadID();
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException{
        return customerDAO.delete(id);
    }


    @Override
    public String getNextId() throws SQLException, ClassNotFoundException{
        return customerDAO.getNextId();
    }


    @Override
    public boolean save(Customer customer) throws SQLException, FileNotFoundException, ClassNotFoundException{
        return customerDAO.save(customer);
    }

    @Override
    public Customer searchById(String ID) throws SQLException, ClassNotFoundException{
        return customerDAO.searchById(ID);
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException{
        return customerDAO.update(customer);
    }
}
