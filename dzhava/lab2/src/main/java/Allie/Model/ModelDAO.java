package Allie.Model;

import Allie.Brand.BrandDAO;
import Allie.Default.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ModelDAO implements DAO<Model> {

    private JdbcTemplate jdbcTemplate;
    static BrandDAO brandDAO;

    @Autowired
    public void setBrandDAO(BrandDAO dao) {
        this.brandDAO = dao;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ModelDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Model Add(Model model) throws SQLException {
        int id = GetAll().size();
        String query = "INSERT INTO dbBooking.dbo.CarModels VALUES ('" + id + "','" + model.getModelName() + "','" + model.getYearOfCreation() + "','" + model.getBrand().getId() + "')";
        jdbcTemplate.update(query);
        return model;
    }

    @Override
    public int Delete(Model model) throws SQLException {
        String query = "DELETE FROM dbBooking.dbo.CarModels WHERE modelId='" + model.getId() + "'";
        return jdbcTemplate.update(query);
    }

    @Override
    public int DeleteById(int id) throws SQLException {
        String query = "DELETE FROM dbBooking.dbo.CarModels WHERE modelId='" + id + "'";
        return jdbcTemplate.update(query);
    }

    @Override
    public List<Model> GetAll() throws SQLException {
        String query = "SELECT * FROM dbBooking.dbo.CarModels";
        return this.jdbcTemplate.query(query, new ModelRowMapper());
    }

    @Override
    public Model GetById(int id) throws SQLException {
        String query = "SELECT * FROM dbBooking.dbo.CarModels WHERE modelId=?";
        Object[] inputs = new Object[]{id};
        return this.jdbcTemplate.queryForObject(query, new Object[]{id}, new ModelRowMapper());
    }

    @Override
    public int Update(Model model) throws SQLException {
        String query = "UPDATE dbBooking.dbo.CarModels SET modelName='" + model.getModelName() + "' ";
        return jdbcTemplate.update(query);
    }
}
