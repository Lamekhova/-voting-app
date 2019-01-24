package com.example.sweater;

import com.example.sweater.model.Restaurant;

import java.util.List;

public class RestaurantTestData {

    public static final Restaurant PEPERONI = new Restaurant(100005, "Peperoni", "Lenin Avenue, 34", null);
    public static final Restaurant PERCHINI = new Restaurant(100006, "Perchini", "Russian Avenue, 168", null);
    public static final Restaurant MINDAL = new Restaurant(100007, "Mindal", "Lenin Avenue, 188", null);

    public static Restaurant WOOD = new Restaurant("Wood", "Baker street, 10", null);

    public static final Restaurant WITH_DUPLICATE_NAME = new Restaurant("Mindal", "S175 Washington Pl.", null);
    public static final Restaurant NOT_EXISTENT_RESTAURANT = new Restaurant(1, "KartoFun", "225 Park Ave. S", null);

    public static final List<Restaurant> RESTAURANT_LIST = List.of(MINDAL, PEPERONI, PERCHINI);
    public static final List<Restaurant> RESTAURANT_LIST_WITHOUT_MINDAL = List.of(PEPERONI, PERCHINI);
}
