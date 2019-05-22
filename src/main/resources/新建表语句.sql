create table if not exists course
(
		c_id int not null auto_increment primary key comment '课程id',
		c_name VARCHAR(20) not null comment '课程名字',
		t_id int not null comment '教师id'
)engine=Innodb default charset=utf8;

create table if not exists teacher
(
	t_id int not null auto_increment primary key comment '教师id',
	t_name varchar(20) not null comment '教师姓名',
	t_address varchar(20) not null comment '教师地址',
	t_email varchar(20) not null comment '教师邮箱',
	t_phone char(11) not null comment '教师电话' 
) engine=Innodb default charset=utf8;

create table if not exists knowledge_point
(
	k_id int not null auto_increment primary key comment '知识点id',
	k_name varchar(20) not null comment '知识点名字',
	c_id int not null comment '课程id 表明这个知识点是属于哪个课程的',
	k_number varchar(20) not null comment '知识点序号 比如这个知识点是第一章第一节 那么就填写1-1',
	k_createtime date not null comment '创建日期' 
) engine=Innodb default charset=utf8;


create table if not exists question
(
	q_id int not null auto_increment primary key comment '问题id',
	k_id int not null COMMENT '知识点id',
	q_content text not null comment '问题描述',
	q_right_answer text not null comment '问题的正确回答',
	q_type varchar(10) not null comment '问题的题型',
	q_difflevel double not null comment '问题难度系数',
	q_createtime date not null comment '问题创建时间'	
)engine=Innodb default charset=utf8;

create table if not exists paper
(
	p_id int not null auto_increment primary key comment '试卷id', 
	p_name varchar(20) comment '试卷名字', 
	c_id int not null comment '课程id',
	t_id int comment '教师id',
	p_test_types varchar(20) not null comment '考试类型',
	p_score int not null comment '考试总分',
	p_createtime date  not null comment '试卷生成日期',
	p_choiceSocre int not null  comment '每题选择题的分数',
	p_fillBlankSocre int not null  comment '每题填空题的分数',
	p_answerSocre int not null  comment '每题简单题的分数',
	p_judgeSocre int not null  comment '每题判断题的分数',
	p_difflevel double(6,2) not null comment '试卷难度系数'
)engine=Innodb default charset=utf8;

create table if not exists paper_question
(
	p_id int not null comment '试卷id',
	q_id int not null comment '问题id',
	primary key(p_id,q_id)
) engine=Innodb default charset=utf8;