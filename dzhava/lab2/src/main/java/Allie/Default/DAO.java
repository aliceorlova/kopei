package Allie.Default;

import java.sql.SQLException;
import java.util.List;


public interface DAO<T> {
    T Add(T item) throws SQLException;

    int Delete(T item) throws SQLException;

    int DeleteById(int id) throws SQLException;

    int Update(T item) throws SQLException;

    List<T> GetAll() throws SQLException;

    T GetById(int id) throws SQLException;
}
