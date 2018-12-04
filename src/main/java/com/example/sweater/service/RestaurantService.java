package com.example.sweater.service;

import com.example.sweater.model.Restaurant;
import com.example.sweater.repository.CrudRestaurantRepository;
import com.example.sweater.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.example.sweater.util.ValidationUtil.checkNotFoundObjectWithId;
import static com.example.sweater.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");

    private final CrudRestaurantRepository crudRestaurantRepository;

    @Autowired
    public RestaurantService(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    public Restaurant addNew(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return crudRestaurantRepository.save(restaurant);
    }

    public Restaurant getById(Integer id) throws NotFoundException {
        return checkNotFoundObjectWithId(crudRestaurantRepository.findById(id).orElse(null), id);
    }

    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll(SORT_NAME);
    }

    public List<Restaurant> getAllWithMealsByDate(LocalDate localDate) {
        Assert.notNull(localDate, "local date must not be null");
        return crudRestaurantRepository.findAllWithMealsByDate(localDate);
    }

    public void update(Restaurant restaurant) throws NotFoundException {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundObjectWithId(getById(restaurant.getId()), restaurant.getId());
        crudRestaurantRepository.save(restaurant);
    }

    public void deleteById(Integer id) throws NotFoundException {
        checkNotFoundWithId(crudRestaurantRepository.delete(id) != 0, id);
    }
}
