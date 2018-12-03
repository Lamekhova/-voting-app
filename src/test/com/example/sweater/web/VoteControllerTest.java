package com.example.sweater.web;

import com.example.sweater.model.Vote;
import com.example.sweater.to.VoteTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.example.sweater.UserTestData.SUE;
import static com.example.sweater.VoteTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VoteControllerTest {

    @Autowired
    protected VoteController voteController;

    @Autowired
    protected RestaurantController restaurantController;

    @Autowired
    protected UserController userController;

    @Test
    void addNew() {
        Vote vote = voteController.addNew(VOTE_11);
        assertEquals(vote, voteController.getById(vote.getId()));
    }

    @Test
    void getById() {
        assertEquals(VOTE_2, voteController.getById(VOTE_2.getId()));
    }

//    @Test
//    void getNotFoundById() {
//        assertThrows(NotFoundException.class, () ->
//                voteController.getById(NOT_EXISTENT_VOTE.getId()));
//    }

    @Test
    void getAllByUserId() {
        List<VoteTO> userVotes = voteController.getAllByUser(SUE);
        assertEquals(2, userVotes.size());
        assertEquals(VOTE_3.getRestaurant().getId(), userVotes.get(0).getRestaurantId());
        assertEquals(VOTE_8.getRestaurant().getId(), userVotes.get(1).getRestaurantId());

    }
}