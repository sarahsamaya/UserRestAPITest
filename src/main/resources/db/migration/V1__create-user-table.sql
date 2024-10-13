create table users(

                      id bigint not null auto_increment,
                      username varchar(100) not null unique,
                      first_name varchar(100) not null,
                      last_name varchar(100) not null,
                      email varchar(100) not null,
                      phone_number varchar(100) not null,

                      primary key(id)
);