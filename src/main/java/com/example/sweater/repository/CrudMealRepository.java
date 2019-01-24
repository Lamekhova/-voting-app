package com.example.sweater.repository;

import com.example.sweater.model.Meal;
import com.example.sweater.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @EntityGraph(Meal.GRAPH_WITH_RESTAURANT)
    @Query("SELECT m FROM Meal m WHERE m.id=?1 AND m.restaurant=?2")
    Meal getById(Integer id, Restaurant restaurant);

    List<Meal> findMealsByRestaurantIdOrderByDateDesc(Integer restaurantId);

//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Meal m WHERE m.id=?1 AND m.restaurant.id=?2")
//    int deleteById(Integer mealId, Integer restaurantId);
}
