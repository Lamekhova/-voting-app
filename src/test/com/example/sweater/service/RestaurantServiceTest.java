package com.example.sweater.service;

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
class RestaurantServiceTest {

    @Autowired
    private RestaurantService restaurantService;


    @Test
    void addNew() {
        Restaurant restaurant = restaurantService.addNew(WOOD);
        assertEquals(restaurant, restaurantService.getById(restaurant.getId()));
    }

    @Test
    void addNewWithDuplicateName() {
        assertThrows(DataAccessException.class, () ->
                restaurantService.addNew(WITH_DUPLICATE_NAME));
    }

    @Test
    void getById() {
        assertEquals(PEPERONI, restaurantService.getById(100005));
    }

//    @Test
//    void getNotFoundById() {
//        assertThrows(NotFoundException.class, () ->
//                restaurantService.getById(NON_EXISTENT_RESTAURANT.getId()));
//    }

    @Test
    void getAll() {
        assertEquals(RESTAURANT_LIST, restaurantService.getAll());
    }

    @Test
    void getAllWithMealsByDate() {
        PEPERONI.setMeals(PEPERONI_MEALS_TODAY);
        PERCHINI.setMeals(PERCHINI_MEALS_TODAY);
        List<Restaurant> list = restaurantService.getAllWithMealsByDate(LocalDate.now());
        assertEquals(List.of(PEPERONI, PERCHINI), list);
        assertEquals(PEPERONI.getMeals(), list.get(0).getMeals());
        assertEquals(PERCHINI.getMeals(), list.get(1).getMeals());
    }

    @Test
    void update() {
        PERCHINI.setAddress("New address");
        restaurantService.update(PERCHINI);
        assertEquals(PERCHINI, restaurantService.getById(PERCHINI.getId()));
    }

//    @Test
//    void updateNotFound() {
//        assertThrows(NotFoundException.class, () ->
//                restaurantService.update(NON_EXISTENT_RESTAURANT.getId(), NON_EXISTENT_RESTAURANT));
//    }

    //?
    @Test
    void deleteById() {
        restaurantService.deleteById(100007);
        assertEquals(RESTAURANT_LIST_WITHOUT_MINDAL, restaurantService.getAll());
    }

//    @Test
//    void deleteNotFoundById() {
//        assertThrows(NotFoundException.class, () ->
//                restaurantService.deleteById(NON_EXISTENT_RESTAURANT.getId()));
//
//    }
}