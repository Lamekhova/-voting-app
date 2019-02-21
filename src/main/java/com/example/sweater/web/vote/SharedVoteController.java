package com.example.sweater.web.vote;

import com.example.sweater.service.VoteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping(SharedVoteController.REST_URL)
@Api(description="Operations with votes that all authorized users can perform")
public class SharedVoteController {

    public static final String REST_URL = "/rest/votes";

    private final VoteService voteService;

    @Autowired
    public SharedVoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    /**
     * @param localDate is optional, if the parameter value is not passed, it is equal to the current date
     * @return Restaurant name on the number of votes
     */

    @GetMapping(value = "/result", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getVoteResultByDate(@RequestParam(value = "date", required = false)
                                              @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate) {
        Map<String, Integer> ratedRestaurants = voteService.getVoteResultByDate(localDate == null ? LocalDate.now() : localDate);
        return ResponseEntity.ok(ratedRestaurants);
    }
}
