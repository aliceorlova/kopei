package allie;

import allie.Car.CarModel;
import allie.CarBrand.CarBrandModel;
import allie.CarModel.CarModelModel;
import allie.User.UserModel;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        ItemsFacade facade = new ItemsFacade();

        UserModel alice = new UserModel(0L, "Alice", "Orlova", "alice@gmail.com", "password");
        UserModel rowan = new UserModel(1L, "Rowan", "Whitethorn", "rowan@gmail.com", "password");
        UserModel aelin = new UserModel(2L, "Aelin", "Galathynius", "aelin@gmail.com", "password");
        facade.AddItem(alice);
        facade.AddItem(rowan);
        facade.AddItem(aelin);

        CarBrandModel toyota = new CarBrandModel(0L, "Toyota");
        CarBrandModel nissan = new CarBrandModel(1L, "Nissan");
        CarBrandModel audi = new CarBrandModel(2L, "Audi");
        facade.AddItem(toyota);
        facade.AddItem(nissan);
        facade.AddItem(audi);

        CarModelModel corolla = new CarModelModel(0L, "Corolla", 2017, toyota);
        CarModelModel camry = new CarModelModel(1L, "Camry", 2016, toyota);
        CarModelModel qashqai = new CarModelModel(2L, "Qashqai", 2019, nissan);
        CarModelModel leaf = new CarModelModel(3L, "Leaf", 2018, nissan);
        CarModelModel teana = new CarModelModel(4L, "Teana", 2015, nissan);
        CarModelModel A5 = new CarModelModel(5L,"A5",2012,audi);
        facade.AddItem(corolla);
        facade.AddItem(camry);
        facade.AddItem(qashqai);
        facade.AddItem(leaf);
        facade.AddItem(teana);
        facade.AddItem(A5);

        CarModel car1 = new CarModel(0L,"AA8000BC",qashqai,100);
        CarModel car2 = new CarModel(1L,"AA4000",teana,80);
        CarModel car3 = new CarModel(2L,"AI2300",qashqai,100);
        CarModel car4 = new CarModel(3L,"BC3200AL",camry,120);
        CarModel car5 = new CarModel(4L, "OB2000LA",corolla,110);
        CarModel car6 = new CarModel(5L,"AA1356BN",leaf,110);
        CarModel car7 = new CarModel(6L,"AA1111AM",A5,90);
        facade.AddItem(car1);
        facade.AddItem(car2);
        facade.AddItem(car3);
        facade.AddItem(car4);
        facade.AddItem(car5);
        facade.AddItem(car6);
        facade.AddItem(car7);

        alice.RentACar(car1,14);
        aelin.RentACar(car5,20);
        rowan.RentACar(car3,10);

        List<UserModel> list = facade.getUser().GetAll();
        for (UserModel user:list) {
           System.out.println(user);
        }
        System.out.println(facade.getUser().GetById(3));
    }
}
