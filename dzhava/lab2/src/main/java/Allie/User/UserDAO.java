package Allie.User;

import Allie.Booking.Booking;
import Allie.Booking.BookingDAO;
import Allie.Car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import Allie.Default.DAO;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

@Repository
public class UserDAO implements DAO<User> {
    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User Add(User user) throws SQLException {
        int id = GetAll().size();
        String query = "INSERT INTO dbBooking.dbo.Users VALUES ('" + id + "','" + user.getFirstName() + "','" + user.getLastName() + "','" + user.getEmail() + "','" + user.getPassword() + "','" + user.isBlocked() + "')";
        jdbcTemplate.update(query);
        return user;
    }

    @Override
    public int Delete(User user) throws SQLException {
        String query = "DELETE FROM dbBooking.dbo.Users WHERE userId='" + user.getId() + "'";
        return jdbcTemplate.update(query);
    }

    @Override
    public int DeleteById(int id) throws SQLException {
        String query = "DELETE FROM dbBooking.dbo.Users WHERE userId='" + id + "'";
        return jdbcTemplate.update(query);
    }

    @Override
    public int Update(User user) throws SQLException {
        String query = "UPDATE dbBooking.dbo.Users SET firstName='" + user.getFirstName() + "',lastName='" + user.getLastName() + "'WHERE userId='" + user.getId() + "' ";
        return jdbcTemplate.update(query);
    }

    @Override
    public List GetAll() throws SQLException {
        String query = "SELECT * FROM dbBooking.dbo.Users";
        return this.jdbcTemplate.query(query, new UserRowMapper());
    }

    @Override
    public User GetById(int id) throws SQLException {
        String query = "SELECT * FROM dbBooking.dbo.Users WHERE userId=?";
        Object[] inputs = new Object[]{id};
        return this.jdbcTemplate.queryForObject(query, new Object[]{id}, new UserRowMapper());
    }

    public Booking RentACar(User user, Car car, int days) throws SQLException {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Booking booking = new Booking(user, car, date, days);
        System.out.println("New booking." + booking.toString());
        return booking;
    }
}
