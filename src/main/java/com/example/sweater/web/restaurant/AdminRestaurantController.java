package com.example.sweater.web.restaurant;

import com.example.sweater.model.Restaurant;
import com.example.sweater.service.RestaurantService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.sweater.util.ExceptionUtil.assureIdConsistent;

@RestController
@RequestMapping(AdminRestaurantController.REST_URL)
@Api(description="Operations with restaurants that only an administrator can perform")
public class AdminRestaurantController {

    static final String REST_URL = "/rest/admin/restaurants";

    private final RestaurantService restaurantService;

    @Autowired
    public AdminRestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addNew(@RequestBody Restaurant restaurant) {
        restaurantService.addNew(restaurant);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll() {
        List<Restaurant> restaurantList = restaurantService.getAll();
        return ResponseEntity.ok(restaurantList);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable("id") Integer id,
                                 @RequestBody Restaurant restaurant) {
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteById(@PathVariable Integer id) {
        restaurantService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
