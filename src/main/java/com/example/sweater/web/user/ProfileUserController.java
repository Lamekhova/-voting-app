package com.example.sweater.web.user;

import com.example.sweater.model.User;
import com.example.sweater.security.UserPrincipal;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ProfileUserController.REST_URL)
public class ProfileUserController extends AbstractUserController {

    static final String REST_URL = "/rest/profile";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return super.getById(userPrincipal.getUserId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@AuthenticationPrincipal UserPrincipal userPrincipal,
                       @RequestBody User user) {
        super.update(userPrincipal.getUserId(), user);
    }

    @DeleteMapping
    public void delete(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        super.deleteById(userPrincipal.getUserId());
    }
}
