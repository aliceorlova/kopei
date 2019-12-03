package Allie.Booking;

import Allie.Car.CarRowMapper;
import Allie.User.User;
import Allie.User.UserRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingRowMapper implements RowMapper<Booking> {
    @Override
    public Booking mapRow(ResultSet resultSet, int i) throws SQLException {
        Booking booking = new Booking();
        booking.setId(resultSet.getLong("bookingId"));
        booking.setDateOfBooking(resultSet.getDate("dateOfBooking"));
        booking.setLengthOfBookingInDays(resultSet.getInt("lengthOfBookingInDays"));
        booking.setFinished(resultSet.getBoolean("isFinished"));
        booking.setUserId(resultSet.getInt("userId"));
        booking.setDateOfReturn();
        booking.setCarId(resultSet.getInt("carId"));
        booking.setCar(BookingDAO.carDAO.GetById((resultSet.getInt("carId"))));
        booking.setUser(BookingDAO.userDAO.GetById(resultSet.getInt("userId")));
        return booking;
    }
}
