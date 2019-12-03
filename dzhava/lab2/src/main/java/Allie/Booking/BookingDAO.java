package Allie.Booking;

import Allie.Car.CarDAO;
import Allie.Default.DAO;
import Allie.Model.ModelDAO;
import Allie.User.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookingDAO implements DAO<Booking> {
    static CarDAO carDAO;
    static UserDAO userDAO;

    @Autowired
    public void setBrandDAO(CarDAO dao) {
        carDAO = dao;
    }

    @Autowired
    public void setBrandDAO(UserDAO dao) {
        userDAO = dao;
    }

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BookingDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Booking Add(Booking booking) throws SQLException {
        int id = GetAll().size();
        String query = "INSERT INTO dbBooking.dbo.Bookings VALUES ('" + id + "','" + booking.getUser().getId() + "', '" + booking.getCar().getId() + "','" + booking.getDateOfBooking() + "','" + booking.getLengthOfBookingInDays() + "','" + booking.isFinished() + "','" + booking.getDateOfBooking() +"')";
        jdbcTemplate.update(query);
        return booking;
    }

    @Override
    public int Delete(Booking booking) throws SQLException {

        String query = "DELETE FROM dbBooking.dbo.Bookings WHERE bookingId='" + booking.getId() + "'";
        return jdbcTemplate.update(query);
    }

    @Override
    public int DeleteById(int id) throws SQLException {
        String query = "DELETE FROM dbBooking.dbo.Bookings WHERE bookingId='" + id + "'";
        return jdbcTemplate.update(query);
    }

    @Override
    public List<Booking> GetAll() throws SQLException {
        String query = "SELECT * FROM dbBooking.dbo.Bookings";
        return this.jdbcTemplate.query(query, new BookingRowMapper());
    }

    @Override
    public Booking GetById(int id) throws SQLException {
        String query = "SELECT * FROM dbBooking.dbo.Bookings WHERE bookingId=?";
        return this.jdbcTemplate.queryForObject(query, new Object[]{id}, new BookingRowMapper());
    }

    @Override
    public int Update(Booking booking) throws SQLException {
        String query = "UPDATE dbBooking.dbo.Bookings SET lengthOfBookingInDays='" + booking.getLengthOfBookingInDays() + "' ";
        return jdbcTemplate.update(query);
    }
}
