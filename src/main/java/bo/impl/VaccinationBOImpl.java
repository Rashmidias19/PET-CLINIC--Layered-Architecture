package bo.impl;

import bo.VaccinationBO;
import dao.DAOFactory;
import dao.PetDAO;
import dao.VaccinationDAO;
import entity.Pet;
import entity.VaccinationSchedule;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VaccinationBOImpl implements VaccinationBO {
    VaccinationDAO vaccinationDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.VACCINATION);
    PetDAO petDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PET);

    @Override
    public List<String> loadPetID() throws SQLException, ClassNotFoundException{
        return petDAO.loadID();
    }

    @Override
    public Pet searchPetById(String ID) throws SQLException, ClassNotFoundException{
        return petDAO.searchById(ID);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException{
        return vaccinationDAO.getNextId();
    }

    @Override
    public String splitId(String currentId){
        return vaccinationDAO.splitId(currentId);
    }

    @Override
    public List<VaccinationSchedule> getAll() throws SQLException, ClassNotFoundException{
        ArrayList<VaccinationSchedule> list = new ArrayList<>();
        ArrayList<VaccinationSchedule> all = (ArrayList<VaccinationSchedule>) vaccinationDAO.getAll();
        for (VaccinationSchedule dto:all) {
            list.add(new VaccinationSchedule(dto.getVaccinationID(), dto.getPetID(), dto.getCustomerID(), dto.getDate(), dto.getTime(), dto.getDescription(), dto.getContact()));
        }
        return list;
    }

    @Override
    public boolean save(VaccinationSchedule vaccinationSchedule) throws SQLException, ClassNotFoundException, FileNotFoundException {
        return vaccinationDAO.save(vaccinationSchedule);
    }
}
