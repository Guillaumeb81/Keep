

CREATE TABLE STORY(
  USERNAME VARCHAR(50),
  CONTENT VARCHAR(144)
);

create table users(
  username varchar(50) not null primary key,
  password varchar(250) not null,
  enabled boolean not null
);

create table authorities (
  username varchar(50) not null,
  authority varchar(50) not null,
  constraint fk_authorities_users foreign key(username) references users(username)
);
-- create unique index ix_auth_username on authorities (username,authority);

-- create user
INSERT INTO users values('admin','$2a$10$YuMjGSDPz8PkHykYwdYbV.5WdA848RlAHDEQHjBgkpKOlxzDXMcpe',true);
insert into authorities values('admin','ADMIN');

