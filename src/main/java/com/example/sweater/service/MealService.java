package com.example.sweater.service;

import com.example.sweater.model.Meal;
import com.example.sweater.repository.CrudMealRepository;
import com.example.sweater.repository.CrudRestaurantRepository;
import com.example.sweater.to.MealTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.example.sweater.util.ExceptionUtil.checkNew;
import static com.example.sweater.util.ExceptionUtil.checkNotFoundObjectWithId;
import static com.example.sweater.util.ExceptionUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final CrudMealRepository crudMealRepository;

    private final CrudRestaurantRepository crudRestaurantRepository;

    private final RestaurantService restaurantService;

    @Autowired
    public MealService(CrudMealRepository crudMealRepository, CrudRestaurantRepository crudRestaurantRepository, RestaurantService restaurantService) {
        this.crudMealRepository = crudMealRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.restaurantService = restaurantService;
    }

    public Meal addNew(Integer restaurantId, MealTO mealTO) {
        Meal meal = new Meal(
                mealTO.getId(),
                mealTO.getName(),
                mealTO.getPrice(),
                LocalDate.now(),
                restaurantService.getById(restaurantId)
        );
        checkNew(meal);
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
