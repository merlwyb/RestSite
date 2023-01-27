CREATE TABLE ROLE
(
    id   NUMBER PRIMARY KEY,
    name varchar2(50) NOT NULL UNIQUE
);
CREATE SEQUENCE sq_role_id START WITH 1 INCREMENT BY 1;

CREATE TABLE USERS
(
    id            NUMBER PRIMARY KEY,
    login         VARCHAR2(255) NOT NULL UNIQUE,
    hash_password VARCHAR2(255) NOT NULL,
    full_name     VARCHAR2(255) NOT NULL,
    token_amount  NUMBER
);
CREATE SEQUENCE sq_user_id START WITH 1 INCREMENT BY 1;

CREATE TABLE USER_ROLE
(
    user_id NUMBER NOT NULL,
    role_id NUMBER NOT NULL
);

INSERT INTO ROLE
VALUES (next value for sq_role_id, 'ROLE_USER');
INSERT INTO ROLE
VALUES (next value for sq_role_id, 'ROLE_ADMIN');

-- LOGIN:PASSWORD:
-- admin:admin
-- omni:omni
INSERT INTO USERS (id, login, hash_password, full_name, token_amount)
VALUES (next value for sq_user_id, 'admin', '$2a$04$l6jf/IelD8EcKEx0z5LJFur01DtdBcTLUxfiq79X1GF2hgJdmIeEW',
             'Ivanov Igor Ivanovich', 100.0),
       (next value for sq_user_id, 'user', '$2a$04$uUTMuVyvusd6gIkxLdrF5ufDQ0K359C0Pjq6yBtbctOo3y6mhpwiy',
             'Nikolaev Zib Arm', 0.0);

INSERT INTO USER_ROLE (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 1);


CREATE TABLE GAME
(
    id   NUMBER PRIMARY KEY,
    type varchar2(50) NOT NULL UNIQUE
);
CREATE SEQUENCE sq_game_id START WITH 1 INCREMENT BY 1;

CREATE TABLE MATCH
(
    id              NUMBER PRIMARY KEY,
    bet             NUMBER NOT NULL,
    multiply        NUMBER NOT NULL,
    result          NUMBER NOT NULL,
    match_date_time TIMESTAMP
);
CREATE SEQUENCE sq_match_id START WITH 1 INCREMENT BY 1;

CREATE TABLE MATCH_GAME
(
    match_id NUMBER NOT NULL,
    game_id  NUMBER NOT NULL
);

CREATE TABLE MATCH_USER
(
    match_id NUMBER NOT NULL,
    user_id  NUMBER NOT NULL
);

INSERT INTO GAME
VALUES (next value for sq_game_id, 'GAME_MINES');
INSERT INTO GAME
VALUES (next value for sq_game_id, 'GAME_DICE');

INSERT INTO MATCH (id, bet, multiply, result, match_date_time)
VALUES (next value for sq_match_id, 15.54, 0.5, 7.77, '2022-01-27 18:47:52'),
       (next value for sq_match_id, 22, 0.5, 11, '2022-01-26 18:45:52'),
       (next value for sq_match_id, 89, 0.8, 0, '2022-01-27 19:48:52');

INSERT INTO MATCH_GAME (match_id, game_id)
VALUES (1, 1);

INSERT INTO MATCH_USER (match_id, user_id)
VALUES (1, 1),
       (2, 1),
       (3, 2);