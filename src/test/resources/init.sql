drop table if exists users;
create table users (
    id bigint not null,
    f_name varchar(255),
    l_name varchar(255),
    date_of_birth date
);
INSERT INTO users (id, f_name, l_name, date_of_birth) VALUES (1, 'Roger', 'Clari', '2000-11-4');
INSERT INTO users (id, f_name, l_name, date_of_birth) VALUES (2, 'Pagani', 'Mery', '1980-7-7');
INSERT INTO users (id, f_name, l_name, date_of_birth) VALUES (4, 'Marta', 'Greco', '1987-1-15');