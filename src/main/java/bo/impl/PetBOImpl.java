package bo.impl;

import bo.PetBO;
import dao.CustomerDAO;
import dao.DAOFactory;
import dao.PetDAO;
import dao.impl.CustomerDAOImpl;
import dao.impl.PetDAOImpl;
import dto.Item;
import dto.Pet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetBOImpl implements PetBO {
    PetDAO petDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PET);
    CustomerDAO customerDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public List<String> loadID() throws SQLException, ClassNotFoundException{
        return petDAO.loadID();
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException{
        return petDAO.getNextId();
    }

    @Override
    public Pet searchById(String ID) throws SQLException, ClassNotFoundException{
        return petDAO.searchById(ID);
    }

    @Override
    public boolean saveWithPicture(Pet pet, FileInputStream inp, File file) throws SQLException, FileNotFoundException, ClassNotFoundException{
        return petDAO.saveWithPicture(pet, inp, file);
    }

    @Override
    public boolean update(Pet pet) throws SQLException, ClassNotFoundException{
        return petDAO.update(pet);
    }

    @Override
    public String splitId(String currentId){
        return petDAO.splitId(currentId);
    }

    @Override
    public List<Pet> getAll() throws SQLException, ClassNotFoundException{
        ArrayList<Pet> list = new ArrayList<>();
        ArrayList<Pet> all = (ArrayList<Pet>) petDAO.getAll();
        for (Pet dto:all) {
            list.add(new Pet(dto.getPetID(), dto.getName(), dto.getCustomerID(), dto.getType(), dto.getBreed(), dto.getGender(), dto.getDOB(), dto.getAge(), dto.getAddress(), dto.getContact()));
        }
        return list;
    }

    @Override
    public List<String> loadCustomerID() throws SQLException, ClassNotFoundException{
        return customerDAO.loadID();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException{
        return petDAO.delete(id);
    }
}
