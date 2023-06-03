package bo.impl;

import bo.ItemBO;
import dao.DAOFactory;
import dao.ItemDAO;
import dao.impl.ItemDAOImpl;
import dto.Customer;
import dto.Item;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public List<String> loadID() throws SQLException, ClassNotFoundException{
        return itemDAO.loadID();
    }

    @Override
    public Item searchById(String ID) throws SQLException, ClassNotFoundException{
        return itemDAO.searchById(ID);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException{
        return itemDAO.delete(id);
    }

    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException{
        ArrayList<Item> list = new ArrayList<>();
        ArrayList<Item> all = (ArrayList<Item>) itemDAO.getAll();
        for (Item dto:all) {
            list.add(new Item(dto.getItemID(), dto.getMan_Date(), dto.getExp_Date(), dto.getSupplier_name(), dto.getType(), dto.getSupplier_contact(), dto.getDescription(), dto.getQuantity(), dto.getPrice(), dto.getName()));
        }
        return list;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException{
        return itemDAO.getNextId();
    }

    @Override
    public String splitId(String currentId){
        return itemDAO.splitId(currentId);
    }

    @Override
    public boolean save(Item item) throws SQLException, ClassNotFoundException, FileNotFoundException {
        return itemDAO.save(item);
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException{
        return itemDAO.update(item);
    }
}
