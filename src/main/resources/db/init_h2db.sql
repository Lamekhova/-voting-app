DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS votes;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users (
  id            INTEGER DEFAULT global_seq.nextval PRIMARY KEY,
  name          VARCHAR_IGNORECASE      NOT NULL,
  email         VARCHAR                 NOT NULL,
  password      VARCHAR                 NOT NULL,
  CONSTRAINT users_email UNIQUE (email)
);

CREATE TABLE restaurants (
  id            INTEGER DEFAULT global_seq.nextval PRIMARY KEY,
  name          VARCHAR                 NOT NULL,
  address       VARCHAR                 NOT NULL,
  CONSTRAINT restaurant_name UNIQUE (name)
);


CREATE TABLE meals (
  id            INTEGER DEFAULT global_seq.nextval PRIMARY KEY,
  name          VARCHAR                 NOT NULL,
  price         NUMERIC(7, 2)           NOT NULL,
  date          DATE                    NOT NULL,
  restaurant_id INTEGER                 NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE votes
(
  id            INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
  date          DATE                    NOT NULL,
  time          TIME                    NOT NULL,
  user_id       INTEGER                 NOT NULL,
  restaurant_id INTEGER                 NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
