package Allie.User;


import Allie.Booking.Booking;
import Allie.Car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
/*
    @Autowired
    private UserService service;

    public UserController(UserDAO dao) {
        this.service = new UserService(dao);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    User GetById(@PathVariable int id) throws SQLException {
        return service.GetById(id);
    }

    @GetMapping("/")
    public List<User> getAll() throws SQLException {
        return service.GetAll();
    }

    @PostMapping("/")
    User Add(@RequestBody User user) throws SQLException {
        return service.Add(user);
    }

    @PostMapping("/book/{userId}/{carId}")
    Booking RentACar(@PathVariable int userId, @PathVariable int carId, @RequestParam(value = "days", required = false) int days) throws SQLException {
        User user = service.dao.GetById(userId);
        Car car = service.carDao.GetById(carId);
        return service.bookingDao.Add(service.RentACar(user, car, days));
    }
*/
}
