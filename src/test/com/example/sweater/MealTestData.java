package com.example.sweater;

import com.example.sweater.model.Meal;
import com.example.sweater.to.MealTO;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import static com.example.sweater.RestaurantTestData.MINDAL;
import static com.example.sweater.RestaurantTestData.PEPERONI;
import static com.example.sweater.RestaurantTestData.PERCHINI;

public class MealTestData {

    public static final Meal EGGPLANT = new Meal(100008, "Spicy grilled eggplant", 350.00, LocalDate.now(), PEPERONI);
    public static final Meal BURGER = new Meal(100009, "Truffle burger", 650.00, LocalDate.now(), PEPERONI);
    public static final Meal EGG = new Meal(100010, "Egg benedict with hollandaise sauce", 580.00, LocalDate.now(), PEPERONI);
    public static final Meal FOCACCIA = new Meal(100011, "Focaccia with burrata and basil", 250.00, LocalDate.now(), PERCHINI);
    public static final Meal TUNA = new Meal(100012, "Sliced tuna with pak-choi", 470.00, LocalDate.now(), PERCHINI);
    public static final Meal PIZZA = new Meal(100013, "Pizza with nutella and berries", 330.00, LocalDate.now(), PERCHINI);

    public static final Meal QUESADILLA = new Meal(100014, "Сhicken quesadilla", 310.00, LocalDate.now().minus(Period.ofDays(1)), PEPERONI);
    public static final Meal PASTA = new Meal(100015, "Pasta pesto", 350.00, LocalDate.now().minus(Period.ofDays(1)), PEPERONI);
    public static final Meal FONDANT = new Meal(100016, "Сake fondant", 190.00, LocalDate.now().minus(Period.ofDays(1)), PEPERONI);
    public static final Meal SANDWICH = new Meal(100017, "Ham sandwich", 275.00, LocalDate.now().minus(Period.ofDays(1)), PERCHINI);
    public static final Meal PANCAKE = new Meal(100018, "Pancake with meat", 108.00, LocalDate.now().minus(Period.ofDays(1)), PERCHINI);
    public static final Meal RABBIT = new Meal(100019, "Grilled rabbit", 180.00, LocalDate.now().minus(Period.ofDays(1)), PERCHINI);

    public static final MealTO STEAK = new MealTO(null,"Beef steak", 590.00);

    public static final Meal NOT_EXISTENT_MEAL = new Meal(1, "Fried crocodile", 900.00, LocalDate.now(), MINDAL);

    public static final List<Meal> ALL_PEPERONI_MEALS = List.of(EGGPLANT, BURGER, EGG, QUESADILLA, PASTA, FONDANT);
    public static final List<Meal> ALL_PEPERONI_MEALS_WITHOUT_EGGPLANT = List.of(BURGER, EGG, QUESADILLA, PASTA, FONDANT);

    public static final List<Meal> PEPERONI_MEALS_TODAY = List.of(EGGPLANT, BURGER, EGG);
    public static final List<Meal> PERCHINI_MEALS_TODAY = List.of(FOCACCIA, TUNA, PIZZA);
}
