package bo.impl;

import bo.InhouseBO;
import dao.DAOFactory;
import dao.InhouseDAO;
import dao.PetDAO;
import entity.Inhouse;
import entity.Pet;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class InhouseBOImpl implements InhouseBO {

    PetDAO petDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PET);
    InhouseDAO inhouseDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.INHOUSE);

    @Override
    public List<String> loadPetID() throws SQLException, ClassNotFoundException{
        return petDAO.loadID();
    }

    @Override
    public String splitId(String currentId){
        return inhouseDAO.splitId(currentId);
    }

    @Override
    public List<Inhouse> getAll() throws SQLException, ClassNotFoundException{
        return inhouseDAO.getAll();
    }

    @Override
    public List<String> loadID() throws SQLException, ClassNotFoundException{
        return inhouseDAO.loadID();
    }

    @Override
    public Inhouse searchById(String inhouseID) throws SQLException, ClassNotFoundException{
        return inhouseDAO.searchById(inhouseID);
    }

    @Override
    public boolean save(Inhouse inhouse) throws SQLException, ClassNotFoundException, FileNotFoundException {
        return inhouseDAO.save(inhouse);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException{
        return inhouseDAO.delete(id);
    }

    @Override
    public boolean update(Inhouse inhouse) throws SQLException, ClassNotFoundException{
        return inhouseDAO.update(inhouse);
    }

    @Override
    public Pet searchPetById(String ID) throws SQLException, ClassNotFoundException{
        return petDAO.searchById(ID);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException{
        return inhouseDAO.getNextId();
    }
}
