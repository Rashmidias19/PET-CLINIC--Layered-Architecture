package bo;

import dao.CustomerDAO;
import dao.impl.CustomerDAOImpl;
import dto.Bill;
import dto.Customer;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl {
    CustomerDAO customerDAO =new CustomerDAOImpl();

    public String splitId(String currentId){
        return customerDAO.splitId(currentId);
    }

    public List<Customer> getAll() throws SQLException, ClassNotFoundException{
        ArrayList<Bill> list = new ArrayList<>();
        ArrayList<Bill> all = (ArrayList<Bill>) billDAO.getAll();
        for (Bill bill:all) {
            list.add(new Bill(bill.getBillID(),bill.getCustomerID(),bill.getDate(),bill.getTime(),bill.getAmount(),bill.getContact(),bill.getEmail(),bill.getDescription()));
        }
        return list;
    }


    public List<String> loadID() throws SQLException, ClassNotFoundException{}


    public boolean delete(String id) throws SQLException, ClassNotFoundException{}


    public String getNextId() throws SQLException, ClassNotFoundException{}


    public boolean save(Customer customer) throws SQLException, FileNotFoundException, ClassNotFoundException{}

    public Customer searchById(String ID) throws SQLException, ClassNotFoundException{}

    public boolean update(Customer customer) throws SQLException, ClassNotFoundException{}
}
