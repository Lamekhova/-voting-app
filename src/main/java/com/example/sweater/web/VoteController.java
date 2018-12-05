package com.example.sweater.web;

import com.example.sweater.model.User;
import com.example.sweater.model.Vote;
import com.example.sweater.service.UserService;
import com.example.sweater.service.VoteService;
import com.example.sweater.to.VoteTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class VoteController {

    private final VoteService voteService;

    private final UserService userService;

    @Autowired
    public VoteController(VoteService voteService, UserService userService) {
        this.voteService = voteService;
        this.userService = userService;
    }

    @PostMapping(value = "/votes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote addNew(@RequestParam(value = "restaurantId") Integer restaurantId) {
        User user = null;
        return voteService.addNew(restaurantId, user);
    }

    //for tests
//    @GetMapping(value = "/votes/{voteId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Vote getById(@PathVariable Integer voteId) {
//        return voteService.getById(voteId);
//    }

    @GetMapping(value = "/votes/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VoteTO> getAllByUser(@PathVariable Integer userId) {
        return voteService.getAllByUser(userService.getById(userId));
    }

    @GetMapping(value = "/votes/result", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> getVoteResultByDate(@RequestParam(value = "date", required = false)
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate) {
        return voteService.getVoteResultByDate(localDate == null ? LocalDate.now() : localDate);
    }
}
