drop schema if exists `computer-database-db`;
create schema if not exists `computer-database-db`;
use `computer-database-db`;

drop table if exists computer;
drop table if exists company;
drop table if exists authorities;
drop table if exists users;

create table company (
  id                        bigint not null auto_increment,
  name                      varchar(255),
  constraint pk_company primary key (id))
;

create table computer (
  id                        bigint not null auto_increment,
  name                      varchar(255),
  introduced                timestamp NULL,
  discontinued              timestamp NULL,
  company_id                bigint default NULL,
  constraint pk_computer primary key (id))
;

create table users(
  username                  varchar(100) not null primary key,
  password                  varchar(100) not null)
;

create table authorities (
  user_role_id              bigint not null auto_increment,
  username                  varchar(100) not null ,
  role                      varchar(100) not null,
  constraint                pk_authorities primary key (user_role_id))
;

alter table computer add constraint fk_computer_company_1 foreign key (company_id) references company (id) on delete restrict on update restrict;
create index ix_computer_company_1 on computer (company_id);

alter table authorities add constraint fk_authorities_users foreign key(username) references users(username) on delete CASCADE on update restrict;
create index ix_role_username on authorities (username,role);
