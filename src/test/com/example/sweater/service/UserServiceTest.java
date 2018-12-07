package com.example.sweater.service;

import com.example.sweater.model.User;
import com.example.sweater.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.sweater.UserTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void addNew() {
        User user = userService.addNew(MARRY);
        MARRY.setId(user.getId());
        assertEquals(MARRY, user);
    }

    @Test
    void addNewWithDuplicateEmail() {
        assertThrows(DataAccessException.class, () ->
                userService.addNew(WITH_DUPLICATE_EMAIL));
    }

    @Test
    void getById() {
        assertEquals(IVAN, userService.getById(100000));
    }

    @Test
    void getNotFoundById() {
        assertThrows(NotFoundException.class, () ->
                userService.getById(NOT_EXISTENT_USER.getId()));
    }

    @Test
    void getByEmail() {
        assertEquals(SUE, userService.getByEmail("userTwo@mail.ru"));
    }

    @Test
    void getNotFoundByEmail() {
        assertThrows(NotFoundException.class, () ->
                userService.getByEmail("nonexistent@mail.com"));
    }

    @Test
    void getAll() {
        assertEquals(USER_LIST, userService.getAll());
    }

    @Test
    void update() {
        IVAN.setName("New Name");
        IVAN.setEmail("newemail@mail.ru");
        IVAN.setPassword("NewPassword");
        userService.update(IVAN.getId(), IVAN);
        assertEquals(IVAN, userService.getById(IVAN.getId()));
    }

    @Test
    void updateNotFound() {
        assertThrows(NotFoundException.class, () ->
                userService.update(NOT_EXISTENT_USER.getId(), NOT_EXISTENT_USER));
    }

    @Test
    void deleteById() {
        userService.deleteById(100000);
        assertEquals(USER_LIST_WITHOUT_IVAN, userService.getAll());
    }

    @Test
    void deleteNotFoundById() {
        assertThrows(NotFoundException.class, () ->
                userService.deleteById(NOT_EXISTENT_USER.getId()));

    }
}