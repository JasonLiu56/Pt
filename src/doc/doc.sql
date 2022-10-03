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
INSERT INTO menu(id, pattern) VALUES (1, '/menu/**');
INSERT INTO menu(id, pattern) VALUES (2, '/role/**');
INSERT INTO menu(id, pattern) VALUES (3, '/user/**');

INSERT INTO menu(id, pattern) VALUES (4, '/category/**');
INSERT INTO menu(id, pattern) VALUES (5, '/course/**');
INSERT INTO menu(id, pattern) VALUES (6, '/chapter/**');
INSERT INTO menu(id, pattern) VALUES (7, '/video/**');
INSERT INTO menu(id, pattern) VALUES (8, '/exam/**');
INSERT INTO menu(id, pattern) VALUES (9, '/question/fill/**');
INSERT INTO menu(id, pattern) VALUES (10, '/question/judge/**');
INSERT INTO menu(id, pattern) VALUES (11, '/question/select/**');

INSERT INTO menu(id, pattern) VALUES (12, '/quiz/**');
INSERT INTO menu(id, pattern) VALUES (13, '/quiz/question/**');
INSERT INTO menu(id, pattern) VALUES (14, '/my/**');
INSERT INTO menu(id, pattern) VALUES (15, '/category/getById');
INSERT INTO menu(id, pattern) VALUES (16, '/category/getByPage');
INSERT INTO menu(id, pattern) VALUES (17, '/course/getById');
INSERT INTO menu(id, pattern) VALUES (18, '/course/getByPage');
INSERT INTO menu(id, pattern) VALUES (19, '/chapter/getById');
INSERT INTO menu(id, pattern) VALUES (20, '/chapter/getAll');
INSERT INTO menu(id, pattern) VALUES (21, '/video/getById');
INSERT INTO menu(id, pattern) VALUES (22, '/video/getAll');
INSERT INTO menu(id, pattern) VALUES (23, '/exam/getById');
INSERT INTO menu(id, pattern) VALUES (24, '/exam/getByPage');
INSERT INTO menu(id, pattern) VALUES (25, '/question/fill/getById');
INSERT INTO menu(id, pattern) VALUES (26, '/question/fill/getAll');
INSERT INTO menu(id, pattern) VALUES (27, '/question/judge/getById');
INSERT INTO menu(id, pattern) VALUES (28, '/question/judge/getAll');
INSERT INTO menu(id, pattern) VALUES (29, '/question/select/getById');
INSERT INTO menu(id, pattern) VALUES (30, '/question/select/getAll');
COMMIT;

BEGIN;
INSERT INTO role(id, name, name_zh) VALUES (1, 'ROLE_ADMIN', '系统管理员');
INSERT INTO role(id, name, name_zh) VALUES (2, 'ROLE_TEACHER', '教师');
INSERT INTO role(id, name, name_zh) VALUES (3, 'ROLE_STUDENT', '学生');
COMMIT;

BEGIN;
INSERT INTO menu_role(id, mid, rid) VALUES (1, 1, 1);
INSERT INTO menu_role(id, mid, rid) VALUES (2, 2, 1);
INSERT INTO menu_role(id, mid, rid) VALUES (3, 3, 1);

INSERT INTO menu_role(id, mid, rid) VALUES (4, 4, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (5, 5, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (6, 6, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (7, 7, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (8, 8, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (9, 9, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (10, 10, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (11, 11, 2);


INSERT INTO menu_role(id, mid, rid) VALUES (12, 12, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (13, 13, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (14, 14, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (15, 15, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (16, 16, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (17, 17, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (18, 18, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (19, 19, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (20, 20, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (21, 21, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (22, 22, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (23, 23, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (24, 24, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (25, 25, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (26, 26, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (27, 27, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (28, 28, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (29, 29, 2);
INSERT INTO menu_role(id, mid, rid) VALUES (30, 30, 2);

INSERT INTO menu_role(id, mid, rid) VALUES (31, 12, 3);
INSERT INTO menu_role(id, mid, rid) VALUES (32, 13, 3);
INSERT INTO menu_role(id, mid, rid) VALUES (33, 14, 3);
INSERT INTO menu_role(id, mid, rid) VALUES (34, 15, 3);
INSERT INTO menu_role(id, mid, rid) VALUES (35, 16, 3);
INSERT INTO menu_role(id, mid, rid) VALUES (36, 17, 3);
INSERT INTO menu_role(id, mid, rid) VALUES (37, 18, 3);
INSERT INTO menu_role(id, mid, rid) VALUES (38, 19, 3);
INSERT INTO menu_role(id, mid, rid) VALUES (39, 20, 3);
INSERT INTO menu_role(id, mid, rid) VALUES (40, 21, 3);
INSERT INTO menu_role(id, mid, rid) VALUES (41, 22, 3);
INSERT INTO menu_role(id, mid, rid) VALUES (42, 23, 3);
INSERT INTO menu_role(id, mid, rid) VALUES (43, 24, 3);
INSERT INTO menu_role(id, mid, rid) VALUES (44, 25, 3);
INSERT INTO menu_role(id, mid, rid) VALUES (45, 26, 3);
INSERT INTO menu_role(id, mid, rid) VALUES (46, 27, 3);
INSERT INTO menu_role(id, mid, rid) VALUES (47, 28, 3);
INSERT INTO menu_role(id, mid, rid) VALUES (48, 29, 3);
INSERT INTO menu_role(id, mid, rid) VALUES (49, 30, 3);

COMMIT;


BEGIN;
INSERT INTO user (id, username, nickname, password) VALUES (1, '10000', '超级管理员', '{bcrypt}$2a$10$mlIJkmRorJqTQWXRsJGsue2txTvx9UPq0hRGlnBaqBUGCoZShCJpa');
INSERT INTO user (id, username, nickname, password) VALUES (2, '20000', '教师1', '{bcrypt}$2a$10$mlIJkmRorJqTQWXRsJGsue2txTvx9UPq0hRGlnBaqBUGCoZShCJpa');
INSERT INTO user (id, username, nickname, password) VALUES (3, '202207101', '学生1', '{bcrypt}$2a$10$mlIJkmRorJqTQWXRsJGsue2txTvx9UPq0hRGlnBaqBUGCoZShCJpa');
COMMIT;

BEGIN;
INSERT INTO user_role(id, uid, rid) VALUES (1, 1, 1);
INSERT INTO user_role(id, uid, rid) VALUES (2, 2, 2);
INSERT INTO user_role(id, uid, rid) VALUES (3, 3, 3);
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