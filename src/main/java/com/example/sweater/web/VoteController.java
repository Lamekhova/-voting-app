package com.example.sweater.web;

import com.example.sweater.model.Vote;
import com.example.sweater.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VoteController {

    private final VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    //for user
    @PostMapping(value = "/votes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote addNew(@RequestBody Vote vote) {
        return voteService.addNew(vote);
    }

    //for tests
    @GetMapping(value = "/votes/{voteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote getById(@PathVariable Integer voteId) {
        return voteService.getById(voteId);
    }

    //for user
    @GetMapping(value = "/votes/user/{userId}/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAllByUserId(@PathVariable Integer userId) {
        return voteService.getAllByUserId(userId);
    }
}
