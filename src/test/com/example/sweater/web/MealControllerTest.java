package com.example.sweater.web;

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
public class MealControllerTest {

    @Autowired
    protected MealController mealController;

    @Autowired
    protected RestaurantController restaurantController;

    @Test
    void addNew() {
        Meal meal = mealController.addNew(MINDAL.getId(), STEAK);
        assertEquals(STEAK, mealController.getById(MINDAL.getId(), meal.getId()));
    }

    @Test
    void getById() {
        assertEquals(BURGER, mealController.getById(PEPERONI.getId(), BURGER.getId()));
    }

//    @Test
//    void getNotFoundById() {
//        assertThrows(NotFoundException.class, () ->
//                mealController.getById(NON_EXISTENT_MEAL.getId()));
//    }

    @Test
    void getAllByRestaurantId() {
        assertEquals(ALL_PEPERONI_MEALS, mealController.getAllByRestaurantId(PEPERONI.getId()));
    }

    @Test
    void update() {
        EGG.setPrice(370.00);
        mealController.update(PEPERONI.getId(), EGG);
        assertEquals(EGG, mealController.getById(PEPERONI.getId(), EGG.getId()));
    }

//    @Test
//    void updateNotFound() {
//        assertThrows(NotFoundException.class, () ->
//                mealController.update(MINDAL.getId(), NON_EXISTENT_MEAL));
//    }

    @Test
    void deleteById() {
        mealController.deleteById(PEPERONI.getId(), EGGPLANT.getId());
        assertEquals(ALL_PEPERONI_MEALS_WITHOUT_EGGPLANT, mealController.getAllByRestaurantId(PEPERONI.getId()));
    }

//    @Test
//    void deleteNotFoundById() {
//        assertThrows(NotFoundException.class, () ->
//                mealController.deleteById(MINDAL.getId(), NON_EXISTENT_MEAL.getId()));
//    }
}