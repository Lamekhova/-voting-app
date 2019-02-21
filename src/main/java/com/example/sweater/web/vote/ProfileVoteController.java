package com.example.sweater.web.vote;

import com.example.sweater.security.UserPrincipal;
import com.example.sweater.service.VoteService;
import com.example.sweater.to.VoteTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProfileVoteController.REST_URL)
@Api(description="Operations with votes that only a user can perform in their own profile")
public class ProfileVoteController {

    public static final String REST_URL = "/rest/profile/votes";

    private final VoteService voteService;

    @Autowired
    public ProfileVoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addNew(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                 @RequestParam(value = "restaurantId") Integer restaurantId) {
        voteService.addNew(restaurantId, userPrincipal.getUser());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllByUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<VoteTO> voteTOList = voteService.getAllByUser(userPrincipal.getUser());
        return ResponseEntity.ok(voteTOList);
    }


}
