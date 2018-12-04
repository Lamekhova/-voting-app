package com.example.sweater.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "meals")
@NamedEntityGraph(name = Meal.GRAPH_WITH_RESTAURANT, includeAllAttributes = true)
public class Meal extends AbstractNameEntity {

    public static final String GRAPH_WITH_RESTAURANT = "Meal.withRestaurant";

    @NotNull
    @Column(name = "price", columnDefinition = "NUMERIC(7, 2) NOT NULL")
    private double price;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private LocalDate date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonBackReference(value="restaurant-meals")
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(String name, double price, LocalDate date, Restaurant restaurant) {
        super(null, name);
        this.price = price;
        this.date = date;
        this.restaurant = restaurant;
    }

    public Meal(Integer id, String name, double price, LocalDate date, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.date = date;
        this.restaurant = restaurant;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
