package com.example.sweater.web.user;

import com.example.sweater.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ProfileUserController.REST_URL)
public class ProfileUserController extends AbstractUserController {

    static final String REST_URL = "rest/profile/users/";

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getById(@PathVariable Integer id) {
        return super.getById(id);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable("id") Integer id,
                       @RequestBody User user) {
       super.update(id, user);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteById(@PathVariable Integer id) {
        super.deleteById(id);
    }
}
