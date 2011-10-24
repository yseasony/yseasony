/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2011/6/23 10:16:55                           */
/*==============================================================*/


/*==============================================================*/
/* Table: acg_authority                                         */
/*==============================================================*/
create table acg.acg_authority
(
   id                   int(11) not null auto_increment,
   name                 national varchar(255) not null,
   display_name         varchar(30) not null,
   b_name               varchar(20) not null,
   primary key (id),
   key name (name)
);

/*==============================================================*/
/* Table: acg_role                                              */
/*==============================================================*/
create table acg.acg_role
(
   id                   int(11) not null auto_increment,
   name                 national varchar(20) not null,
   primary key (id),
   key name (name)
);

/*==============================================================*/
/* Table: acg_role_authority                                    */
/*==============================================================*/
create table acg.acg_role_authority
(
   role_id              int(11) not null,
   authority_id         int(11) not null
);

/*==============================================================*/
/* Table: acg_user                                              */
/*==============================================================*/
create table acg.acg_user
(
   id                   bigint(20) not null auto_increment,
   email                national varchar(100) default '',
   login_name           national varchar(30) not null,
   name                 national varchar(20) default '',
   password             national varchar(20) not null,
   enabled              bit not null default b'1',
   sex                  bit not null default b'1',
   primary key (id),
   key login_name (login_name)
);

/*==============================================================*/
/* Table: acg_user_role                                         */
/*==============================================================*/
create table acg.acg_user_role
(
   user_id              bigint(20) not null,
   role_id              int(11) not null
);

alter table acg.acg_role_authority add constraint fkae2434663fe97564 foreign key (authority_id)
      references acg.acg_authority (id);

alter table acg.acg_role_authority add constraint fkae243466de3fb930 foreign key (role_id)
      references acg.acg_role (id);

alter table acg.acg_user_role add constraint fkfe85cb3e836a7d10 foreign key (user_id)
      references acg.acg_user (id);

alter table acg.acg_user_role add constraint fkfe85cb3ede3fb930 foreign key (role_id)
      references acg.acg_role (id);

