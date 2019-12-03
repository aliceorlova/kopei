package Allie.Default;

import java.sql.SQLException;
import java.util.List;

public interface Service<T> {
    T Add(T item) throws SQLException;

    void Delete(T item) throws SQLException;

    void DeleteById(int id) throws SQLException;

    void Update(T item) throws SQLException;

    Iterable<T> GetAll() throws SQLException;

    T GetById(int id) throws SQLException;
}
