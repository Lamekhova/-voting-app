package com.example.sweater.service;

import com.example.sweater.model.Restaurant;
import com.example.sweater.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.example.sweater.MealTestData.PEPERONI_MEALS_TODAY;
import static com.example.sweater.MealTestData.PERCHINI_MEALS_TODAY;
import static com.example.sweater.RestaurantTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts="classpath:/data.sql")
public class RestaurantServiceTest {

    @Autowired
    private RestaurantService restaurantService;


    @Test
    public void addNew() {
        Restaurant restaurant = restaurantService.addNew(WOOD);
        assertNotNull(restaurant);
        assertEquals(restaurant, restaurantService.getById(restaurant.getId()));
    }

    @Test
    public void addNewWithDuplicateName() {
        assertThrows(DataAccessException.class, () ->
                restaurantService.addNew(WITH_DUPLICATE_NAME));
    }

    @Test
    public void getById() {
        assertEquals(PEPERONI, restaurantService.getById(100005));
    }

    @Test
    public void getNotFoundById() {
        assertThrows(NotFoundException.class, () ->
                restaurantService.getById(NOT_EXISTENT_RESTAURANT.getId()));
    }

    @Test
    public void getAll() {
        assertEquals(RESTAURANT_LIST, restaurantService.getAll());
    }

    @Test
    public void getAllWithMealsByDate() {
        PEPERONI.setMeals(PEPERONI_MEALS_TODAY);
        PERCHINI.setMeals(PERCHINI_MEALS_TODAY);
        List<Restaurant> list = restaurantService.getAllWithMealsByDate(LocalDate.now());
        assertEquals(Arrays.asList(PEPERONI, PERCHINI), list);
        System.out.println(list.get(0).getName() + "\t" + list.get(1).getName());
        assertEquals(PEPERONI.getMeals(), list.get(0).getMeals());
        assertEquals(PERCHINI.getMeals(), list.get(1).getMeals());
    }

    @Test
    public void update() {
        PERCHINI.setAddress("New address");
        restaurantService.update(PERCHINI);
        assertEquals(PERCHINI, restaurantService.getById(PERCHINI.getId()));
    }

    @Test
    public void updateNotFound() {
        assertThrows(NotFoundException.class, () ->
                restaurantService.update(NOT_EXISTENT_RESTAURANT));
    }

    @Test
    public void deleteById() {
        restaurantService.deleteById(100007);
        assertEquals(RESTAURANT_LIST_WITHOUT_MINDAL, restaurantService.getAll());
    }

    @Test
    public void deleteNotFoundById() {
        assertThrows(NotFoundException.class, () ->
                restaurantService.deleteById(NOT_EXISTENT_RESTAURANT.getId()));

    }
}