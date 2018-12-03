package com.example.sweater.web;

import com.example.sweater.model.Vote;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.example.sweater.UserTestData.JOHN;
import static com.example.sweater.VoteTestData.VOTE_11;
import static com.example.sweater.VoteTestData.VOTE_2;
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
        List<Vote> userVotes = voteController.getAllByUserId(JOHN.getId());
        assertEquals(3, userVotes.size());
    }
}