package app.Car;

import app.Default.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service("CarService")
public class CarService implements IService<Car> {

    @Autowired
    private CarRepository carRepo;

    @Override
    public Car Add(Car car) throws SQLException {
        car.setId(GetAll().size());
        return carRepo.save(car);
    }

    @Override
    public void Delete(Car car) throws SQLException {
        carRepo.delete(car);
    }

    @Override
    public void DeleteById(int id) throws SQLException {
        carRepo.delete((long) id);
    }

    @Override
    public void Update(Car car) throws SQLException {
        Car c = carRepo.findOne(car.getId());
        c.setCarNumber(car.getCarNumber());
        carRepo.save(c);
    }

    @Override
    public List<Car> GetAll() throws SQLException {
        List<Car> list = new ArrayList<>();
        carRepo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Car GetById(int id) throws SQLException {
        return carRepo.findOne((long) id);
    }
}
