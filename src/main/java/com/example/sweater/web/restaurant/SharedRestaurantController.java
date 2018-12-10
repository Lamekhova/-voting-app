package com.example.sweater.web.restaurant;

import com.example.sweater.model.Restaurant;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(SharedRestaurantController.REST_URL)
public class SharedRestaurantController extends AbstractRestaurantController{

    static final String REST_URL = "rest/restaurants";

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getById(@PathVariable Integer id) {
        return super.getById(id);
    }

    @GetMapping(value = "/available", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllWithMealsByDate(@RequestParam(value = "date", required = false)
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate) {
        return super.getAllWithMealsByDate(localDate);
    }


}
