package Allie.Car;

import Allie.Model.ModelRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRowMapper implements RowMapper<Car> {
    @Override
    public Car mapRow(ResultSet resultSet, int i) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getLong("carId"));
        car.setCarNumber(resultSet.getString("carNumber"));
        car.setPrice(resultSet.getInt("price"));
        car.setTaken(resultSet.getBoolean("isTaken"));
        car.setModelId(resultSet.getLong("modelId"));
        car.setCarModelModel(CarDAO.modelDAO.GetById((resultSet.getInt("modelId"))));
        return car;
    }
}
