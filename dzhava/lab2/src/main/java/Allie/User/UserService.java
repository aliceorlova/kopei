package Allie.User;


import Allie.Booking.Booking;
import Allie.Booking.BookingDAO;
import Allie.Car.Car;
import Allie.Car.CarDAO;
import Allie.Default.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

@org.springframework.stereotype.Service("UserService")
public class UserService // implements Service<User> {
{
    @Autowired
    UserDAO dao;
    @Autowired
    CarDAO carDao;
    @Autowired
    BookingDAO bookingDao;

/*
    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public UserService() {
    }

    @Override
    public User Add(User user) throws SQLException {
        return dao.Add(user);
    }

    @Override
    public int Delete(User user) throws SQLException {
        return dao.Delete(user);
    }

    @Override
    public int DeleteById(int id) throws SQLException {
        return dao.DeleteById(id);
    }

    @Override
    public int Update(User user) throws SQLException {
        return dao.Update(user);
    }

    @Override
    public List<User> GetAll() throws SQLException {
        return dao.GetAll();
    }

    @Override
    public User GetById(int id) throws SQLException {
        return dao.GetById(id);
    }

    public Booking RentACar(User user, Car car, int days) throws SQLException {
        return dao.RentACar(user, car, days);
    }

 */
}
