package com.example.sweater.web.restaurant;

import com.example.sweater.model.Restaurant;
import com.example.sweater.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static com.example.sweater.util.ValidationUtil.assureIdConsistent;
import static com.example.sweater.util.ValidationUtil.checkNew;

public abstract class AbstractRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    public Restaurant addNew(Restaurant restaurant) {
        checkNew(restaurant);
        return restaurantService.addNew(restaurant);
    }

    /**
     * @param localDate is optional, if the parameter value is not passed, it is equal to the current date
     * @return Restaurants with available meals for requested date
     */

    public List<Restaurant> getAllWithMealsByDate(LocalDate localDate) {
        return restaurantService.getAllWithMealsByDate(localDate == null ? LocalDate.now() : localDate);
    }

    public Restaurant getById(Integer id) {
        return restaurantService.getById(id);
    }

    public List<Restaurant> getAll() {
        return restaurantService.getAll();
    }

    public void update(Integer id, Restaurant restaurant) {
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }

    public void deleteById(Integer id) {
        restaurantService.deleteById(id);
    }
}
