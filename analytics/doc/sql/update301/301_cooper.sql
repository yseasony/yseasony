/*==============================================================*/
/* Table: ma_cooperation                                        */
/*==============================================================*/
create table ma_cooperation
(
   cid                  smallint(6) not null,
   id                   int(11),
   company              varchar(50) not null,
   cooperate_mode       tinyint,
   brank_name           varchar(50),
   bank_account_name    varchar(50),
   bank_account         char(19),
   mobile_num           char(11),
   address              varchar(200),
   postcode             char(6),
   linkman              varchar(20),
   telephone            char(15),
   faxphone             char(15),
   QQ                   varchar(15),
   email                varchar(30),
   web                  varchar(50),
   wap                  varchar(50),
   protocol_circs       tinyint,
   protocol_code        varchar(50),
   cooperate_circs      tinyint,
   cooperate_oper_att   tinyint,
   background_add       varchar(200),
   background_user      varchar(20),
   background_pass      varchar(20),
   agreement_start_time datetime,
   agreement_end_time   datetime,
   ma_user_id           bigint,
   remark               text,
   create_time          datetime not null,
   pkg_money            decimal(5,2),
   primary key (cid)
);

alter table ma_cooperation comment '合作商家';

alter table ma_cooperation add constraint FK_user_cooperation foreign key (id)
      references ma_user (id) on delete restrict on update restrict;

/*==============================================================*/
/* Table: ma_promotion_pkg                                      */
/*==============================================================*/
create table ma_promotion_pkg
(
   pkg_number           char(10) not null,
   version              char(10) not null,
   os                   varchar(20) not null,
   down_add             varchar(300),
   create_time          datetime not null,
   primary key (pkg_number)
);

/*==============================================================*/
/* Table: ma_talk                                               */
/*==============================================================*/
create table ma_talk
(
   talk_id              int not null auto_increment,
   cid                  smallint(6),
   content              text,
   create_time          datetime,
   primary key (talk_id)
);

alter table ma_talk comment '回访记录';

alter table ma_talk add constraint FK_FK_talk_cooper foreign key (cid)
      references ma_cooperation (cid) on delete restrict on update restrict;
  
/*==============================================================*/
/* Table: ma_coop_file                                          */
/*==============================================================*/
create table ma_coop_file
(
   file_id              int not null auto_increment,
   cid                  smallint(6),
   file_path            varchar(100),
   title                varchar(100),
   server_id            varchar(100),
   primary key (file_id)
);

alter table ma_coop_file comment '文件列表 ，存放协议等一些文件';

alter table ma_coop_file add constraint FK_FK_coop_file foreign key (cid)
      references ma_cooperation (cid) on delete restrict on update restrict;
      
/*==============================================================*/
/* Table: ma_resource                                           */
/*==============================================================*/
create table ma_resource
(
   resource_id          int not null auto_increment,
   cid                  smallint(6),
   create_time          datetime,
   resource_money       decimal(5,2),
   path                 varchar(100),
   flage                bit,
   test                 bit,
   primary key (resource_id)
);

alter table ma_resource comment '资源包列表';

alter table ma_resource add constraint FK_FK_cooper_resource foreign key (cid)
      references ma_cooperation (cid) on delete restrict on update restrict; 
     
/*==============================================================*/
/* Table: ma_res_income                                         */
/*==============================================================*/
create table ma_res_income
(
   income_id            int not null auto_increment,
   resource_id          int,
   create_time          datetime,
   stare_time           datetime,
   end_time             datetime,
   income_money         decimal(5,2),
   primary key (income_id)
);

alter table ma_res_income comment '资源收益表';

alter table ma_res_income add constraint FK_FK_resource_income foreign key (resource_id)
      references ma_resource (resource_id) on delete restrict on update restrict;

/*==============================================================*/
/* Table: ma_balance                                            */
/*==============================================================*/
create table ma_balance
(
   balance_id           int not null auto_increment,
   cid                  smallint(6),
   balance_money        decimal(5,2),
   balance_start_time   datetime,
   balance_end_time     datetime,
   create_time          datetime,
   primary key (balance_id)
);

alter table ma_balance comment '结算记录表';

alter table ma_balance add constraint FK_balance_cooper foreign key (cid)
      references ma_cooperation (cid) on delete restrict on update restrict;
                                               
/*==============================================================*/
/* Table: ma_user_pkg                                           */
/*==============================================================*/
create table ma_user_pkg
(
   user_id              int not null,
   pkg_number           char(10)
);

alter table ma_user_pkg add constraint FK_pkg_user foreign key (pkg_number)
      references ma_promotion_pkg (pkg_number) on delete restrict on update restrict;

alter table ma_user_pkg add constraint FK_user_pkg foreign key (user_id)
      references ma_user (id) on delete restrict on update restrict;
   
/*==============================================================*/
/* Table: ma_prom_coop_pkg                                      */
/*==============================================================*/
create table ma_prom_coop_pkg
(
   pkg_number           char(10),
   cid                  smallint(6)
);

alter table ma_prom_coop_pkg add constraint FK_prom_coop_pkg foreign key (pkg_number)
      references ma_promotion_pkg (pkg_number) on delete restrict on update restrict;

alter table ma_prom_coop_pkg add constraint FK_prom_pkg_coop foreign key (cid)
      references ma_cooperation (cid) on delete restrict on update restrict;
 
/*==============================================================*/
/* Table: ma_cooperation_log                                    */
/*==============================================================*/
create table ma_cooperation_log
(
   imei                 varchar(50) not null,
   pkg_number           char(10),
   version              char(10) not null,
   create_time          datetime not null,
   primary key (imei)
);

alter table ma_cooperation_log add constraint FK_pkg_log foreign key (pkg_number)
      references ma_promotion_pkg (pkg_number) on delete restrict on update restrict;
   
/*==============================================================*/
/* Table: ma_salesman_count_log                                 */
/*==============================================================*/
create table ma_salesman_count_log
(
   id                   smallint(6) not null auto_increment,
   username             varchar(20) not null,
   uid                  int(11) not null,
   pkg_number           char(10) not null,
   cid                  smallint(6) not null,
   c_name               varchar(20) not null,
   success_count        int default 0,
   start_time           datetime not null,
   end_time             datetime not null,
   create_time          datetime not null,
   primary key (id)
);      
                                   