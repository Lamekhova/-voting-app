package com.example.sweater.web.vote;

import com.example.sweater.model.Vote;
import com.example.sweater.security.UserPrincipal;
import com.example.sweater.to.VoteTO;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProfileVoteController.REST_URL)
public class ProfileVoteController extends AbstractVoteController {

    static final String REST_URL = "rest/profile/votes/user";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote addNew(@AuthenticationPrincipal UserPrincipal userPrincipal,
                       @RequestParam(value = "restaurantId") Integer restaurantId) {
        return super.addNew(userPrincipal.getUser(), restaurantId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VoteTO> getAllByUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return super.getAllByUser(userPrincipal.getUser());
    }


}
