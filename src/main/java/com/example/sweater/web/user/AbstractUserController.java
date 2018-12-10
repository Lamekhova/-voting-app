package com.example.sweater.web.user;

import com.example.sweater.model.User;
import com.example.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.example.sweater.util.ValidationUtil.assureIdConsistent;
import static com.example.sweater.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {

    @Autowired
    private UserService userService;

    public User addNew(User user) {
        checkNew(user);
        return userService.addNew(user);
    }

    public User getById(Integer id) {
        return userService.getById(id);
    }

    public User getByEmail(String email) {
        return userService.getByEmail(email);
    }

    public List<User> getAll() {
        return userService.getAll();
    }

    public void update(Integer id, User user) {
        assureIdConsistent(user, id);
        userService.update(id, user);
    }

    public void deleteById(Integer id) {
        userService.deleteById(id);
    }
}
