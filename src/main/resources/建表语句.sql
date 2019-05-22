create table course
(
	cid VARCHAR(20) primary key ,
	`name` VARCHAR(20) not null,
	tid VARCHAR(20)
);
create table knowledge_point
(
  kid varchar(20) ,
  kname varchar(20),
  cid varchar(20),
  createTime DATE
);

create table paper_extracting
(
	qid VARCHAR(20),
	pid VARCHAR(20),
	remark text ,
	primary key(qid,pid)
);

create table question_bank
(
	qid VARCHAR(20) PRIMARY KEY,
	cid VARCHAR(20),
	kid varchar (20),
	content text,
	rightAnswers text,
	type VARCHAR(30),
	questionRating int,
	creatTime DATE
);


create table teacher
(
	tid VARCHAR(20) PRIMARY KEY,
	password VARCHAR(20),
	name char(5) ,
	address VARCHAR(30),
	phone char(11),
	email VARCHAR(20)
);


create table test_paper
(
	pid VARCHAR(20) PRIMARY KEY,
	name VARCHAR(20) ,
	cid VARCHAR(20),
	testTypes VARCHAR(20),
	teacherNum varchar (20),
	score int,
	createTime DATE,
	choiceSocre VARCHAR(20),
	fillBlankSocre VARCHAR(20),
	answerSocre VARCHAR(20),
	judgeSocre VARCHAR(20)
);

insert into teacher values('1','123','周慧灿','湖南常德','198989898','dfdsfdsf@163.com');


