package app.Booking;


import app.Car.Car;
import app.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Calendar;

@RestController
@RequestMapping(path = "/bookings")
public class BookingController {
    @Autowired
    private BookingService service;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    Booking GetById(@PathVariable int id) throws SQLException {
        return service.GetById(id);
    }

    @GetMapping("/")
    public Iterable<Booking> getAll() throws SQLException {
        return service.GetAll();
    }

    @PostMapping("/{userId}/{carId}")
    Booking RentACar(@PathVariable int userId, @PathVariable int carId, @RequestParam(value = "days", required = false) int days) throws SQLException {
        User user = service.userRepo.findOne((long) userId);
        Car car = service.carRepo.findOne((long) carId);
        return service.Add(service.RentACar(user, car, days));
    }
}
