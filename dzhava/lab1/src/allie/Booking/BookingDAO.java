package allie.Booking;

import allie.Car.CarModel;
import allie.CrudDAO;
import allie.DatabaseConnector;
import allie.User.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO implements CrudDAO<BookingModel> {
    @Override
    public void Add(BookingModel item) throws SQLException {
        Connection connection = DatabaseConnector.connect();
        String query = "INSERT INTO dbBooking.dbo.Bookings VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, item.getId());
            statement.setLong(2, item.getUser().getId());
            statement.setLong(3, item.getCar().getId());
            statement.setDate(4, item.getDateOfBooking());
            statement.setInt(5,item.getLengthOfBookingInDays());
            statement.setBoolean(6, item.isFinished());
            statement.executeUpdate();
            System.out.println("Booking Added!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
    }

    @Override
    public void Delete(BookingModel item) throws SQLException {
        String query = "DELETE FROM dbBooking.dbo.Bookings WHERE bookingId=?";
        Connection connection = DatabaseConnector.connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, item.getId());
            preparedStatement.executeUpdate();
            System.out.println("Booking deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
    }

    @Override
    public void DeleteById(int id) throws SQLException {
        String query = "DELETE FROM dbBooking.dbo.Bookings WHERE bookingId=?";
        Connection connection = DatabaseConnector.connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Booking deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
    }

    @Override
    public void Update(BookingModel item) throws SQLException {
        String query = "UPDATE dbBooking.dbo.Bookings SET lengthOfBookingInDays=?";
        Connection connection = DatabaseConnector.connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, item.getLengthOfBookingInDays());
            preparedStatement.executeUpdate();
            System.out.println("Booking Updated");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
    }

    @Override
    public List<BookingModel> GetAll() throws SQLException {
        Connection connection = DatabaseConnector.connect();
        List<BookingModel> bookingsList = new ArrayList<>();
        String query = "SELECT bookingId, userId, carId, dateOfBooking, lengthOfBookingInDays, isFinished FROM dbBooking.dbo.Cars";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                BookingModel booking = new BookingModel((UserModel) resultSet.getObject("userId"),(CarModel) resultSet.getObject("carId"), resultSet.getDate("dateOfBooking"),resultSet.getInt("lengthOfBookingInDays"));
                bookingsList.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
        return bookingsList;
    }

    @Override
    public BookingModel GetById(int id) throws SQLException {
        BookingModel booking = new BookingModel();
        Connection connection = DatabaseConnector.connect();
        String query = "SELECT * FROM dbBooking.dbo.Bookings WHERE bookingId=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                booking.setId(resultSet.getLong("bookingId"));
                booking.setUser((UserModel) resultSet.getObject("userId"));
                booking.setCar((CarModel) resultSet.getObject("carId"));
                booking.setDateOfBooking(resultSet.getDate("dateOfBooking"));
                booking.setLengthOfBookingInDays(resultSet.getInt("lengthOfBookingInDays"));
                booking.setFinished(resultSet.getBoolean("isFinished"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.disconnect(connection);
        }
        return booking;
    }
}
