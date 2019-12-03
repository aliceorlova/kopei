package Allie.Brand;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BrandRowMapper implements RowMapper<Brand> {

    @Override
    public Brand mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Brand brand = new Brand();
        brand.setId(resultSet.getLong("brandId"));
        brand.setName(resultSet.getString("brandName"));
        return brand;
    }
}
