package Allie.Booking;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(path = "/bookings")
public class BookingController {
    @Autowired
    private BookingService service;

    /*  public BookingController(BookingDAO dao) {
          this.service = new BookingService(dao);
      }
 public BookingController(){
        service = new BookingService();
    }
    @Autowired
    public BookingController(IRepository<Booking> rep) {
        this.service = new BookingService(rep);
    }
 */

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    Booking GetById(@PathVariable int id) throws SQLException {
        return service.GetById(id);
    }

    @GetMapping("/")
    public Iterable<Booking> getAll() throws SQLException {
        return service.GetAll();
    }
}
