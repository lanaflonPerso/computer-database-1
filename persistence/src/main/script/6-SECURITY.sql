use `computer-database-db-test`;

drop table if exists authorities;
drop table if exists users;

create table users(
      username                  varchar(100) not null primary key,
      password                  varchar(100) not null,
      enabled                   boolean not null DEFAULT TRUE );

create table authorities (
      user_role_id              bigint not null auto_increment,
      username                  varchar(100) not null ,
      role                      varchar(100) not null,
      constraint                pk_authorities primary key (user_role_id));

alter table authorities add constraint fk_authorities_users foreign key(username) references users(username) on delete CASCADE on update restrict;
create index ix_role_username on authorities (username,role);


INSERT INTO users(username,password,enabled) VALUES ('admin','$2a$10$cwXZKDV44c3cFl0ac0QU0OyIMMkSuUC9gEifqDuwzmc8QSAVbhatS', TRUE);
INSERT INTO users(username,password,enabled) VALUES ('user','$2a$10$H0eNB7HcZI0V049EetfGxu4hK07vZLCkaZkcQOv6/aZCGLI7TFSQW', TRUE);
INSERT INTO users(username,password,enabled) VALUES ('all','$2a$10$UA.m4MEeB5dFfwtZZO.0B.a.dLJuzuB05gclWOjS/a9UiZ8dZI/2S', TRUE);


INSERT INTO authorities (username, role) VALUES ('admin', 'ROLE_USER');
INSERT INTO authorities (username, role) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO authorities (username, role) VALUES ('user', 'ROLE_USER');
INSERT INTO authorities (username, role) VALUES ('all', 'ROLE_USER');
INSERT INTO authorities (username, role) VALUES ('all', 'ROLE_ADMIN');
INSERT INTO authorities (username, role) VALUES ('all', 'ROLE_SUPER_ADMIN');