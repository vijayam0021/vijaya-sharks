--Db Setup
create database  IF NOT EXISTS msharks;

use msharks;

CREATE USER 'devdb'@'localhost' IDENTIFIED BY  'devdbpass1';

GRANT CREATE, ALTER, DROP, INSERT, UPDATE, DELETE, SELECT ON msharks.* TO 'devdb'@'localhost';

--Application db script
drop table if exists user;

create table user(
    username varchar(255) unique,
    user_password varchar(255) unique,
    email varchar(255),
    primary key (username)
)engine=InnoDB;