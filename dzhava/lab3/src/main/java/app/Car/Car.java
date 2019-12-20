package app.Car;
import app.Model.Model;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Entity
@CrossOrigin(origins = "http://localhost:3000")
@Table(name = "Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "carId")
    private long CarId;
    @Column(name = "carNumber")
    private String CarNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modelId")
    private Model CarModel;
    @Column(name = "isTaken")
    private boolean IsTaken;
    @Column(name = "price")
    private int Price;

    public Car() {

    }

    public Car(long Id, String number, Model model, int price) {
        IsTaken = false;
        CarId = Id;
        CarNumber = number;
        CarModel = model;
        Price = price;
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
        return String.format("Allie.Car:  Allie.Model Id: %s, Number: %s, Price: %s", this.getCarModelModel().getId(), this.getCarNumber(), this.getPrice());
    }

}
