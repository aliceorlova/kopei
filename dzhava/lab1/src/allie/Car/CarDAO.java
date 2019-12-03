package allie.Car;

import allie.CarModel.CarModelModel;
import allie.CrudDAO;
import allie.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO implements CrudDAO<CarModel> {
    @Override
    public void Add(CarModel item) throws SQLException {
        Connection connection = DatabaseConnector.connect();
        String query = "INSERT INTO dbBooking.dbo.Cars VALUES (?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, item.getId());
            statement.setString(2, item.getCarNumber());
            statement.setLong(3, item.getCarModelModel().getId());
            statement.setBoolean(4, item.isTaken());
            statement.setInt(5,item.getPrice());
            statement.executeUpdate();
            System.out.println("Car Added!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
    }

    @Override
    public void Delete(CarModel item) throws SQLException {
        String query = "DELETE FROM dbBooking.dbo.Cars WHERE carId=?";
        Connection connection = DatabaseConnector.connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, item.getId());
            preparedStatement.executeUpdate();
            System.out.println("Car deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
    }

    @Override
    public void DeleteById(int id) throws SQLException {
        String query = "DELETE FROM dbBooking.dbo.Cars WHERE carId=?";
        Connection connection = DatabaseConnector.connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Car deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
    }

    @Override
    public void Update(CarModel item) throws SQLException {
        String query = "UPDATE dbBooking.dbo.Cars SET carNumber=?, price=?";
        Connection connection = DatabaseConnector.connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, item.getCarNumber());
            preparedStatement.setInt(2, item.getPrice());
            preparedStatement.executeUpdate();
            System.out.println("Car Updated");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
    }

    @Override
    public List<CarModel> GetAll() throws SQLException {
        Connection connection = DatabaseConnector.connect();
        List<CarModel> carsList = new ArrayList<>();
        String query = "SELECT carId, carNumber, modelId, isTaken, price FROM dbBooking.dbo.Cars";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                CarModel car = new CarModel(resultSet.getLong("carId"), resultSet.getString("carNumber"), (CarModelModel) resultSet.getObject("modelId"), resultSet.getInt("price"));
                carsList.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
        return carsList;
    }

    @Override
    public CarModel GetById(int id) throws SQLException {
        CarModel car = new CarModel();
        Connection connection = DatabaseConnector.connect();
        String query = "SELECT * FROM dbBooking.dbo.Cars WHERE carId=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                car.setId(resultSet.getLong("carId"));
                car.setCarNumber(resultSet.getString("carNumber"));
                car.setCarModelModel((CarModelModel) resultSet.getObject("modelId"));
                car.setTaken(resultSet.getBoolean("isTaken"));
                car.setPrice(resultSet.getInt("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
        return car;
    }
}
