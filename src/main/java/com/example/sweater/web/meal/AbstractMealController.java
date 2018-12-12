package com.example.sweater.web.meal;

import com.example.sweater.model.Meal;
import com.example.sweater.service.MealService;
import com.example.sweater.to.MealTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.example.sweater.util.ExceptionUtil.assureIdConsistent;

public abstract class AbstractMealController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService mealService;

    public Meal addNew(Integer restaurantId, MealTO mealTO) {
        log.info("add new meal {} for restaurant with id {}", mealTO, restaurantId);
        return mealService.addNew(restaurantId, mealTO);
    }

    public Meal getById(Integer restaurantId, Integer mealId) {
        log.info("get meal with id {} owned by a restaurant with id {}", mealId, restaurantId);
        return mealService.getById(restaurantId, mealId);
    }

    public List<Meal> getAllByRestaurantId(Integer restaurantId) {
        log.info("get all meals owned by a restaurant with id {}", restaurantId);
        return mealService.getAllByRestaurantId(restaurantId);
    }

    public void update(Integer mealId, Meal meal) {
        log.info("update meal {} with id {}", meal, mealId);
        assureIdConsistent(meal, mealId);
        meal.setId(mealId);
        mealService.update(meal);
    }

    public void deleteById(Integer restaurantId, Integer mealId) {
        log.info("delete meal with id {} owned by a restaurant with id {}", mealId, restaurantId);
        mealService.deleteById(restaurantId, mealId);
    }
}
