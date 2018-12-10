package com.example.sweater.web.meal;

import com.example.sweater.model.Meal;
import com.example.sweater.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.example.sweater.util.ValidationUtil.assureIdConsistent;
import static com.example.sweater.util.ValidationUtil.checkNew;

public abstract class AbstractMealController {

    @Autowired
    private MealService mealService;

    public Meal addNew(Integer restaurantId, Meal meal) {
        checkNew(meal);
        return mealService.addNew(restaurantId, meal);
    }

    public Meal getById(Integer restaurantId, Integer mealId) {
        return mealService.getById(restaurantId, mealId);
    }

    public List<Meal> getAllByRestaurantId(Integer restaurantId) {
        return mealService.getAllByRestaurantId(restaurantId);
    }

    public void update(Integer mealId, Meal meal) {
        assureIdConsistent(meal, mealId);
        meal.setId(mealId);
        mealService.update(meal);
    }

    public void deleteById(Integer restaurantId, Integer id) {
        mealService.deleteById(restaurantId, id);
    }
}
