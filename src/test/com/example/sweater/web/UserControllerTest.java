package com.example.sweater.web;

import com.example.sweater.model.User;
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
public class UserControllerTest {

    @Autowired
    protected UserController userController;

    @Autowired
    protected RestaurantController restaurantController;

    @Autowired
    protected VoteController voteController;

    @Test
    void addNew() {
        User user = userController.addNew(MARRY);
        assertEquals(user, userController.getById(user.getId()));
    }

    @Test
    void addNewWithDuplicateEmail() {
        assertThrows(DataAccessException.class, () ->
                userController.addNew(WITH_DUPLICATE_EMAIL));
    }

    @Test
    void getById() {
        assertEquals(IVAN, userController.getById(100000));
    }

//    @Test
//    void getNotFoundById() {
//        assertThrows(NotFoundException.class, () ->
//                userController.getById(NON_EXISTENT_USER.getId()));
//    }

    @Test
    void getByEmail() {
        assertEquals(SUE, userController.getByEmail("userTwo@mail.ru"));
    }

//    @Test
//    void getNotFoundByEmail() {
//        assertThrows(NotFoundException.class, () ->
//                userController.getByEmail("nonexistent@mail.com"));
//    }

    @Test
    void getAll() {
        assertEquals(USER_LIST, userController.getAll());
    }

    @Test
    void update() {
        IVAN.setName("New Name");
        IVAN.setEmail("newemail@mail.ru");
        IVAN.setPassword("NewPassword");
        userController.update(IVAN.getId(), IVAN);
        assertEquals(IVAN, userController.getById(IVAN.getId()));
    }

//    @Test
//    void updateNotFound() {
//        assertThrows(NotFoundException.class, () ->
//                userController.update(NON_EXISTENT_USER.getId(), NON_EXISTENT_USER));
//    }

    @Test
    void deleteById() {
        userController.deleteById(100000);
        assertEquals(USER_LIST_WITHOUT_IVAN, userController.getAll());
    }

//    @Test
//    void deleteNotFoundById() {
//        assertThrows(NotFoundException.class, () ->
//                userController.deleteById(NON_EXISTENT_USER.getId()));
//
//    }
}