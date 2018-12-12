package com.example.sweater.service;

import com.example.sweater.model.Meal;
import com.example.sweater.repository.CrudMealRepository;
import com.example.sweater.repository.CrudRestaurantRepository;
import com.example.sweater.to.MealTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.example.sweater.util.ExceptionUtil.*;

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
        Meal meal = createMealFromMealTO(mealTO, restaurantId);
        checkNew(meal);
        return crudMealRepository.save(meal);
    }

    public Meal getById(Integer mealId, Integer restaurantId) {
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

    public void update(Integer mealId, Integer restaurantId, MealTO mealTO) {
        Meal meal = createMealFromMealTO(mealTO, restaurantId);
        meal.setId(mealId);
        checkNotFoundObjectWithId(crudMealRepository.getById(mealId), mealId);
        crudMealRepository.save(meal);
    }

    public void deleteById(Integer mealId, Integer restaurantId) {
        checkNotFoundWithId(crudMealRepository.deleteById(restaurantId, mealId) != 0, mealId);
    }

    private Meal createMealFromMealTO(MealTO mealTO, Integer restaurantId) {
        Meal meal = new Meal(
                mealTO.getId(),
                mealTO.getName(),
                mealTO.getPrice(),
                LocalDate.now(),
                restaurantService.getById(restaurantId)
        );
        return meal;
    }
}
