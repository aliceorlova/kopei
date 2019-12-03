package Allie.Booking;

import Allie.Default.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

@org.springframework.stereotype.Service("BookingService")
public class BookingService implements Service<Booking> {

    // private BookingDAO dao;
    @Autowired
    private BookingRepository repo;
   // private IRepository<Booking> repo;

    /* public BookingService(BookingDAO dao) {
         this.dao = dao;
     }
    @Autowired
    public BookingService(IRepository<Booking> rep) {
        repo = rep;
    }
*/
    @Override
    public Booking Add(Booking booking) throws SQLException {
        //  return dao.Add(booking);
        return repo.save(booking);
    }

    @Override
    public void Delete(Booking booking) throws SQLException {
        // return dao.Delete(booking);
        repo.delete(booking);
    }

    @Override
    public void DeleteById(int id) throws SQLException {
        //   return dao.DeleteById(id);
        repo.delete((long) id);
    }

    @Override
    public void Update(Booking booking) throws SQLException {
        //  return dao.Update(booking);
        Booking b = repo.findOne(booking.getId());
        b.setLengthOfBookingInDays(booking.getLengthOfBookingInDays());
        repo.save(b);
    }

    @Override
    public Iterable<Booking> GetAll() throws SQLException {
        //  return dao.GetAll();
        return repo.findAll();
    }

    @Override
    public Booking GetById(int id) throws SQLException {
        //  return dao.GetById(id);
        return repo.findOne((long) id);
    }
}
