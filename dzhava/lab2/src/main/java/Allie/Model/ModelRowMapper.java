package Allie.Model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelRowMapper implements RowMapper<Model> {

    @Override
    public Model mapRow(ResultSet resultSet, int i) throws SQLException {
        Model model = new Model();
        model.setId(resultSet.getLong("modelId"));
        model.setModelName(resultSet.getString("modelName"));
        model.setYearOfCreation(resultSet.getInt("yearOfCreation"));
        model.setBrandId(resultSet.getInt("brandId"));
        model.setBrand(ModelDAO.brandDAO.GetById((resultSet.getInt("brandId"))));
        return model;
    }
}
