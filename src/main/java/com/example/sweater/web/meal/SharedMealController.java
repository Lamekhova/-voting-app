package com.example.sweater.web.meal;

import com.example.sweater.model.Meal;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SharedMealController.REST_URL)
public class SharedMealController extends AbstractMealController {

    static final String REST_URL = "/rest/meals/restaurant";

    @GetMapping(value = "/{restaurantId}/meal/{mealId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal getById(@PathVariable Integer restaurantId,
                        @PathVariable Integer mealId) {
        return super.getById(restaurantId, mealId);
    }
}
