package com.example.sweater.web;

import com.example.sweater.model.Restaurant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static com.example.sweater.MealTestData.PEPERONI_MEALS_TODAY;
import static com.example.sweater.MealTestData.PERCHINI_MEALS_TODAY;
import static com.example.sweater.RestaurantTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RestaurantControllerTest {

    @Autowired
    protected MealController mealController;

    @Autowired
    protected RestaurantController restaurantController;

    @Test
    void addNew() {
        Restaurant restaurant = restaurantController.addNew(WOOD);
        assertEquals(restaurant, restaurantController.getById(restaurant.getId()));
    }

    @Test
    void addNewWithDuplicateName() {
        assertThrows(DataAccessException.class, () ->
                restaurantController.addNew(WITH_DUPLICATE_NAME));
    }

    @Test
    void getById() {
        assertEquals(PEPERONI, restaurantController.getById(100005));
    }

//    @Test
//    void getNotFoundById() {
//        assertThrows(NotFoundException.class, () ->
//                restaurantController.getById(NON_EXISTENT_RESTAURANT.getId()));
//    }

    //?
    @Test
    void getAll() {
        assertEquals(RESTAURANT_LIST, restaurantController.getAll());
    }

    @Test
    void getAllWithMealsByDate() {
        PEPERONI.setMeals(PEPERONI_MEALS_TODAY);
        PERCHINI.setMeals(PERCHINI_MEALS_TODAY);
        List<Restaurant> list = restaurantController.getAllWithMealsByDate(LocalDate.now());
        assertEquals(List.of(PEPERONI, PERCHINI), list);
        assertEquals(PEPERONI.getMeals(), list.get(0).getMeals());
        assertEquals(PERCHINI.getMeals(), list.get(1).getMeals());
    }

    @Test
    void update() {
        PERCHINI.setAddress("New address");
        restaurantController.update(PERCHINI.getId(), PERCHINI);
        assertEquals(PERCHINI, restaurantController.getById(PERCHINI.getId()));
    }

//    @Test
//    void updateNotFound() {
//        assertThrows(NotFoundException.class, () ->
//                restaurantController.update(NON_EXISTENT_RESTAURANT.getId(), NON_EXISTENT_RESTAURANT));
//    }

    //?
    @Test
    void deleteById() {
        restaurantController.deleteById(100007);
        assertEquals(RESTAURANT_LIST_WITHOUT_MINDAL, restaurantController.getAll());
    }

//    @Test
//    void deleteNotFoundById() {
//        assertThrows(NotFoundException.class, () ->
//                restaurantController.deleteById(NON_EXISTENT_RESTAURANT.getId()));
//
//    }
}