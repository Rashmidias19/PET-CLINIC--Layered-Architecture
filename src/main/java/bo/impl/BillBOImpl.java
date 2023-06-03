package bo.impl;

import bo.BillBO;
import dao.BillDAO;
import dao.CustomerDAO;
import dao.DAOFactory;
import dao.ItemDAO;
import dao.impl.BillDAOImpl;
import dao.impl.CustomerDAOImpl;
import dao.impl.ItemDAOImpl;
import dao.impl.util.CrudUtil;
import dto.Bill;
import dto.Customer;
import dto.Item;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillBOImpl implements BillBO {
    BillDAO billDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BILL);
    CustomerDAO customerDAO =DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO =DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public List<String> loadID() throws SQLException, ClassNotFoundException {
        return billDAO.loadID();
    }

    @Override
    public List<Bill> getAll() throws SQLException, ClassNotFoundException{
        ArrayList<Bill> list = new ArrayList<>();
        ArrayList<Bill> all = (ArrayList<Bill>) billDAO.getAll();
        for (Bill bill:all) {
            list.add(new Bill(bill.getBillID(),bill.getCustomerID(),bill.getDate(),bill.getTime(),bill.getAmount(),bill.getContact(),bill.getEmail(),bill.getDescription()));
        }
        return list;
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException{
        return billDAO.delete(id);
    }

    @Override
    public ArrayList<String> loadCustomerID() throws SQLException, ClassNotFoundException{
        return (ArrayList<String>) customerDAO.loadID();
    }

    @Override
    public List<String> loadItemID() throws SQLException, ClassNotFoundException{
        return itemDAO.loadID();
    }


    @Override
    public String getNextId() throws SQLException, ClassNotFoundException{
        return billDAO.getNextId();
    }

    @Override
    public String splitId(String currentId){
        return billDAO.splitId(currentId);
    }

    @Override
    public boolean save(Bill bill) throws SQLException, ClassNotFoundException, FileNotFoundException {
        return billDAO.save(bill);
    }

    @Override
    public Item searchByItemId(String ID) throws SQLException, ClassNotFoundException{
        return itemDAO.searchById(ID);
    }

    @Override
    public Customer searchByCustomerId(String ID) throws SQLException, ClassNotFoundException{
        return customerDAO.searchById(ID);
    }
}
