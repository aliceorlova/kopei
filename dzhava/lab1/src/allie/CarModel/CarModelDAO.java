package allie.CarModel;

import allie.CarBrand.CarBrandModel;
import allie.CrudDAO;
import allie.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarModelDAO implements CrudDAO<CarModelModel> {

    @Override
    public void Add(CarModelModel item) throws SQLException {
        Connection connection = DatabaseConnector.connect();
        String query = "INSERT INTO dbBooking.dbo.CarModels VALUES (?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, item.getId());
            statement.setString(2, item.getModelName());
            statement.setInt(3, item.getYearOfCreation());
            statement.setLong(4, item.getBrand().getId());
            statement.executeUpdate();
            System.out.println("Car Model Added!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
    }

    @Override
    public void Delete(CarModelModel item) throws SQLException {
        String query = "DELETE FROM dbBooking.dbo.CarModels WHERE modelId=?";
        Connection connection = DatabaseConnector.connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, item.getId());
            preparedStatement.executeUpdate();
            System.out.println("Model deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
    }

    @Override
    public void DeleteById(int id) throws SQLException {
        String query = "DELETE FROM dbBooking.dbo.CarModels WHERE modelId=?";
        Connection connection = DatabaseConnector.connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Model deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
    }

    @Override
    public void Update(CarModelModel item) throws SQLException {
        String query = "UPDATE dbBooking.dbo.CarModels SET modelName=?, yearOfCreation=?";
        Connection connection = DatabaseConnector.connect();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, item.getModelName());
            preparedStatement.setInt(2, item.getYearOfCreation());
            preparedStatement.executeUpdate();
            System.out.println("Model Updated");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
    }

    @Override
    public List<CarModelModel> GetAll() throws SQLException {
        Connection connection = DatabaseConnector.connect();
        List<CarModelModel> modelsList = new ArrayList<>();
        String query = "SELECT modelId, modelName, yearOfCreation, brandId FROM dbBooking.dbo.CarModels";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                CarModelModel model = new CarModelModel(resultSet.getLong("modelId"), resultSet.getString("modelName"), resultSet.getInt("yearOfCreation"), (CarBrandModel) resultSet.getObject("brandId"));
                modelsList.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
        return modelsList;
    }

    @Override
    public CarModelModel GetById(int id) throws SQLException {
        CarModelModel model = new CarModelModel();
        Connection connection = DatabaseConnector.connect();
        String query = "SELECT * FROM dbBooking.dbo.CarModels WHERE modelId=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                model.setId(resultSet.getLong("modelId"));
                model.setModelName(resultSet.getString("modelName"));
                model.setYearOfCreation(resultSet.getInt("yearOfCreation"));
                model.setBrand((CarBrandModel) resultSet.getObject("brandId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
        return model;
    }
}
