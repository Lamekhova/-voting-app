package com.example.sweater.service;

import com.example.sweater.model.Meal;
import com.example.sweater.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.sweater.MealTestData.*;
import static com.example.sweater.RestaurantTestData.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts="classpath:/data.sql")
public class MealServiceTest {

    @Autowired
    private MealService mealService;

    @Test
    void addNew() {
        Meal meal = mealService.addNew(MINDAL.getId(), STEAK);
        assertNotNull(meal);
        assertEquals(STEAK.getName(), meal.getName());
        assertEquals(STEAK.getPrice(), meal.getPrice());
        assertEquals(MINDAL.getName(), meal.getRestaurant().getName());
    }

    @Test
    void getById() {
        assertEquals(BURGER, mealService.getById(BURGER.getId(), PEPERONI.getId()));
    }

    @Test
    void getNotFoundById() {
        assertThrows(NotFoundException.class, () ->
                mealService.getById(MINDAL.getId(), NOT_EXISTENT_MEAL.getId()));
    }

    @Test
    void getAllByRestaurantId() {
        assertEquals(ALL_PEPERONI_MEALS, mealService.getAllByRestaurantId(PEPERONI.getId()));
    }

    @Test
    void getAllByNotFoundRestaurantId() {
        assertThrows(NotFoundException.class, () ->
                mealService.getAllByRestaurantId(NOT_EXISTENT_RESTAURANT.getId()));
    }

    @Test
    void update() {
        mealService.update(EGG.getId(), PEPERONI.getId(), EGGTO);
        Meal meal = mealService.getById(EGG.getId(), PEPERONI.getId());
        assertNotNull(meal);
        assertEquals(EGG.getId(), meal.getId());
        assertEquals(EGGTO.getName(), meal.getName());
        assertEquals(EGGTO.getPrice(), meal.getPrice());
        assertEquals(PEPERONI, meal.getRestaurant());
    }

    @Test
    void updateNotFound() {
        assertThrows(NotFoundException.class, () ->
                mealService.update(1, PEPERONI.getId(), NOT_EXISTENT_MEAL_TO));
    }

    @Test
    void deleteById() {
        mealService.delete(EGGPLANT.getId(), PEPERONI.getId());
        assertEquals(ALL_PEPERONI_MEALS_WITHOUT_EGGPLANT, mealService.getAllByRestaurantId(PEPERONI.getId()));
    }

    @Test
    void deleteNotFoundById() {
        assertThrows(NotFoundException.class, () ->
                mealService.delete(MINDAL.getId(), NOT_EXISTENT_MEAL.getId()));
    }
}