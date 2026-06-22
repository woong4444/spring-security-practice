CREATE TABLE security_member (
                                 NO NUMBER CONSTRAINT security_member_no_pk PRIMARY KEY,
                                 USER_ID varchar2(100) CONSTRAINT security_member_userid_nn NOT NULL
						                               CONSTRAINT security_member_userid_uk UNIQUE,
                                 USER_PW varchar2(100) CONSTRAINT security_member_userpw_nn NOT NULL,
                                 USER_NAME varchar2(100) CONSTRAINT security_member_username_nn NOT NULL,
                                 ROLE    varchar2(20)  DEFAULT 'ROLE_USER' CONSTRAINT security_member_role_nn NOT NULL,
                                 REGDATE DATE DEFAULT sysdate
);
DROP TABLE security_member;
CREATE SEQUENCE seq_sequrity_member
    START WITH 1
    INCREMENT BY 1
    nocache
nocycle;

SELECT * FROM security_member;