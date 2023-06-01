package dao;

import dto.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T,ID> {

    ID splitId(ID currentId);

    List<T> getAll() throws SQLException, ClassNotFoundException;

    List<ID> loadID() throws SQLException, ClassNotFoundException;

    boolean delete(ID id) throws SQLException, ClassNotFoundException;

    ID getNextId() throws SQLException, ClassNotFoundException;

    boolean save(T dto) throws SQLException, ClassNotFoundException;

    T searchById(ID ID) throws SQLException, ClassNotFoundException;

    boolean update(T dto) throws SQLException, ClassNotFoundException;

}
