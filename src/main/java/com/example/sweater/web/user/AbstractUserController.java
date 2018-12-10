package com.example.sweater.web.user;

import com.example.sweater.model.User;
import com.example.sweater.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.example.sweater.util.ValidationUtil.assureIdConsistent;
import static com.example.sweater.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    public User addNew(User user) {
        log.info("add new user {}", user);
        checkNew(user);
        return userService.addNew(user);
    }

    public User getById(Integer id) {
        log.info("get user with id {}", id);
        return userService.getById(id);
    }

    public User getByEmail(String email) {
        log.info("get user by email {}", email);
        return userService.getByEmail(email);
    }

    public List<User> getAll() {
        log.info("get all users");
        return userService.getAll();
    }

    public void update(Integer id, User user) {
        log.info("update user {} with id  {}", user, id);
        assureIdConsistent(user, id);
        userService.update(id, user);
    }

    public void deleteById(Integer id) {
        log.info("delete user with id  {}", id);
        userService.deleteById(id);
    }
}
