package app.Model;


import app.Brand.Brand;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "CarModels", schema = "dbo")
public class Model {
    @Id
    @Column(name = "modelId")
    private long ModelId;
    @Column(name = "modelName")
    private String ModelName;
    @Column(name = "yearOfCreation")
    private int YearOfCreation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brandId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Brand Brand;

    public Model() {

    }

    public Model(long id, String name, int year, Brand brand) {
        ModelId = id;
        ModelName = name;
        YearOfCreation = year;
        Brand = brand;
    }

    public Long getId() {
        return ModelId;
    }

    public void setId(long modelId) {
        ModelId = modelId;
    }

    public String getModelName() {
        return ModelName;
    }

    public void setModelName(String modelName) {
        ModelName = modelName;
    }

    public int getYearOfCreation() {
        return YearOfCreation;
    }

    public void setYearOfCreation(int yearOfCreation) {
        YearOfCreation = yearOfCreation;
    }

    public Brand getBrand() {
        return Brand;
    }

    public void setBrand(Brand brand) {
        Brand = brand;
    }

    @Override
    public String toString() {
        return String.format("Allie.Car Allie.Model: Allie.Brand Id: %s, Year: %s, Name: %s", this.getBrand().getId(), this.getYearOfCreation(), this.getModelName());
    }
}
