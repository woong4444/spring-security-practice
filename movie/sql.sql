select 'db 연결 성공' as message from dual;

--todo table
CREATE TABLE todo (
	NO NUMBER CONSTRAINT todo_no_pk PRIMARY KEY,
	content varchar2(4000) CONSTRAINT todo_content_nn NOT NULL,
	complete varchar2(1) DEFAULT 'N'
);
CREATE SEQUENCE todo_seq 
START WITH 1
INCREMENT BY 1
MAXVALUE 9999999999999
nocache
nocycle;


SELECT * FROM todo;
--insert 3개
INSERT INTO todo (NO,content,complete) VALUES (todo_seq.nextval,'점심먹기','N');
INSERT INTO todo (NO,content,complete) VALUES (todo_seq.nextval,'산책','N');
INSERT INTO todo (NO,content,complete) VALUES (todo_seq.nextval,'스프링 공부','N');
COMMIT;

UPDATE todo SET  complete = 'Y' WHERE NO=4;


DELETE FROM todo WHERE NO = 1 OR 1=1


CREATE TABLE movie(
	NO         NUMBER        CONSTRAINT movie_no_pk PRIMARY KEY ,
	title      varchar2(100) CONSTRAINT movie_title_nn NOT NULL ,
	reserve_yn varchar2(1)   DEFAULT 'N'
);
SELECT * FROM movie;
CREATE SEQUENCE movie_seq
START WITH 1
INCREMENT BY 1
MAXVALUE 99999999999
nocache
nocycle;

SELECT * FROM movie;

INSERT INTO movie VALUES (movie_seq.nextval,'범죄도시','N');
INSERT INTO movie VALUES (movie_seq.nextval,'파묘','N');
INSERT INTO movie VALUES (movie_seq.nextval,'왕과 사는 남자','N');
COMMIT;

UPDATE movie SET reserve_yn = 'Y' WHERE NO = 1;
DELETE FROM movie WHERE NO = 1;
ROLLBACK;


DROP TABLE board;
CREATE TABLE board(
	NO NUMBER CONSTRAINT board_no_pk PRIMARY KEY,
	title varchar2(300) CONSTRAINT board_title_nn NOT NULL ,
	nickname varchar2(300) CONSTRAINT board_nickname_nn NOT NULL,
	content clob  CONSTRAINT board_content_nn NOT NULL ,
	hit NUMBER DEFAULT 0,
	regdate DATE DEFAULT sysdate 
);

CREATE SEQUENCE board_seq
START WITH 1
INCREMENT BY 1
MAXVALUE 99999999999
nocache
nocycle;

SELECT * FROM board;

INSERT INTO 
	board (no,title,content,nickname,regdate,hit) 
	VALUES (board_seq.nextval,'제목','내용','홍길동',sysdate,0);
ROLLBACK;
COMMIT;

SELECT * FROM board WHERE NO=3;
UPDATE board SET hit = hit+1 WHERE NO=3;
ROLLBACK;


DROP TABLE MEMBER;
CREATE TABLE member (
    no          			NUMBER PRIMARY KEY,
    user_id     			VARCHAR2(100) NOT NULL UNIQUE,
    user_name   			VARCHAR2(100) NOT NULL,
    user_pw     			VARCHAR2(100) NOT NULL,
    email       			VARCHAR2(200) NOT NULL UNIQUE,
    phone       			VARCHAR2(30),
    address     			VARCHAR2(500),
    zipcode     			NUMBER,
    detail_address         	VARCHAR2(300),
    profile                	VARCHAR2(300),
    thumbnail_profile      	VARCHAR2(300),
    regdate     DATE DEFAULT sysdate
);

CREATE SEQUENCE member_seq
START WITH 1
INCREMENT BY 1
NOCACHE;

INSERT INTO MEMBER VALUES 
(member_seq.nextval,'jjang051','장성호',
'1234','jjang051','010-1111-1111','일산시 장항동 11-11',sysdate);


COMMIT;
DELETE FROM MEMBER;
SELECT * FROM MEMBER;

SELECT count(*) FROM MEMBER WHERE USER_ID= 'aaa';

SELECT count(*) FROM MEMBER WHERE email= 'jjang051hta@naver.com';

SELECT count(*) FROM MEMBER WHERE user_id='aaa' AND user_pw='1234';

SELECT count(*) FROM MEMBER 
                            WHERE user_id='jjang051';



CREATE TABLE new_member (
    no          			NUMBER PRIMARY KEY,
    user_id     			VARCHAR2(100) NOT NULL UNIQUE,
    user_name   			VARCHAR2(100) NOT NULL,
    user_pw     			VARCHAR2(100) NOT NULL,
    email       			VARCHAR2(200) NOT NULL UNIQUE,
    phone       			VARCHAR2(30),
    address     			VARCHAR2(500),
    zipcode     			NUMBER,
    detail_address         	VARCHAR2(300),
    profile                	VARCHAR2(300),
    thumbnail_profile      	VARCHAR2(300),
    regdate     DATE DEFAULT sysdate
);
INSERT INTO new_member values(member_seq.nextval,'aaaa','장성호','1111',
'jjang051@hanmail.net','01011111111','장항동',11111,'111-111','aaaaa','aaaaaaaa',sysdate);
SELECT * FROM new_MEMBER WHERE user_id='jjang051' AND user_pw ='1234';
ROLLBACK;
SELECT * FROM new_member;

DROP TABLE new_board;

CREATE TABLE NEW_BOARD (
    NO NUMBER CONSTRAINT new_BOARD_NO_PK PRIMARY KEY,
    TITLE VARCHAR2(300) CONSTRAINT new_BOARD_TITLE_NN NOT NULL,
    CONTENT CLOB CONSTRAINT new_BOARD_CONTENT_NN NOT NULL,
    --NICKNAME VARCHAR2(100) CONSTRAINT new_BOARD_NICKNAME_NN NOT NULL,
    hit NUMBER DEFAULT 0,
    regdate DATE DEFAULT sysdate 
);

CREATE SEQUENCE new_BOARD_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

SELECT * FROM new_board;

SELECT * FROM new_member;
DELETE FROM new_member;
COMMIT;

SELECT * FROM new_board WHERE NO=1;
