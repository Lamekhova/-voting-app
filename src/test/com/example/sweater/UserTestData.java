package com.example.sweater;

import com.example.sweater.model.User;

import java.util.List;

public class UserTestData {

    public static final User IVAN = new User(100000, "Ivan", "adminOne@mail.ru", "adminPass1");
    public static final User NICOLAS = new User(100001, "Nicolas", "userOne@mail.ru", "userPass1");
    public static final User SUE = new User(100002, "Sue", "userTwo@mail.ru", "userPass2");
    public static final User PERRY = new User(100003, "Perry", "userThree@mail.ru", "userPass3");
    public static final User JOHN = new User(100004, "John", "userFour@mail.ru", "userPass4");

    public static User MARRY = new User("Marry", "userFive@mail.ru", "userPass5");

    public static final User WITH_DUPLICATE_EMAIL = new User("Johnson", "userFour@mail.ru", "userPass6");
    public static final User NOT_EXISTENT_USER = new User(1, "Barry", "userBarry@mail.ru", "userPass7");

    public static final List<User> USER_LIST = List.of(IVAN, JOHN, MARRY, NICOLAS, PERRY, SUE);
    public static final List<User> USER_LIST_WITHOUT_IVAN = List.of(JOHN, MARRY, NICOLAS, PERRY, SUE);


}
