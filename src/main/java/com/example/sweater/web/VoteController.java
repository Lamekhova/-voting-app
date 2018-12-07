package com.example.sweater.web;

import com.example.sweater.security.UserPrincipal;
import com.example.sweater.model.Vote;
import com.example.sweater.service.VoteService;
import com.example.sweater.to.VoteTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class VoteController {

    private final VoteService voteService;


    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping(value = "rest/profile/votes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote addNew(@AuthenticationPrincipal UserPrincipal userPrincipal,
                       @RequestParam(value = "restaurantId") Integer restaurantId) {
        return voteService.addNew(restaurantId, userPrincipal.getUser());
    }

    @GetMapping(value = "rest/profile/votes/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VoteTO> getAllByUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return voteService.getAllByUser(userPrincipal.getUser());
    }

    @GetMapping(value = "rest/votes/result", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> getVoteResultByDate(@RequestParam(value = "date", required = false)
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate) {
        return voteService.getVoteResultByDate(localDate == null ? LocalDate.now() : localDate);
    }
}
