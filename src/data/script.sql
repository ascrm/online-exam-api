create table tb_exam_history
(
    id               int auto_increment
        primary key,
    exam_paper_id    int                                null,
    exam_question_id int                                null,
    status           int                                null comment '答题状态（0：错误，1：正确，2：未作答，3：未批改）',
    created_by       varchar(64)                        null,
    created_at       datetime default CURRENT_TIMESTAMP null,
    updated_at       datetime default CURRENT_TIMESTAMP null,
    is_delete        tinyint(1)                         null
);

create table tb_exam_paper
(
    id            int auto_increment
        primary key,
    name          varchar(200)                         null,
    description   text                                 null,
    duration      smallint                             null comment '考试时长（分钟）',
    total_score   decimal(5, 2)                        null,
    passing_score decimal(5, 2)                        null,
    is_published  tinyint(1) default 0                 null comment '1发布 0未发布',
    created_by    varchar(64)                          null,
    created_at    datetime   default CURRENT_TIMESTAMP null,
    updated_at    datetime   default CURRENT_TIMESTAMP null,
    is_delete     tinyint(1) default 0                 null
);

create table tb_exam_question
(
    id            int auto_increment
        primary key,
    exam_paper_id int                  null,
    question_id   int                  null,
    is_delete     tinyint(1) default 0 null
);

create table tb_history_exam
(
    id            int auto_increment
        primary key,
    username      varchar(128)                         null,
    exam_paper_id int                                  null,
    total_score   decimal(5, 2)                        null comment '得分',
    created_at    timestamp  default CURRENT_TIMESTAMP null,
    is_delete     tinyint(1) default 0                 null
);

create table tb_history_exam_question
(
    id              int auto_increment
        primary key,
    history_exam_id int                  null,
    question_id     int                  null,
    answer          varchar(128)         null,
    correct         tinyint(1)           null comment '1正确  0错误',
    is_delete       tinyint(1) default 0 null
);

create table tb_judge_question
(
    id          int auto_increment
        primary key,
    question_id int                                  null,
    answer      char                                 null,
    created_by  varchar(128)                         null,
    created_at  datetime   default CURRENT_TIMESTAMP null,
    updated_at  datetime   default CURRENT_TIMESTAMP null,
    is_delete   tinyint(1) default 0                 null
)
    comment '判断题表';

create table tb_multiple_choice_question
(
    id          int auto_increment comment '主键'
        primary key,
    question_id int                                  null,
    option_A    varchar(128)                         null,
    option_B    varchar(128)                         null,
    option_C    varchar(128)                         null,
    option_D    varchar(128)                         null,
    answer      varchar(64)                          null,
    created_by  varchar(128)                         null,
    created_at  datetime   default CURRENT_TIMESTAMP null,
    updated_at  datetime   default CURRENT_TIMESTAMP null,
    is_delete   tinyint(1) default 0                 null
)
    comment '多选题表';

create table tb_question
(
    id            int auto_increment
        primary key,
    name          varchar(128)                         null comment '题目名称',
    question_type int                                  null comment '题目类型 1单选，2多选，3判断，4解答',
    description   varchar(200)                         null comment '题目描述',
    score         decimal(5, 2)                        null comment '题目分数',
    analysis      text                                 null comment '题目解析',
    difficulty    tinyint    default 1                 null comment '1-5级难度',
    created_by    varchar(64)                          null,
    created_at    datetime   default CURRENT_TIMESTAMP null,
    updated_at    datetime   default CURRENT_TIMESTAMP null,
    is_deleted    tinyint(1) default 0                 null
);

create table tb_single_choice_question
(
    id          int auto_increment
        primary key,
    question_id int                                  null,
    option_A    varchar(128)                         null,
    option_B    varchar(128)                         null,
    option_C    varchar(128)                         null,
    option_D    varchar(128)                         null,
    answer      char                                 null comment '正确答案',
    created_by  varchar(128)                         null,
    created_at  datetime   default CURRENT_TIMESTAMP null,
    updated_at  datetime   default CURRENT_TIMESTAMP null,
    is_delete   tinyint(1) default 0                 null comment '逻辑删除'
);

create table tb_user
(
    id         int auto_increment comment '主键'
        primary key,
    username   varchar(64)                null comment '账号',
    password   varchar(64)                null comment '密码',
    role       int                        null comment '角色: 1 管理员; 2 用户',
    nick_name  varchar(128)               null comment '昵称',
    gender     int                        null comment '性别: 1男;2女',
    avatar     varchar(128)               null comment '头像',
    email      varchar(64)                null comment '邮箱',
    phone      varchar(11)                null comment '电话',
    created_by varchar(128)               null comment '创建人',
    created_at datetime   default (now()) null comment '创建时间',
    is_delete  tinyint(1) default 0       null comment '逻辑删除 1被删除;0未被删除'
);


