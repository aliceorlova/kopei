package app.Brand;


import app.Model.Model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CarBrands", schema = "dbo")
public class Brand {
    @Id
    @Column(name = "brandId")
    private long BrandId;
    @Column(name = "brandName")
    private String Name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "Brand")
    private List<Model> Models = new ArrayList<>();

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
