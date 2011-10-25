/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2011/10/25 14:19:09                          */
/*==============================================================*/


drop table if exists exam_examination_pager;

drop table if exists exam_question_bank;

drop table if exists exam_question_type;

drop table if exists exam_student_answer;

/*==============================================================*/
/* Table: exam_examination_pager                                */
/*==============================================================*/
create table exam_examination_pager
(
   ex_id                int not null,
   title                varchar(50) not null,
   start_time           date not null,
   end_time             date not null,
   invigilate_name      varchar(30) not null,
   create_time          date,
   primary key (ex_id)
);

/*==============================================================*/
/* Table: exam_question_bank                                    */
/*==============================================================*/
create table exam_question_bank
(
   qb_id                int not null,
   ex_id                int not null,
   qt_id                int not null,
   title                varchar(500) not null,
   question             varchar(500) not null default '',
   answer               varchar(500) not null,
   score                int not null,
   create_time          date,
   primary key (qb_id)
);

/*==============================================================*/
/* Table: exam_question_type                                    */
/*==============================================================*/
create table exam_question_type
(
   qt_id                int not null,
   type_name            varchar(15) not null,
   primary key (qt_id)
);

/*==============================================================*/
/* Table: exam_student_answer                                   */
/*==============================================================*/
create table exam_student_answer
(
   sa_id                int not null,
   qb_id                int not null,
   ex_id                int not null,
   qt_id                int,
   answer               varchar(500) not null,
   create_time          date not null,
   primary key (sa_id)
);

alter table exam_question_bank add constraint FK_exam_pager_r_question_bank foreign key (ex_id)
      references exam_examination_pager (ex_id) on delete restrict on update restrict;

alter table exam_question_bank add constraint FK_question_type_r_question_bank foreign key (qt_id)
      references exam_question_type (qt_id) on delete restrict on update restrict;

alter table exam_student_answer add constraint FK_Reference_4 foreign key (ex_id)
      references exam_examination_pager (ex_id) on delete restrict on update restrict;

alter table exam_student_answer add constraint FK_question_bank_r_student_answer foreign key (qb_id)
      references exam_question_bank (qb_id) on delete restrict on update restrict;

alter table exam_student_answer add constraint FK_question_type_r_student_answer foreign key (qt_id)
      references exam_question_type (qt_id) on delete restrict on update restrict;

