package Allie.Car;

import Allie.Model.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cars")
public class Car  {
    @Id
    @Column(name="carId")
    private long CarId;
    @Column(name="carNumber")
    private String CarNumber;
    private Model CarModel;
    @Column(name="isTaken")
    private boolean IsTaken;
    @Column(name="price")
    private int Price;
    @Column(name="modelId")
    private long ModelId;

    public Car() {

    }

    public Car(long Id, String number, Model model, int price) {
        IsTaken = false;
        CarId = Id;
        CarNumber = number;
        CarModel = model;
        Price = price;
        ModelId = model.getId();
    }

    public Long getId() {
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

    public Model getCarModelModel() {
        return CarModel;
    }

    public void setCarModelModel(Model carModel) {
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
        return String.format("Allie.Car:  Allie.Model Id: %s, Number: %s, Price: %s", this.getModelId(),  this.getCarNumber(), this.getPrice());
    }

    public long getModelId() {
        return ModelId;
    }

    public void setModelId(long modelId) {
        ModelId = modelId;
    }
}
