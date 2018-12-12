package com.example.sweater.service;

import com.example.sweater.model.Vote;
import com.example.sweater.to.VoteTO;
import com.example.sweater.util.exception.LateToVote;
import com.example.sweater.util.exception.NotFoundException;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static com.example.sweater.RestaurantTestData.PEPERONI;
import static com.example.sweater.UserTestData.JOHN;
import static com.example.sweater.UserTestData.SUE;
import static com.example.sweater.VoteTestData.*;
import static com.example.sweater.util.TimeUtil.setVoteFinishTime;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class VoteServiceTest {

    @Autowired
    private VoteService voteService;

    @Test
    void addNewJustInTime() {
        setVoteFinishTime(LocalTime.now().plusMinutes(1));
        Vote vote = voteService.addNew(PEPERONI.getId(), JOHN);
        assertNotNull(vote);
        assertEquals(vote, voteService.getById(vote.getId()));
    }

    @Test
    void addNewLate() {
        setVoteFinishTime(LocalTime.now().minusMinutes(1));
        assertThrows(LateToVote.class, () ->
                        voteService.addNew(PEPERONI.getId(), JOHN));
    }

    @Test
    void getById() {
        Vote vote = voteService.getById(VOTE_4.getId());
        assertEquals(VOTE_4.getUser(), vote.getUser());
        assertEquals(VOTE_4.getRestaurant(), vote.getRestaurant());
    }

    @Test
    void getNotFoundById() {
        assertThrows(NotFoundException.class, () ->
                voteService.getById(NOT_EXISTENT_VOTE.getId()));
    }

    @Test
    void getAllByUser() {
        List<VoteTO> userVotes = voteService.getAllByUser(SUE);
        assertEquals(2, userVotes.size());
        assertEquals(VOTE_3.getRestaurant().getId(), userVotes.get(0).getRestaurantId());
        assertEquals(VOTE_8.getRestaurant().getId(), userVotes.get(1).getRestaurantId());
    }

    @Test
    void getVoteResultByDate() {
        Map<String, Integer> map = voteService.getVoteResultByDate(LocalDate.now().minusDays(1));
        assertEquals(3, map.size());
        assertThat(map.keySet(), Matchers.contains("Peperoni", "Mindal", "Perchini"));
        assertThat(map, IsMapContaining.hasEntry("Mindal", 2));
        assertThat(map, IsMapContaining.hasEntry("Peperoni", 2));
        assertThat(map, IsMapContaining.hasEntry("Perchini", 1));
    }
}