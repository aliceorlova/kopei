package allie.CarBrand;

import allie.CrudDAO;
import allie.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarBrandDAO implements CrudDAO<CarBrandModel> {
    @Override
    public void Add(CarBrandModel item) throws SQLException {
        Connection connection = DatabaseConnector.connect();
        String query = "INSERT INTO dbBooking.dbo.CarBrands VALUES (?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, item.getId());
            statement.setString(2, item.getName());
            statement.executeUpdate();
            System.out.println("Car Brand Added!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
    }

    @Override
    public void Delete(CarBrandModel item) throws SQLException {
        String query = "DELETE FROM dbBooking.dbo.CarBrands WHERE brandId=?";
        Connection connection = DatabaseConnector.connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, item.getId());
            preparedStatement.executeUpdate();
            System.out.println("Brand deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
    }

    @Override
    public void DeleteById(int id) throws SQLException {
        String query = "DELETE FROM dbBooking.dbo.CarBrands WHERE brandId=?";
        Connection connection = DatabaseConnector.connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Brand deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
    }

    @Override
    public void Update(CarBrandModel item) throws SQLException {
        String query = "UPDATE dbBooking.dbo.CarBrands SET brandName=?";
        Connection connection = DatabaseConnector.connect();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, item.getName());
            preparedStatement.executeUpdate();
            System.out.println("Brand Updated");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
    }

    @Override
    public List<CarBrandModel> GetAll() throws SQLException {
        Connection connection = DatabaseConnector.connect();
        List<CarBrandModel> brandsList = new ArrayList<>();
        String query = "SELECT brandId, brandName FROM dbBooking.dbo.CarBrands";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                CarBrandModel brand = new CarBrandModel(resultSet.getLong("brandId"), resultSet.getString("brandName"));
                brandsList.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
        return brandsList;
    }

    @Override
    public CarBrandModel GetById(int id) throws SQLException {
        CarBrandModel brand = new CarBrandModel();
        Connection connection = DatabaseConnector.connect();
        String query = "SELECT * FROM dbBooking.dbo.CarBrands WHERE brandId=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                brand.setId(resultSet.getLong("brandId"));
                brand.setName(resultSet.getString("brandName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
        return brand;
    }
}
