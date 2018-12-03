package com.example.sweater.web;

import com.example.sweater.model.Restaurant;
import com.example.sweater.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    //for admin
    @PostMapping(value = "/restaurants", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant addNew(@RequestBody Restaurant restaurant) {
        return restaurantService.addNew(restaurant);
    }

    //general
    @GetMapping(value = "/restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getById(@PathVariable Integer id) {
        return restaurantService.getById(id);
    }

    //for admin
    @GetMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return restaurantService.getAll();
    }

    /**
     *
     * @param localDate is optional, if the parameter value is not passed, it is equal to the current date
     * @return Restaurants with available meals for requested date
     */
    @GetMapping(value = "/restaurants/available", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllWithMealsByDate(@RequestParam(value = "date", required = false)
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate) {
        return restaurantService.getAllWithMealsByDate(localDate == null ? LocalDate.now() : localDate);
    }

    /**
     *
     * @param number is the amount of restaurants with the most votes. This parameter is optional,
     *               if the parameter value is not passed, it is equal to 10
     * @return amount of votes for each restaurant
     */
    @GetMapping(value = "/restaurants/top", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Restaurant, Integer> getTopWithRating(@RequestParam(value = "number", required = false) Integer number) {
        return restaurantService.getTopWithRating(number == null ? 10 : number);
    }

    //for admin
    @PutMapping(value = "/restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable("id") Integer id,
                       @RequestBody Restaurant restaurant) {
        restaurantService.update(restaurant);
    }

    //for admin
    @DeleteMapping(value = "/restaurants/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteById(@PathVariable Integer id) {
        restaurantService.deleteById(id);
    }


}
