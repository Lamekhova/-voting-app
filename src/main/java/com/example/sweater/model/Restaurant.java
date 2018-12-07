package com.example.sweater.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@NamedEntityGraph(name = Restaurant.GRAPH_WITH_MEALS, includeAllAttributes = true)
@Table(name = "restaurants")
public class Restaurant extends AbstractNameEntity {

    public static final String GRAPH_WITH_MEALS = "Restaurant.withMeals";

    @NotBlank
    @Column(name = "address")
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("date,id ASC")
    @JsonManagedReference(value = "restaurant-meals")
    private List<Meal> meals;

    public Restaurant() {
    }

    public Restaurant(String name, String address, List<Meal> meals) {
        super(null, name);
        this.address = address;
        this.meals = meals;
    }

    public Restaurant(Integer id, String name, String address, List<Meal> meals) {
        super(id, name);
        this.address = address;
        this.meals = meals;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

}
