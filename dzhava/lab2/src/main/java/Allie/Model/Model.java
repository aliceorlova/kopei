package Allie.Model;

import Allie.Brand.Brand;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CarModels")
public class Model {
    @Id
    @Column(name="modelId")
    private long ModelId;
    @Column(name="modelName")
    private String ModelName;
    @Column(name="yearOfCreation")
    private int YearOfCreation;
    private Brand Brand;
    @Column(name="brandId")
    private long BrandId;

    public Model() {

    }

    public Model(long id, String name, int year, Brand brand) {
        ModelId = id;
        ModelName = name;
        YearOfCreation = year;
        Brand = brand;
        BrandId = brand.getId();
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
        return String.format("Allie.Car Allie.Model: Allie.Brand Id: %s, Year: %s, Name: %s", this.getBrandId(), this.getYearOfCreation(), this.getModelName());
    }

    public long getBrandId() {
        return BrandId;
    }

    public void setBrandId(long brandId) {
        BrandId = brandId;
    }
}
