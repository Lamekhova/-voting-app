package com.example.sweater.service;

import com.example.sweater.model.Meal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.sweater.MealTestData.*;
import static com.example.sweater.RestaurantTestData.MINDAL;
import static com.example.sweater.RestaurantTestData.PEPERONI;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

//    @Test
//    void getNotFoundById() {
//        assertThrows(NotFoundException.class, () ->
//                mealService.getById(NON_EXISTENT_MEAL.getId()));
//    }

    @Test
    void getAllByRestaurantId() {
        assertEquals(ALL_PEPERONI_MEALS, mealService.getAllByRestaurantId(PEPERONI.getId()));
    }

    @Test
    void update() {
        EGG.setPrice(370.00);
        mealService.update(PEPERONI.getId(), EGG);
        assertEquals(EGG, mealService.getById(PEPERONI.getId(), EGG.getId()));
    }

//    @Test
//    void updateNotFound() {
//        assertThrows(NotFoundException.class, () ->
//                mealService.update(MINDAL.getId(), NON_EXISTENT_MEAL));
//    }

    @Test
    void deleteById() {
        mealService.deleteById(PEPERONI.getId(), EGGPLANT.getId());
        assertEquals(ALL_PEPERONI_MEALS_WITHOUT_EGGPLANT, mealService.getAllByRestaurantId(PEPERONI.getId()));
    }

//    @Test
//    void deleteNotFoundById() {
//        assertThrows(NotFoundException.class, () ->
//                mealService.deleteById(MINDAL.getId(), NON_EXISTENT_MEAL.getId()));
//    }
}