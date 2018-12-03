package com.example.sweater.web;

import com.example.sweater.model.Restaurant;
import com.example.sweater.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping(value = "/restaurants", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant addNew(@RequestBody Restaurant restaurant) {
        return restaurantService.addNew(restaurant);
    }

    @GetMapping(value = "/restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getById(@PathVariable Integer id) {
        return restaurantService.getById(id);
    }

    @GetMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return restaurantService.getAll();
    }

    /**
     * @param localDate is optional, if the parameter value is not passed, it is equal to the current date
     * @return Restaurants with available meals for requested date
     */
    @GetMapping(value = "/restaurants/available", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllWithMealsByDate(@RequestParam(value = "date", required = false)
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate) {
        return restaurantService.getAllWithMealsByDate(localDate == null ? LocalDate.now() : localDate);
    }

    @PutMapping(value = "/restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable("id") Integer id,
                       @RequestBody Restaurant restaurant) {
        restaurantService.update(restaurant);
    }

    @DeleteMapping(value = "/restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteById(@PathVariable Integer id) {
        restaurantService.deleteById(id);
    }
}
