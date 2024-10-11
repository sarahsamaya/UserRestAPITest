create table users(

                      id bigint not null auto_increment,
                      username varchar(100) not null,
                      first_name varchar(100) not null,
                      last_name varchar(100) not null,
                      email varchar(100) not null unique,
                      phone_number varchar(100) not null unique,

                      primary key(id)
);