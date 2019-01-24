package com.example.sweater.web.meal;

import com.example.sweater.model.Meal;
import com.example.sweater.service.MealService;
import com.example.sweater.to.MealTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(AdminMealController.REST_URL)
public class AdminMealController {

    static final String REST_URL = "/rest/admin/meals";

    private final MealService mealService;

    @Autowired
    public AdminMealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping(value = "/restaurant/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addNew(@PathVariable Integer restaurantId,
                                 @Valid @RequestBody MealTO mealTO) {
        mealService.addNew(restaurantId, mealTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping(value = "/restaurant/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllByRestaurantId(@PathVariable Integer restaurantId) {
        List<Meal> mealList = mealService.getAllByRestaurantId(restaurantId);
        return ResponseEntity.ok(mealList);
    }

    @PutMapping(value = "{mealId}/restaurant/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable Integer mealId,
                                 @PathVariable Integer restaurantId,
                                 @Valid @RequestBody MealTO mealTO) {
        mealService.update(mealId, restaurantId, mealTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "{mealId}/restaurant/{restaurantId}")
    public ResponseEntity deleteById(@PathVariable Integer mealId,
                                     @PathVariable Integer restaurantId) {
        mealService.delete(mealId, restaurantId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
