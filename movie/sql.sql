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




