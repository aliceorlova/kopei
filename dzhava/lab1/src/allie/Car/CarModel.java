package allie.Car;

import allie.CarModel.CarModelModel;
import allie.Item;

public class CarModel implements Item {
    private long CarId;
    private String CarNumber;
    private CarModelModel CarModel;
    private boolean IsTaken;
    private int Price;

    public CarModel(){

    }
    public CarModel(long Id, String number, CarModelModel model, int price) {
        IsTaken = false;
        CarId = Id;
        CarNumber = number;
        CarModel = model;
        Price = price;
    }

    public long getId() {
        return CarId;
    }

    public void setId(long carId) {
        CarId = carId;
    }

    public String getCarNumber() {
        return CarNumber;
    }

    public void setCarNumber(String carNumber) {
        CarNumber = carNumber;
    }

    public CarModelModel getCarModelModel() {
        return CarModel;
    }

    public void setCarModelModel(CarModelModel carModel) {
        CarModel = carModel;
    }

    public boolean isTaken() {
        return IsTaken;
    }

    public void setTaken(boolean taken) {
        IsTaken = taken;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    @Override
    public String toString() {
        return String.format("Car: Brand: %s, Model: %s, Number: %s, Price: %s", this.getCarModelModel().getBrand().getName(), this.getCarModelModel().getModelName(), this.getCarNumber(), this.getPrice());
    }
}
