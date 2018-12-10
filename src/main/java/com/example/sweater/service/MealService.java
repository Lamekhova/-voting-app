package com.example.sweater.service;

import com.example.sweater.model.Meal;
import com.example.sweater.repository.CrudMealRepository;
import com.example.sweater.repository.CrudRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

import static com.example.sweater.util.ValidationUtil.checkNotFoundObjectWithId;
import static com.example.sweater.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final CrudMealRepository crudMealRepository;

    private final CrudRestaurantRepository crudRestaurantRepository;

    @Autowired
    public MealService(CrudMealRepository crudMealRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMealRepository = crudMealRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    public Meal addNew(Integer restaurantId, Meal meal) {
        Assert.notNull(meal, "meal must not be null");
        if (!meal.isNew() && getById(restaurantId, meal.getId()) == null) {
            return null;
        }
        meal.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMealRepository.save(meal);
    }

    public Meal getById(Integer restaurantId, Integer mealId) {
        Meal meal = crudMealRepository.getById(mealId);
        if (!(meal != null && meal.getRestaurant() != null
                && Objects.equals(meal.getRestaurant().getId(), restaurantId))) {
            meal = null;
        }
        return checkNotFoundObjectWithId(meal, mealId);
    }

    public List<Meal> getAllByRestaurantId(Integer restaurantId) {
        checkNotFoundObjectWithId(crudRestaurantRepository.findById(restaurantId).orElse(null), restaurantId);
        return crudMealRepository.findMealsByRestaurantIdOrderByDateDesc(restaurantId);
    }

    public void update(Meal meal) {
        Assert.notNull(meal, "meal must not be null");
        checkNotFoundObjectWithId(crudMealRepository.getById(meal.getId()), meal.getId());
        crudMealRepository.save(meal);
    }

    public void deleteById(Integer restaurantId, Integer mealId) {
        checkNotFoundWithId(crudMealRepository.deleteById(restaurantId, mealId) != 0, mealId);
    }
}
