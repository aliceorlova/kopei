package allie.User;
import allie.CrudDAO;
import allie.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<UserModel> {


    public void Add(UserModel item) throws SQLException {
        item.setBlocked(false);
        Connection connection = DatabaseConnector.connect();
        String query = "INSERT INTO dbBooking.dbo.Users VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, item.getId());
            statement.setString(2, item.getFirstName());
            statement.setString(3, item.getLastName());
            statement.setString(4, item.getEmail());
            statement.setString(5, item.getPassword());
            statement.setBoolean(6, item.isBlocked());
            statement.executeUpdate();
            System.out.println("User Added!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
    }

    @Override
    public void Delete(UserModel item) throws SQLException {
        String query = "DELETE FROM dbBooking.dbo.Users WHERE userId=?";
        Connection connection = DatabaseConnector.connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, item.getId());
            preparedStatement.executeUpdate();
            System.out.println("User deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
    }

    @Override
    public void DeleteById(int id) throws SQLException {
        String query = "DELETE FROM dbBooking.dbo.Users WHERE userId=?";
        Connection connection = DatabaseConnector.connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
    }

    @Override
    public void Update(UserModel item) throws SQLException {
        String query = "UPDATE dbBooking.dbo.Users SET firstName=?, lastName=?";
        Connection connection = DatabaseConnector.connect();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, item.getFirstName());
            preparedStatement.setString(2, item.getLastName());
            preparedStatement.executeUpdate();
            System.out.println("User Updated");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
    }

    @Override
    public List<UserModel> GetAll() throws SQLException {
        Connection connection = DatabaseConnector.connect();
        List<UserModel> usersList = new ArrayList<>();
        String query = "SELECT userId, firstName, lastName, email, customerPassword FROM dbBooking.dbo.Users";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                UserModel user = new UserModel(resultSet.getLong("userId"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("email"), resultSet.getString("customerPassword"));
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
        return usersList;
    }

    @Override
    public UserModel GetById(int id) throws SQLException {
        UserModel user = new UserModel();
        Connection connection = DatabaseConnector.connect();
        String query = "SELECT * FROM dbBooking.dbo.Users WHERE userId=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                user.setId(resultSet.getLong("userId"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("customerPassword"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
        return user;
    }
}
