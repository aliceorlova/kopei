package Allie.Car;

import Allie.Default.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

@org.springframework.stereotype.Service("CarService")
public class CarService implements Service<Car> {


    // private CarDAO dao;
    //  private IRepository<Car> carRepo;

    @Autowired
    private CarRepository carRepo;
   /* CarService(IRepository<Car> repo) {
        carRepo = repo;
    }
*/
   /* CarService(CarDAO dao) {
        this.dao = dao;
    }*/

    @Override
    public Car Add(Car car) throws SQLException {
        // return dao.Add(car);
        return carRepo.save(car);
    }

    @Override
    public void Delete(Car car) throws SQLException {
        //  return dao.Delete(car);
        carRepo.delete(car);
    }

    @Override
    public void DeleteById(int id) throws SQLException {
        //  return dao.DeleteById(id);
        carRepo.delete((long) id);
    }

    @Override
    public void Update(Car car) throws SQLException {
        //  return dao.Update(car);
        Car c = carRepo.findOne(car.getId());
        c.setCarNumber(car.getCarNumber());
        carRepo.save(c);
    }

    @Override
    public Iterable<Car> GetAll() throws SQLException {
        //  return dao.GetAll();
        return carRepo.findAll();
    }

    @Override
    public Car GetById(int id) throws SQLException {
        //  return dao.GetById(id);
        return carRepo.findOne((long) id);
    }
}
