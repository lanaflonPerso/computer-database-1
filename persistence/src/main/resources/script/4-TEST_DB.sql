drop schema if exists `computer-database-db-test`;
  create schema if not exists `computer-database-db-test`;
  use `computer-database-db-test`;

  drop table if exists computer;
  drop table if exists company;

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

  alter table computer add constraint fk_computer_company_1 foreign key (company_id) references company (id) on delete restrict on update restrict;
  create index ix_computer_company_1 on computer (company_id);



insert into company (id,name) values (  1,'Apple Inc.');
insert into company (id,name) values (  2,'Thinking Machines');
insert into company (id,name) values (  3,'RCA');
insert into company (id,name) values (  4,'Netronics');
insert into company (id,name) values (  5,'Tandy Corporation');
insert into company (id,name) values (  6,'Commodore International');
insert into company (id,name) values (  7,'MOS Technology');
insert into company (id,name) values (  8,'Micro Instrumentation and Telemetry Systems');
insert into company (id,name) values (  9,'IMS Associates, Inc.');
insert into company (id,name) values ( 10,'Digital Equipment Corporation');
insert into company (id,name) values ( 11,'Lincoln Laboratory');
insert into company (id,name) values ( 12,'Moore School of Electrical Engineering');
insert into company (id,name) values ( 13,'IBM');
insert into company (id,name) values ( 14,'Amiga Corporation');
insert into company (id,name) values ( 15,'Canon');
insert into company (id,name) values ( 16,'Nokia');
insert into company (id,name) values ( 17,'Sony');
insert into company (id,name) values ( 18,'OQO');
insert into company (id,name) values ( 19,'NeXT');    
insert into company (id,name) values ( 20,'Atari');
insert into company (id,name) values ( 22,'Acorn computer');
insert into company (id,name) values ( 23,'Timex Sinclair');
insert into company (id,name) values ( 24,'Nintendo');
insert into company (id,name) values ( 25,'Sinclair Research Ltd');
insert into company (id,name) values ( 26,'Xerox');
insert into company (id,name) values ( 27,'Hewlett-Packard');
insert into company (id,name) values ( 28,'Zemmix');
insert into company (id,name) values ( 29,'ACVS');
insert into company (id,name) values ( 30,'Sanyo');
insert into company (id,name) values ( 31,'Cray');
insert into company (id,name) values ( 32,'Evans & Sutherland');    
insert into company (id,name) values ( 33,'E.S.R. Inc.');
insert into company (id,name) values ( 34,'OMRON');
insert into company (id,name) values ( 35,'BBN Technologies');
insert into company (id,name) values ( 36,'Lenovo Group');
insert into company (id,name) values ( 37,'ASUS');
insert into company (id,name) values ( 38,'Amstrad');
insert into company (id,name) values ( 39,'Sun Microsystems');
insert into company (id,name) values ( 40,'Texas Instruments');
insert into company (id,name) values ( 41,'HTC Corporation');
insert into company (id,name) values ( 42,'Research In Motion');
insert into company (id,name) values ( 43,'Samsung Electronics');

insert into computer (id,name,introduced,discontinued,company_id) values (  1,'MacBook Pro 15.4 inch',null,null,1);

