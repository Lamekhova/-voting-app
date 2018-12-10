package com.example.sweater.web.restaurant;

import com.example.sweater.model.Restaurant;
import com.example.sweater.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static com.example.sweater.util.ValidationUtil.assureIdConsistent;
import static com.example.sweater.util.ValidationUtil.checkNew;

public abstract class AbstractRestaurantController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    public Restaurant addNew(Restaurant restaurant) {
        log.info("add new restaurant {}", restaurant);
        checkNew(restaurant);
        return restaurantService.addNew(restaurant);
    }

    /**
     * @param localDate is optional, if the parameter value is not passed, it is equal to the current date
     * @return Restaurants with available meals for requested date
     */

    public List<Restaurant> getAllWithMealsByDate(LocalDate localDate) {
        log.info("get all restaurants with meals on the required date {} (or today)", localDate);
        return restaurantService.getAllWithMealsByDate(localDate == null ? LocalDate.now() : localDate);
    }

    public Restaurant getById(Integer id) {
        log.info("get restaurant with id {}", id);
        return restaurantService.getById(id);
    }

    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return restaurantService.getAll();
    }

    public void update(Integer id, Restaurant restaurant) {
        log.info("update restaurant {} with id {}", restaurant, id);
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }

    public void deleteById(Integer id) {
        log.info("delete restaurant with id {}", id);
        restaurantService.deleteById(id);
    }
}
