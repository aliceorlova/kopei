package allie;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> {

    void Add(T item) throws SQLException;

    void Delete(T item) throws SQLException;

    void DeleteById(int id) throws SQLException;

    void Update(T item) throws SQLException;

    List<T> GetAll() throws SQLException;

    T GetById(int id) throws SQLException;
}
