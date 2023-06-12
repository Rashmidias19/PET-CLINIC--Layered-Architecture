package dao;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T,ID,K,P> extends SuperDAO{

    ID splitId(ID currentId);

    List<T> getAll() throws SQLException, ClassNotFoundException;

    List<ID> loadID() throws SQLException, ClassNotFoundException;

    boolean delete(ID id) throws SQLException, ClassNotFoundException;

    String getNextId() throws SQLException, ClassNotFoundException;

    boolean save(T dto) throws SQLException, ClassNotFoundException, FileNotFoundException;

    boolean saveWithPicture(T dto,K is,P fl) throws SQLException, ClassNotFoundException, FileNotFoundException;

    T searchById(ID ID) throws SQLException, ClassNotFoundException;

    boolean update(T dto) throws SQLException, ClassNotFoundException;

}
