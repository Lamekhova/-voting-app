package com.example.sweater.web.meal;

import com.example.sweater.model.Meal;
import com.example.sweater.service.MealService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SharedMealController.REST_URL)
@Api(description="Operations with meals that all authorized users can perform")
public class SharedMealController {

    public static final String REST_URL = "/rest/meals";

    private final MealService mealService;

    @Autowired
    public SharedMealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping(value = "/{mealId}/restaurant/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@PathVariable Integer mealId,
                                  @PathVariable Integer restaurantId) {
        Meal meal = mealService.getById(mealId, restaurantId);
        return ResponseEntity.ok(meal);
    }
}
