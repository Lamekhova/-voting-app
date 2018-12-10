package com.example.sweater.web.vote;

import com.example.sweater.model.User;
import com.example.sweater.model.Vote;
import com.example.sweater.service.VoteService;
import com.example.sweater.to.VoteTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public abstract class AbstractVoteController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService voteService;

    public Vote addNew(User user, Integer restaurantId) {
        log.info("add new vote for the restaurant with id {} from user {}", restaurantId, user);
        return voteService.addNew(restaurantId, user);
    }

    public List<VoteTO> getAllByUser(User user) {
        log.info("get all votes of user {}", user);
        return voteService.getAllByUser(user);
    }

    /**
     * @param localDate is optional, if the parameter value is not passed, it is equal to the current date
     * @return Restaurant name on the number of votes
     */

    public Map<String, Integer> getVoteResultByDate(LocalDate localDate) {
        log.info("get voting result on the required date {} (or today)", localDate);
        return voteService.getVoteResultByDate(localDate == null ? LocalDate.now() : localDate);
    }
}
