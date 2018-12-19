package com.example.sweater.web.user;

import com.example.sweater.model.User;
import com.example.sweater.security.UserPrincipal;
import com.example.sweater.service.UserService;
import com.example.sweater.to.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.sweater.util.UserUtil.checkMatchesPasswords;
import static com.example.sweater.util.UserUtil.getRolesFromAuthorities;

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
    public ResponseEntity get(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        User user = userService.getById(userPrincipal.getUserId());
        return ResponseEntity.ok(user);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                 @Valid @RequestBody UserTO userTO) {
        User user = createUserFromUserTO(userTO, userPrincipal);
        userService.update(user.getId(), user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    private User createUserFromUserTO(UserTO userTO, UserPrincipal userPrincipal) {
        String password = checkMatchesPasswords(userTO.getPassword(), userPrincipal.getPassword());
        User user = new User(
                userPrincipal.getUserId(),
                userTO.getName(),
                userTO.getEmail().toLowerCase(),
                password,
                getRolesFromAuthorities(userPrincipal.getAuthorities()));
        return user;
    }
}
