package com.example.sweater.web.user;

import com.example.sweater.model.User;
import com.example.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity addNew(@RequestBody User user) {
        checkNew(user);
        userService.addNew(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@PathVariable Integer id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getByEmail(@RequestParam(name = "email") String email) {
        User user = userService.getByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll() {
        List<User> userList = userService.getAll();
        return ResponseEntity.ok(userList);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable("id") Integer id,
                                 @RequestBody User user) {
        assureIdConsistent(user, id);
        userService.update(id, user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
