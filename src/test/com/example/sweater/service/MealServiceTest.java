package com.example.sweater.service;

import com.example.sweater.model.Meal;
import com.example.sweater.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.sweater.MealTestData.*;
import static com.example.sweater.RestaurantTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MealServiceTest {

    @Autowired
    private MealService mealService;

    @Test
    void addNew() {
        Meal meal = mealService.addNew(MINDAL.getId(), STEAK);
        assertEquals(STEAK, mealService.getById(MINDAL.getId(), meal.getId()));
    }

    @Test
    void getById() {
        assertEquals(BURGER, mealService.getById(PEPERONI.getId(), BURGER.getId()));
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
        EGG.setPrice(370.00);
        mealService.update(EGG);
        assertEquals(EGG, mealService.getById(PEPERONI.getId(), EGG.getId()));
    }

    @Test
    void updateNotFound() {
        assertThrows(NotFoundException.class, () ->
                mealService.update(NOT_EXISTENT_MEAL));
    }

    @Test
    void deleteById() {
        mealService.deleteById(PEPERONI.getId(), EGGPLANT.getId());
        assertEquals(ALL_PEPERONI_MEALS_WITHOUT_EGGPLANT, mealService.getAllByRestaurantId(PEPERONI.getId()));
    }

    @Test
    void deleteNotFoundById() {
        assertThrows(NotFoundException.class, () ->
                mealService.deleteById(MINDAL.getId(), NOT_EXISTENT_MEAL.getId()));
    }
}