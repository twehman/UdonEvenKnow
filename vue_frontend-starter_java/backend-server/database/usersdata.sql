BEGIN TRANSACTION;

drop table if exists users_data;

create table users_data (
users_id serial PRIMARY KEY
,id integer NOT NULL
,first_name varchar NOT NULL
,last_name varchar NOT NULL
,address varchar NOT NULL
,city varchar NOT NULL
,state char(2) NOT NULL
,zip_code integer NOT NULL
);

ALTER TABLE users_data ADD FOREIGN KEY (id) REFERENCES users(id);

COMMIT;