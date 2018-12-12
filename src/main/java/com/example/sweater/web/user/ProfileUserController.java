package com.example.sweater.web.user;

import com.example.sweater.model.User;
import com.example.sweater.security.UserPrincipal;
import com.example.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ProfileUserController.REST_URL)
public class ProfileUserController {

    static final String REST_URL = "/rest/profile";

    private final UserService userService;

    @Autowired
    public ProfileUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return userService.getById(userPrincipal.getUserId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@AuthenticationPrincipal UserPrincipal userPrincipal,
                       @RequestBody User user) {
        userService.update(userPrincipal.getUserId(), user);
    }
}
