package Allie.Brand;

import Allie.Default.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import Allie.Default.DAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;


@Repository
public class BrandDAO // implements DAO<Brand> {
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public BrandDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

  //  @Override
    public Brand Add(Brand brand) throws SQLException {
        int id = GetAll().size();
        String query = "INSERT INTO dbBooking.dbo.CarBrands VALUES (" + id + ",'" + brand.getName() + "')";
        System.out.println("Inserted!");
        jdbcTemplate.update(query);
        return brand;
    }

  //  @Override
    public int Delete(Brand brand) throws SQLException {
        String query = "DELETE FROM dbBooking.dbo.CarBrands WHERE brandId='" + brand.getId() + "'";
        return jdbcTemplate.update(query);
    }

  //  @Override
    public int DeleteById(int id) throws SQLException {
        String query = "DELETE FROM dbBooking.dbo.CarBrands WHERE brandId='" + id + "'";
        return jdbcTemplate.update(query);
    }

 //   @Override
    public List<Brand> GetAll() throws SQLException {
        String query = "SELECT * FROM dbBooking.dbo.CarBrands";
        return this.jdbcTemplate.query(query, new BrandRowMapper());
    }

 //   @Override
    public Brand GetById(int id) throws SQLException {
        String query = "SELECT * FROM dbBooking.dbo.CarBrands WHERE brandId = ?";
        return this.jdbcTemplate.queryForObject(query, new Object[]{id}, new BrandRowMapper());
    }

 //   @Override
    public int Update(Brand brand) throws SQLException {
        String query = "UPDATE dbBooking.dbo.CarBrands SET brandName='" + brand.getName() + "' ";
        return jdbcTemplate.update(query);
    }
}
