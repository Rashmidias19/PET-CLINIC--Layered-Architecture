package bo.impl;

import bo.OperationBO;
import dao.CustomerDAO;
import dao.DAOFactory;
import dao.OperationDAO;
import dao.PetDAO;
import dao.impl.CustomerDAOImpl;
import dao.impl.OperationDAOImpl;
import dao.impl.PetDAOImpl;
import dto.Item;
import dto.OperationSchedule;
import dto.Pet;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationBOImpl implements OperationBO {
    OperationDAO operationDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.OPERATION);
    PetDAO petDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PET);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException{
        return operationDAO.getNextId();
    }

    @Override
    public boolean save(OperationSchedule operationSchedule) throws SQLException, ClassNotFoundException, FileNotFoundException {
        return operationDAO.save(operationSchedule);
    }

    @Override
    public String splitId(String currentId){
        return operationDAO.splitId(currentId);
    }

    @Override
    public List<OperationSchedule> getAll() throws SQLException, ClassNotFoundException{
        ArrayList<OperationSchedule> list = new ArrayList<>();
        ArrayList<OperationSchedule> all = (ArrayList<OperationSchedule>) operationDAO.getAll();
        for (OperationSchedule dto:all) {
            list.add(new OperationSchedule(dto.getOperationID(), dto.getPetID(), dto.getCustomerID(), dto.getDate() , dto.getTime(), dto.getDescription(), dto.getHours(), dto.getContact()));
        }
        return list;
    }

    @Override
    public Pet searchPetById(String ID) throws SQLException, ClassNotFoundException{
        return petDAO.searchById(ID);
    }

    @Override
    public List<String> loadPetID() throws SQLException, ClassNotFoundException{
        return petDAO.loadID();
    }
}
