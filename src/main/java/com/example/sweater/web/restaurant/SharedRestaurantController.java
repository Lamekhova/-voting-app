package com.example.sweater.web.restaurant;

import com.example.sweater.model.Restaurant;
import com.example.sweater.service.RestaurantService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(SharedRestaurantController.REST_URL)
@Api(description="Operations with restaurants that all authorized users can perform")
public class SharedRestaurantController {

    public static final String REST_URL = "/rest/restaurants";

    private final RestaurantService restaurantService;

    @Autowired
    public SharedRestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@PathVariable Integer id) {
        Restaurant restaurant = restaurantService.getById(id);
        return ResponseEntity.ok(restaurant);
    }

    /**
     * @param localDate is optional, if the parameter date is not passed, it is equal to the current date
     * @return Restaurants with available meals for requested date
     */

    @GetMapping(value = "/available", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllWithMealsByDate(@RequestParam(value = "date", required = false)
                                                @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate) {
        List<Restaurant> restaurantList = restaurantService.getAllWithMealsByDate(localDate == null ? LocalDate.now() : localDate);
        return ResponseEntity.ok(restaurantList);
    }


}
