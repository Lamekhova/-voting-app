package com.example.sweater.repository;

import com.example.sweater.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @EntityGraph(value = Restaurant.GRAPH_WITH_MEALS)
    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.meals m WHERE m.date = ?1 ORDER BY r.name")
    List<Restaurant> findAllWithMealsByDate(LocalDate date);

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=?1")
    int delete(Integer id);
}
