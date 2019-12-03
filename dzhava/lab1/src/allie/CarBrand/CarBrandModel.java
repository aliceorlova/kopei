package allie.CarBrand;

import allie.Item;

public class CarBrandModel implements Item {
    private long BrandId;
    private String Name;

    public CarBrandModel(){

    }
    public CarBrandModel(long Id, String Name){
        BrandId = Id;
        this.Name = Name;
    }

    public long getId() {
        return BrandId;
    }

    public void setId(long brandId) {
        BrandId = brandId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return String.format("Brand: name: %s", this.getName());
    }
}
