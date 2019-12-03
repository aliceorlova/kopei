package Allie.Brand;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


//@Component
@Entity
@Table(name = "CarBrands")
public class Brand  {
    @Id
    @Column(name="brandId")
    private long BrandId;
    @Column(name="brandName")
    private String Name;

    public Brand() {

    }

    @JsonCreator
    public Brand(@JsonProperty("brandId") long Id, @JsonProperty("brandName") String Name) {
        BrandId = Id;
        this.Name = Name;
    }

    public Long getId() {
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
        return String.format("Allie.Brand: name: %s", this.getName());
    }
}
