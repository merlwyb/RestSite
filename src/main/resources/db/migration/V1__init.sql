CREATE TABLE STUDENT
(
    id NUMBER PRIMARY KEY,
    student_name varchar2(255) NOT NULL,
    group_name varchar2(255) NOT NULL
);
CREATE SEQUENCE sq_student_id START WITH 1 INCREMENT BY 1;

