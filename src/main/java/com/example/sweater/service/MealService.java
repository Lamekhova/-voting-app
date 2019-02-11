package com.example.sweater.service;

import com.example.sweater.model.Meal;
import com.example.sweater.model.Restaurant;
import com.example.sweater.repository.CrudMealRepository;
import com.example.sweater.repository.CrudRestaurantRepository;
import com.example.sweater.to.MealTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
        Restaurant restaurant = restaurantService.getById(restaurantId);
        Meal meal = crudMealRepository.getById(mealId, restaurant);
        return checkNotFoundObjectWithId(meal, mealId);
    }

    public List<Meal> getAllByRestaurantId(Integer restaurantId) {
        checkNotFoundObjectWithId(crudRestaurantRepository.findById(restaurantId).orElse(null), restaurantId);
        return crudMealRepository.findMealsByRestaurantIdOrderByDateDesc(restaurantId);
    }

    public void update(Integer mealId, Integer restaurantId, MealTO mealTO) {
        Meal meal = createMealFromMealTO(mealTO, restaurantId);
        meal.setId(mealId);
        checkNotFoundObjectWithId(getById(mealId, restaurantId), mealId);
        crudMealRepository.save(meal);
    }

    public void delete(Integer mealId, Integer restaurantId) {
        try {
            crudMealRepository.deleteById(mealId);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            checkNotFoundWithId(false, mealId);
        }
    }

    private Meal createMealFromMealTO(MealTO mealTO, Integer restaurantId) {
        return new Meal(
                mealTO.getId(),
                mealTO.getName(),
                mealTO.getPrice(),
                LocalDate.now(),
                restaurantService.getById(restaurantId)
        );
    }
}
