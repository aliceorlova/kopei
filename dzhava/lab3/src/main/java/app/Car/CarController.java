package app.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(path = "/cars")
public class CarController {
    @Autowired
    CarService service;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    Car GetById(@PathVariable int id) throws SQLException {
        return service.GetById(id);
    }

    @GetMapping("/")
    public Iterable<Car> getAll() throws SQLException {
        return service.GetAll();
    }
}
