# 菜单表
create table menu (
    id int(11) not null primary key auto_increment,
    pattern varchar(128) default null unique,
    updated_at datetime not null default now(),
    is_deleted tinyint(1) default 0,
    index is_deleted_index (is_deleted)
)engine=InnoDB default charset=utf8;

# 角色表
create table role (
    id int(11) not null primary key auto_increment,
    name varchar(32) not null unique,
    name_zh varchar(32) not null unique,
    updated_at datetime not null default now(),
    is_deleted tinyint(1) default 0,
    index is_deleted_index (is_deleted)
)engine=InnoDB default charset=utf8;

# 角色菜单表
create table menu_role (
    id int(11) not null primary key auto_increment,
    mid int(11) not null,
    rid int(11) not null,
    updated_at datetime not null default now(),
    is_deleted tinyint(1) default 0,
    index menu_index (mid),
    index role_index (rid),
    index is_deleted_index (is_deleted)
)engine=InnoDB default charset=utf8;

# 用户表
create table user (
    id int(11) not null primary key auto_increment,
    username varchar(32) not null unique,
    nickname varchar(128) not null,
    password varchar(255) not null,
    enabled tinyint(1) default 1,
    account_non_expired tinyint(1) default 1,
    account_non_locked tinyint(1) default 1,
    credentials_non_expired tinyint(1) default 1,
    updated_at datetime not null default now(),
    is_deleted tinyint(1) default 0,
    index is_deleted_index (is_deleted)
)engine=InnoDB default charset=utf8;


# 用户角色表
create table user_role (
    id int(11) not null primary key auto_increment,
    uid int(11) not null,
    rid int(11) not null,
    index user_index (uid),
    index role_index (rid),
    updated_at datetime not null default now(),
    is_deleted tinyint(1) default 0,
    index is_deleted_index (is_deleted)
)engine=InnoDB default charset=utf8;

# 插入数据
BEGIN;
INSERT INTO menu(id, pattern) VALUES (1, '/admin/*');
INSERT INTO menu(id, pattern) VALUES (2, '/user/*');
INSERT INTO menu(id, pattern) VALUES (3, '/guest/*');
COMMIT;

BEGIN;
INSERT INTO menu_role(id, mid, rid) VALUES (1, 1, 1);
INSERT INTO menu_role(id, mid, rid) VALUES (2, 2, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (3, 3, 3);
INSERT INTO menu_role(id, mid, rid) VALUES (4, 3, 2);
COMMIT;

BEGIN;
INSERT INTO role(id, name, name_zh) VALUES (1, 'ADMIN', '系统管理员');
INSERT INTO role(id, name, name_zh) VALUES (2, 'USER', '普通⽤户');
INSERT INTO role(id, name, name_zh) VALUES (3, 'GUEST', '游客');
COMMIT;

BEGIN;
INSERT INTO user (id, username, nickname, password) VALUES (1, '10000', 'admin', '{noop}123');
INSERT INTO user (id, username, nickname, password) VALUES (2, '20000', 'user', '{noop}123');
INSERT INTO user (id, username, nickname, password) VALUES (3, '20220102001', 'blr', '{noop}123');
COMMIT;

BEGIN;
INSERT INTO user_role(id, uid, rid) VALUES (1, 1, 1);
INSERT INTO user_role(id, uid, rid) VALUES (2, 1, 2);
INSERT INTO user_role(id, uid, rid) VALUES (3, 2, 2);
INSERT INTO user_role(id, uid, rid) VALUES (4, 3, 3);
COMMIT;


# 分类
create table category (
    id int(11) not null primary key auto_increment,
    name varchar(255) not null,
    updated_at datetime not null default now(),
    is_deleted tinyint(1) default 0,
    index is_deleted_index (is_deleted)
)engine=InnoDB default charset=utf8;


# 课程
create table course (
    id int(11) not null primary key auto_increment,
    name varchar(255) not null,
    category_id int(11) not null,
    updated_at datetime not null default now(),
    is_deleted tinyint(1) default 0,
    index category_id_index (category_id),
    index is_deleted_index (is_deleted)
)engine=InnoDB default charset=utf8;


# 章节
create table chapter (
    id int(11) not null primary key auto_increment,
    name varchar(255) not null,
    course_id int(11) not null,
    updated_at datetime not null default now(),
    is_deleted tinyint(1) default 0,
    index course_id_index (course_id),
    index is_deleted_index (is_deleted)
)engine=InnoDB default charset=utf8;

# 视频
create table video (
    id int(11) not null primary key auto_increment,
    name varchar(100) not null,
    description varchar(255) not null,
    url varchar(100) default null,
    chapter_id int(11) not null,
    updated_at datetime not null default now(),
    is_deleted tinyint(1) default 0,
    index chapter_id_index (chapter_id),
    index is_deleted_index (is_deleted)
)engine=InnoDB default charset=utf8;


# 考试
create table exam (
    id int(11) not null primary key auto_increment,
    name varchar(100) not null,
    description varchar(255) not null,
    updated_at datetime not null default now(),
    is_deleted tinyint(1) default 0,
    index is_deleted_index (is_deleted)
)engine=InnoDB default charset=utf8;

# 填空问题
create table fill_question (
    id int(11) not null primary key auto_increment,
    exam_id int(11) not null,
    question varchar(255) not null,
    answer varchar(255) not null,
    analysis varchar(255) not null,
    score int(2) default 2,
    updated_at datetime not null default now(),
    is_deleted tinyint(1) default 0,
    index exam_id_index (exam_id),
    index is_deleted_index (is_deleted)
)engine=InnoDB default charset=utf8;

# 判断问题
create table judge_question (
    id int(11) not null primary key auto_increment,
    exam_id int(11) not null,
    question varchar(255) not null,
    answer varchar(255) not null,
    analysis varchar(255) not null,
    score int(2) default 2,
    updated_at datetime not null default now(),
    is_deleted tinyint(1) default 0,
    index exam_id_index (exam_id),
    index is_deleted_index (is_deleted)
)engine=InnoDB default charset=utf8;

# 选择题
create table select_question (
    id int(11) not null primary key auto_increment,
    exam_id int(11) not null,
    question varchar(255) not null,
    answer_a varchar(255) not null,
    answer_b varchar(255) not null,
    answer_c varchar(255) not null,
    answer_d varchar(255) not null,
    answer varchar(255) not null,
    analysis varchar(255) not null,
    score int(2) default 2,
    updated_at datetime not null default now(),
    is_deleted tinyint(1) default 0,
    index exam_id_index (exam_id),
    index is_deleted_index (is_deleted)
)engine=InnoDB default charset=utf8;

# 测试
create table quiz (
    id int(11) not null primary key auto_increment,
    exam_id int(11) not null,
    uid int(11) not null,
    total_score int(2) default 0,
    updated_at datetime not null default now(),
    is_deleted tinyint(1) default 0,
    index exam_id_index (exam_id),
    index uid_index (uid),
    index is_deleted_index (is_deleted)
)engine=InnoDB default charset=utf8;


# 问题结果
create table quiz_question (
    id int(11) not null primary key auto_increment,
    exam_id int(11) not null,
    quiz_id int(11) not null,
    question_id int(11) not null,
    answer varchar(255) not null,
    score int(2) not null,
    question_type int(2) not null,
    updated_at datetime not null default now(),
    is_deleted tinyint(1) default 0,
    index exam_id_index (exam_id),
    index quiz_id_index (quiz_id),
    index question_id_index (question_id),
    index is_deleted_index (is_deleted)
)engine=InnoDB default charset=utf8;