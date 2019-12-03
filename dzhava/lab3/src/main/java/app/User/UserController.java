package app.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService service;


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
}
