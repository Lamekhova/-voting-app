package com.example.sweater.web.meal;

import com.example.sweater.model.Meal;
import com.example.sweater.to.MealTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(AdminMealController.REST_URL)
public class AdminMealController extends AbstractMealController {

    static final String REST_URL = "/rest/admin/meals";

    @PostMapping(value = "/restaurant/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Meal addNew(@PathVariable Integer restaurantId,
                       @Valid @RequestBody MealTO mealTO) {
        return super.addNew(restaurantId, mealTO);
    }

    @GetMapping(value = "/restaurant/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Meal> getAllByRestaurantId(@PathVariable Integer restaurantId) {
        return super.getAllByRestaurantId(restaurantId);
    }

    @PutMapping(value = "/{mealId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable Integer mealId,
                       @RequestBody Meal meal) {
        super.update(mealId, meal);
    }

    @DeleteMapping(value = "/restaurant/{restaurantId}/meal/{mealId}")
    public void deleteById(@PathVariable Integer restaurantId,
                           @PathVariable Integer mealId) {
        super.deleteById(restaurantId, mealId);
    }
}
