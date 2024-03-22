package com.project.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Product extends BaseModel implements Serializable {
    String title;
    String description;
    Double price;
    String imageUrl;
    int numberOfSales;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    Category category;
}
