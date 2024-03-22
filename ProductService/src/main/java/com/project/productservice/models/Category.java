package com.project.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@Entity
public class Category extends BaseModel implements Serializable {
    private String name;
    private String description;
    @OneToMany(mappedBy = "category",cascade = {CascadeType.REMOVE})
    List<Product> productList;
    public Category(String string) {
        Random random = new Random();
        id= random.nextLong();
        this.name=string;
    }
    public Category() {

    }
}
