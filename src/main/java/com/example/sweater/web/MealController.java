package com.example.sweater.web;

import com.example.sweater.model.Meal;
import com.example.sweater.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MealController {

    private final MealService mealService;

    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping(value = "rest/admin/meals/restaurant/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Meal addNew(@PathVariable Integer restaurantId,
                       @RequestBody Meal meal) {
        return mealService.addNew(restaurantId, meal);
    }

    @GetMapping(value = "rest/meals/restaurant/{restaurantId}/meal/{mealId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal getById(@PathVariable Integer restaurantId,
                        @PathVariable Integer mealId) {
        return mealService.getById(restaurantId, mealId);
    }

    @GetMapping(value = "rest/admin/meals/restaurant/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Meal> getAllByRestaurantId(@PathVariable Integer restaurantId) {
        return mealService.getAllByRestaurantId(restaurantId);
    }

    @PutMapping(value = "rest/admin/meals/{mealId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable Integer mealId,
                       @RequestBody Meal meal) {
        meal.setId(mealId);
        mealService.update(meal);
    }

    @DeleteMapping(value = "rest/admin/meals/restaurant/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteById(@PathVariable Integer restaurantId,
                           @PathVariable Integer id) {
        mealService.deleteById(restaurantId, id);
    }
}
