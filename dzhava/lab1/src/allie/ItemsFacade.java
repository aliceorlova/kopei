package allie;

import allie.Car.CarDAO;
import allie.Car.CarModel;
import allie.CarBrand.CarBrandDAO;
import allie.CarBrand.CarBrandModel;
import allie.CarModel.CarModelDAO;
import allie.CarModel.CarModelModel;
import allie.User.UserDAO;
import allie.User.UserModel;

import java.sql.SQLException;

public class ItemsFacade {
    private UserDAO User;
    private CarBrandDAO CarBrand;
    private CarModelDAO CarModel;
    private CarDAO Car;

    public UserDAO getUser(){
        return User;
    }
    public CarBrandDAO getCarBrand(){
        return CarBrand;
    }
    public CarModelDAO getCarModel(){
        return CarModel;
    }
    public CarDAO getCar(){
        return Car;
    }

    public ItemsFacade() {
        this.User = new UserDAO();
        this.CarBrand = new CarBrandDAO();
        this.CarModel = new CarModelDAO();
        this.Car = new CarDAO();
    }

    public void AddItem(Item item) throws SQLException {
        if(item instanceof UserModel) this.User.Add((UserModel) item);
        if(item instanceof CarBrandModel) this.CarBrand.Add((CarBrandModel) item);
        if(item instanceof CarModelModel) this.CarModel.Add((CarModelModel) item);
        if(item instanceof allie.Car.CarModel) this.Car.Add((allie.Car.CarModel) item);
        System.out.println(item.toString());
    }
}
