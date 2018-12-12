package com.example.sweater.web.user;

import com.example.sweater.model.User;
import com.example.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.sweater.util.ExceptionUtil.assureIdConsistent;
import static com.example.sweater.util.ExceptionUtil.checkNew;

@RestController
@RequestMapping(AdminUserController.REST_URL)
public class AdminUserController {

    static final String REST_URL = "/rest/admin/users";

    private final UserService userService;

    @Autowired
    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User addNew(@RequestBody User user) {
        checkNew(user);
        return userService.addNew(user);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @GetMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getByEmail(@RequestParam(name = "email") String email) {
        return userService.getByEmail(email);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return userService.getAll();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable("id") Integer id,
                       @RequestBody User user) {
        assureIdConsistent(user, id);
        userService.update(id, user);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
    }
}
