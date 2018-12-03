package com.example.sweater.service;

import com.example.sweater.model.Vote;
import com.example.sweater.to.VoteTO;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.example.sweater.UserTestData.SUE;
import static com.example.sweater.VoteTestData.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class VoteServiceTest {

    @Autowired
    private VoteService voteService;

    @Test
    void addNew() {
        Vote vote = voteService.addNew(VOTE_11);
        assertEquals(vote, voteService.getById(vote.getId()));
    }

    @Test
    void getById() {
        assertEquals(VOTE_2, voteService.getById(VOTE_2.getId()));
    }

//    @Test
//    void getNotFoundById() {
//        assertThrows(NotFoundException.class, () ->
//                voteService.getById(NOT_EXISTENT_VOTE.getId()));
//    }

    @Test
    void getAllByUserId() {
        List<VoteTO> userVotes = voteService.getAllByUser(SUE);
        assertEquals(2, userVotes.size());
        assertEquals(VOTE_3.getRestaurant().getId(), userVotes.get(0).getRestaurantId());
        assertEquals(VOTE_8.getRestaurant().getId(), userVotes.get(1).getRestaurantId());
    }

    @Test
    void getVoteResultByDate() {
        Map<String, Integer> map = voteService.getVoteResultByDate(LocalDate.now());
        assertEquals(2, map.size());
        assertThat(map.keySet(), Matchers.contains("Perchini", "Peperoni"));
        assertThat(map, IsMapContaining.hasEntry("Perchini", 3));
        assertThat(map, IsMapContaining.hasEntry("Peperoni", 2));
    }


}