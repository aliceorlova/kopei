package app.Booking;

import app.Car.Car;
import app.Car.CarRepository;
import app.Default.IService;
import app.User.User;
import app.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@org.springframework.stereotype.Service("bookingService")
public class BookingService implements IService<Booking> {

    @Autowired
    private BookingRepository repo;
    @Autowired
    CarRepository carRepo;
    @Autowired
    UserRepository userRepo;

    @Override
    public Booking Add(Booking booking) throws SQLException {
        booking.setId(GetAll().size());
        System.out.println("In Add Method.");
        return repo.save(booking);
    }

    @Override
    public void Delete(Booking booking) throws SQLException {
        repo.delete(booking);
    }

    @Override
    public void DeleteById(int id) throws SQLException {
        repo.delete((long) id);
    }

    @Override
    public void Update(Booking booking) throws SQLException {
        Booking b = repo.findOne(booking.getId());
        b.setLengthOfBookingInDays(booking.getLengthOfBookingInDays());
        repo.save(b);
    }

    @Override
    public List<Booking> GetAll() throws SQLException {
        List<Booking> list = new ArrayList<>();
        repo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Booking GetById(int id) throws SQLException {
        return repo.findOne((long) id);
    }

    public Booking RentACar(User user, Car car, int days) throws SQLException {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Booking booking = new Booking(user, car, date, days);
        System.out.println("New booking." + booking.toString());
        return booking;
    }
}
