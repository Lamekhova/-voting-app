package com.example.sweater.web.user;

import com.example.sweater.model.Role;
import com.example.sweater.model.User;
import com.example.sweater.security.UserPrincipal;
import com.example.sweater.service.UserService;
import com.example.sweater.to.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

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
        User user = new User(
                userPrincipal.getUserId(),
                userTO.getName(),
                userTO.getEmail(),
                userTO.getPassword(),
                getRolesFromAuthorities(userPrincipal.getAuthorities()));
        userService.update(user.getId(), user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    private static Set<Role> getRolesFromAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(gr -> Role.valueOf(gr.getAuthority()))
                .collect(Collectors.toSet());
    }

}
