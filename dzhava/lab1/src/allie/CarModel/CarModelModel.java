package allie.CarModel;

import allie.Car.CarModel;
import allie.CarBrand.CarBrandModel;
import allie.Item;

public class CarModelModel implements Item {
    private long ModelId;
    private String ModelName;
    private int YearOfCreation;
    private CarBrandModel Brand;

    public CarModelModel(){

    }

    public CarModelModel(long id, String name, int year, CarBrandModel brand){
    ModelId = id;
    ModelName = name;
    YearOfCreation = year;
    Brand = brand;
    }

    public long getId() {
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

    public CarBrandModel getBrand() {
        return Brand;
    }

    public void setBrand(CarBrandModel brand) {
        Brand = brand;
    }

    @Override
    public String toString() {
        return String.format("Car Model: Brand: %s, Year: %s, Name %s", this.getBrand().getName(), this.getYearOfCreation(), this.getModelName());
    }
}
