package Allie.Car;

import Allie.Default.DAO;
import Allie.Model.ModelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CarDAO implements DAO<Car> {

    private JdbcTemplate jdbcTemplate;
    static ModelDAO modelDAO;

    @Autowired
    public void setBrandDAO(ModelDAO dao) {
        this.modelDAO = dao;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CarDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Car Add(Car car) throws SQLException {
        int id = GetAll().size();
        String query = "INSERT INTO dbBooking.dbo.Cars VALUES ('" + id + "','" + car.getCarNumber() + "','" + car.getCarModelModel().getId() + "','" + car.isTaken() + "','" + car.getPrice() + "')";
        jdbcTemplate.update(query);
        return car;
    }

    @Override
    public int Delete(Car car) throws SQLException {
        String query = "DELETE FROM dbBooking.dbo.Cars WHERE carId='" + car.getId() + "'";
        return jdbcTemplate.update(query);
    }

    @Override
    public int DeleteById(int id) throws SQLException {
        String query = "DELETE FROM dbBooking.dbo.Cars WHERE carId='" + id + "'";
        return jdbcTemplate.update(query);
    }

    @Override
    public List<Car> GetAll() throws SQLException {
        String query = "SELECT * FROM dbBooking.dbo.Cars";
        return this.jdbcTemplate.query(query, new CarRowMapper());
    }

    @Override
    public Car GetById(int id) throws SQLException {
        String query = "SELECT * FROM dbBooking.dbo.Cars WHERE carId=?";
        return this.jdbcTemplate.queryForObject(query, new Object[]{id}, new CarRowMapper());
    }

    @Override
    public int Update(Car car) throws SQLException {
        String query = "UPDATE dbBooking.dbo.Cars SET carNumber='" + car.getCarNumber() + "' ";
        return jdbcTemplate.update(query);
    }
}
