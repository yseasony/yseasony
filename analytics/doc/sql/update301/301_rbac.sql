/*==============================================================*/
/* Table: ma_user                                               */
/*==============================================================*/
create table ma_user
(
   id                   int(11) not null auto_increment,
   username             national varchar(20) not null,
   password             national varchar(20),
   email                national varchar(255),
   enabled              bit(1) not null,
   name                 national varchar(255),
   status               national varchar(255),
   version              int(11),
   create_by            national varchar(255),
   create_time          datetime,
   last_modify_by       national varchar(255),
   last_modify_time     datetime,
   primary key (id),
   key loginname (username)
);

/*==============================================================*/
/* Table: ma_role                                               */
/*==============================================================*/
create table ma_role
(
   id                   int(11) not null auto_increment,
   create_by            national varchar(255),
   create_time          datetime,
   last_modify_by       national varchar(255),
   last_modify_time     datetime,
   name                 national varchar(255) not null,
   primary key (id),
   key name (name)
);

/*==============================================================*/
/* Table: ma_authority                                          */
/*==============================================================*/
create table ma_authority
(
   id                   int(11) not null auto_increment,
   create_by            national varchar(255),
   create_time          datetime,
   last_modify_by       national varchar(255),
   last_modify_time     datetime,
   display_name         national varchar(255),
   name                 national varchar(255) not null,
   primary key (id),
   key name (name)
);

/*==============================================================*/
/* Table: ma_user_role                                          */
/*==============================================================*/
create table ma_user_role
(
   user_id              int(11) not null,
   role_id              int(11) not null
);

alter table ma_user_role add constraint FK_id_role_id foreign key (role_id)
      references ma_role (id);

alter table ma_user_role add constraint FK_id_user_id foreign key (user_id)
      references ma_user (id);
      
/*==============================================================*/
/* Table: ma_role_authority                                     */
/*==============================================================*/
create table ma_role_authority
(
   role_id              int(11) not null,
   authority_id         int(11) not null
);

alter table ma_role_authority add constraint FK_authority_id_id foreign key (authority_id)
      references ma_authority (id);

alter table ma_role_authority add constraint FK_role_id_id foreign key (role_id)
      references ma_role (id);      