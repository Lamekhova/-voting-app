package com.example.sweater;

import com.example.sweater.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

import static com.example.sweater.RestaurantTestData.MINDAL;
import static com.example.sweater.RestaurantTestData.PEPERONI;
import static com.example.sweater.RestaurantTestData.PERCHINI;
import static com.example.sweater.UserTestData.*;

public class VoteTestData {

    public static final Vote VOTE_1 = new Vote(100020, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 30)), IVAN, PEPERONI);
    public static final Vote VOTE_2 = new Vote(100021, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 00)), NICOLAS, PEPERONI);
    public static final Vote VOTE_3 = new Vote(100022, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 00)), SUE, PERCHINI);
    public static final Vote VOTE_4 = new Vote(100023, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 30)), PERRY, PERCHINI);
    public static final Vote VOTE_5 = new Vote(100024, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 30)), JOHN, PERCHINI);

    public static final Vote VOTE_6 = new Vote(100025, LocalDateTime.of(LocalDate.now().minus(Period.ofDays(1)), LocalTime.of(8, 30)), IVAN, MINDAL);
    public static final Vote VOTE_7 = new Vote(100026, LocalDateTime.of(LocalDate.now().minus(Period.ofDays(1)), LocalTime.of(10, 10)), NICOLAS, MINDAL);
    public static final Vote VOTE_8 = new Vote(100027, LocalDateTime.of(LocalDate.now().minus(Period.ofDays(1)), LocalTime.of(9, 00)), SUE, PERCHINI);
    public static final Vote VOTE_9 = new Vote(100028, LocalDateTime.of(LocalDate.now().minus(Period.ofDays(1)), LocalTime.of(10, 00)), PERRY, PEPERONI);
    public static final Vote VOTE_10 = new Vote(100029, LocalDateTime.of(LocalDate.now().minus(Period.ofDays(1)), LocalTime.of(8, 40)), JOHN, PEPERONI);

    public static final Vote VOTE_11 = new Vote(LocalDateTime.of(LocalDate.now().minus(Period.ofDays(2)), LocalTime.of(15, 55)), JOHN, PEPERONI);

    public static final Vote NOT_EXISTENT_VOTE = new Vote(1, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 40)), JOHN, PEPERONI);


}
