use `computer-database-db-test`;

drop table if exists authorities;
drop table if exists users;

create table users(
      username                  varchar(100) not null primary key,
      password                  varchar(100) not null,
      enabled                   boolean not null DEFAULT TRUE );

create table authorities (
      username                  varchar(50) not null,
      authority                 varchar(50) not null,
      constraint                fk_authorities_users foreign key(username) references users(username));
      create unique index       ix_auth_username on authorities (username,authority);


INSERT INTO users(username,password,enabled) VALUES ('admin','$2a$10$cwXZKDV44c3cFl0ac0QU0OyIMMkSuUC9gEifqDuwzmc8QSAVbhatS', TRUE);
INSERT INTO users(username,password,enabled) VALUES ('user','$2a$10$H0eNB7HcZI0V049EetfGxu4hK07vZLCkaZkcQOv6/aZCGLI7TFSQW', TRUE);

INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');