DELETE FROM USERS;
DELETE FROM VOTES;
DELETE FROM MEALS;
DELETE FROM RESTAURANTS;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('Ivan',        'adminOne@mail.ru',  'adminPass1'),
       ('Nicolas',     'userOne@mail.ru',   'userPass1'),
       ('Sue',         'userTwo@mail.ru',   'userPass2'),
       ('Perry',       'userThree@mail.ru', 'userPass3'),
       ('John',        'userFour@mail.ru',  'userPass4');


INSERT INTO RESTAURANTS (NAME, ADDRESS)
VALUES ('Peperoni',    'Lenin Avenue, 34'),
       ('Perchini',    'Russian Avenue, 168'),
       ('Mindal',      'Lenin Avenue, 188');

INSERT INTO MEALS (NAME, PRICE, DATE, RESTAURANT_ID)
VALUES ('Spicy grilled eggplant',                350.0, CURRENT_DATE,                     100005),
       ('Truffle burger',                        650.0, CURRENT_DATE,                     100005),
       ('Egg benedict with hollandaise sauce',   580.0, CURRENT_DATE,                     100005),
       ('Focaccia with burrata and basil',       250.0, CURRENT_DATE,                     100006),
       ('Sliced ​​tuna with pak-choi',             470.0, CURRENT_DATE,                     100006),
       ('Pizza with nutella and berries',        330.0, CURRENT_DATE,                     100006),
       ('Сhicken quesadilla',                    310.0, DATEADD('DAY', -1, CURRENT_DATE), 100005),
       ('Pasta pesto',                           350.0, DATEADD('DAY', -1, CURRENT_DATE), 100005),
       ('Сake fondant',                          190.0, DATEADD('DAY', -1, CURRENT_DATE), 100005),
       ('Ham sandwich',                          275.0, DATEADD('DAY', -1, CURRENT_DATE), 100006),
       ('Pancake with meat',                     108.0, DATEADD('DAY', -1, CURRENT_DATE), 100006),
       ('Grilled rabbit',                        180.0, DATEADD('DAY', -1, CURRENT_DATE), 100006);

INSERT INTO votes (user_id, restaurant_id, date, time) VALUES
       ('100000', '100005', CURRENT_DATE,                     '08:30:00'),
       ('100001', '100005', CURRENT_DATE,                     '09:00:00'),
       ('100002', '100006', CURRENT_DATE,                     '09:00:00'),
       ('100003', '100006', CURRENT_DATE,                     '10:30:00'),
       ('100004', '100006', CURRENT_DATE,                     '10:30:00'),
       ('100000', '100007', DATEADD('DAY', -1, CURRENT_DATE), '08:30:00'),
       ('100001', '100007', DATEADD('DAY', -1, CURRENT_DATE), '10:10:00'),
       ('100002', '100006', DATEADD('DAY', -1, CURRENT_DATE), '09:00:00'),
       ('100003', '100005', DATEADD('DAY', -1, CURRENT_DATE), '10:00:00'),
       ('100004', '100005', DATEADD('DAY', -1, CURRENT_DATE), '08:40:00');

