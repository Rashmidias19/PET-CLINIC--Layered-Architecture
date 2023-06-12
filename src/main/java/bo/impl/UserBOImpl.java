package bo.impl;

import bo.UserBO;
import dao.DAOFactory;
import dao.UserDAO;
import entity.User;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException{
        return userDAO.getNextId();
    }

    @Override
    public String splitId(String currentId){
        return userDAO.splitId(currentId);
    }

    @Override
    public boolean save(User user) throws SQLException, ClassNotFoundException, FileNotFoundException {
        return userDAO.save(user);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException{
        return userDAO.delete(id);
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException{
        ArrayList<User> list = new ArrayList<>();
        ArrayList<User> all = (ArrayList<User>) userDAO.getAll();
        for (User dto:all) {
            list.add(new User(dto.getUserID(), dto.getUserName(), dto.getPassword(), dto.getEmail()));
        }
        return list;
    }

    @Override
    public User searchById(String userID) throws SQLException, ClassNotFoundException{
        return userDAO.searchById(userID);
    }

    @Override
    public List<String> loadID() throws SQLException, ClassNotFoundException{
        return userDAO.loadID();
    }

    @Override
    public boolean update(User user) throws SQLException, ClassNotFoundException{
        return userDAO.update(user);
    }
}
