package app.Default;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    T Add(T item) throws SQLException;

    void Delete(T item) throws SQLException;

    void DeleteById(int id) throws SQLException;

    T Update(T item) throws SQLException;

    List<T> GetAll() throws SQLException;

    T GetById(int id) throws SQLException;
}
