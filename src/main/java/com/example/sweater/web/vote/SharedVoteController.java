package com.example.sweater.web.vote;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping(SharedVoteController.REST_URL)
public class SharedVoteController extends AbstractVoteController {

    static final String REST_URL = "rest/votes";

    @GetMapping(value = "/result", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> getVoteResultByDate(LocalDate localDate) {
        return super.getVoteResultByDate(localDate);
    }
}
