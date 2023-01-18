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
