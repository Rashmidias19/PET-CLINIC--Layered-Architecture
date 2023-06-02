package bo;

import dao.BillDAO;
import dao.CustomerDAO;
import dao.ItemDAO;
import dao.impl.BillDAOImpl;
import dao.impl.CustomerDAOImpl;
import dao.impl.ItemDAOImpl;
import dao.impl.util.CrudUtil;
import dto.Bill;
import dto.Customer;
import dto.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillBOImpl {
    BillDAO billDAO =new BillDAOImpl();
    CustomerDAO customerDAO =new CustomerDAOImpl();
    ItemDAO itemDAO =new ItemDAOImpl();

    public List<String> loadID() throws SQLException, ClassNotFoundException {
        return billDAO.loadID();
    }

    public List<Bill> getAll() throws SQLException, ClassNotFoundException{
        ArrayList<Bill> list = new ArrayList<>();
        ArrayList<Bill> all = (ArrayList<Bill>) billDAO.getAll();
        for (Bill bill:all) {
            list.add(new Bill(bill.getBillID(),bill.getCustomerID(),bill.getDate(),bill.getTime(),bill.getAmount(),bill.getContact(),bill.getEmail(),bill.getDescription()));
        }
        return list;
    }


    public boolean delete(String id) throws SQLException, ClassNotFoundException{
        return billDAO.delete(id);
    }

    public ArrayList<String> loadCustomerID() throws SQLException, ClassNotFoundException{
        return (ArrayList<String>) customerDAO.loadID();
    }

    public List<String> loadItemID() throws SQLException, ClassNotFoundException{
        return itemDAO.loadID();
    }


    public String getNextId() throws SQLException, ClassNotFoundException{
        return billDAO.getNextId();
    }

    public String splitId(String currentId){
        return billDAO.splitId(currentId);
    }

    public boolean save(Bill bill) throws SQLException, ClassNotFoundException{
        return billDAO.save(bill);
    }

    public Item searchByItemId(String ID) throws SQLException, ClassNotFoundException{
        return itemDAO.searchById(ID);
    }

    public Customer searchByCustomerId(String ID) throws SQLException, ClassNotFoundException{
        return customerDAO.searchById(ID);
    }
}
