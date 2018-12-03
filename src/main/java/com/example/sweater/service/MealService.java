package com.example.sweater.service;

import com.example.sweater.model.Meal;
import com.example.sweater.repository.CrudMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class MealService {

    private final CrudMealRepository crudMealRepository;

    @Autowired
    public MealService(CrudMealRepository crudMealRepository) {
        this.crudMealRepository = crudMealRepository;
    }

    public Meal addNew(Integer restaurantId, Meal meal) {
        Assert.notNull(meal, "meal must not be null");
        return crudMealRepository.save(meal);
    }

    public Meal getById(Integer restaurantId, Integer mealId) {
        return crudMealRepository.findById(mealId).orElse(null);
    }
    public List<Meal> getAllByRestaurantId(Integer restaurantId) {
        return crudMealRepository.findMealsByRestaurantIdOrderByDateDesc(restaurantId);
    }

    public void update(Integer restaurantId, Meal meal) {
        Assert.notNull(meal, "meal must not be null");
        crudMealRepository.save(meal);
    }

    public void deleteById(Integer restaurantId, Integer id) {
        crudMealRepository.deleteById(id);
    }
}
