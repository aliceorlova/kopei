package app.User;


import app.Booking.BookingRepository;
import app.Car.CarRepository;
import app.Default.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service("UserService")
public class UserService implements IService<User> {
    @Autowired
    private UserRepository userRepo;

    public UserService() {
    }

    @Override
    public User Add(User user) throws SQLException {
        user.setId(GetAll().size());
        return userRepo.save(user);
    }

    @Override
    public void Delete(User user) throws SQLException {
        userRepo.delete(user);
    }

    @Override
    public void DeleteById(int id) throws SQLException {
        userRepo.delete((long) id);
    }

    @Override
    public User Update(User user) throws SQLException {
        User u = userRepo.findOne(user.getId());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        userRepo.save(u);
        return u;
    }

    @Override
    public List<User> GetAll() throws SQLException {
        List<User> list = new ArrayList<>();
        userRepo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public User GetById(int id) throws SQLException {
        return userRepo.findOne((long) id);
    }

}
