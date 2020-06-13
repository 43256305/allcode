CREATE TABLE TEACHER(
tea_id bigint(8) NOT NULL auto_increment comment '老师ID',   #数据类型中不能没有long
tea_name varchar(10) NOT NULL comment '老师名字',
password varchar(6) NOT NULL comment '登录密码',
primary key(tea_id)
)engine=InnoDB auto_increment=17201301 DEFAULT CHARSET=utf8 COMMENT='教师表';

CREATE TABLE COURSE(
cou_id bigint(8) not null auto_increment comment '课程ID',
cou_name varchar(20) not null comment '课程名字',
primary key(cou_id)  #自增的列一定要是主键
)engine=InnoDB auto_increment=1000 default charset=utf8 comment='课程表';

CREATE TABLE TEA_COU(
tea_id bigint(8) not null,
cou_id bigint(8) not null,
primary key(tea_id,cou_id),
foreign key(tea_id) references TEACHER(tea_id),  #外键的创建
foreign key(cou_id) references COURSE(cou_id)
)engine=InnoDB default charset=utf8 comment='连接表';