BEGIN TRANSACTION;

drop table if exists favorites;

create table favorites (
id serial PRIMARY KEY
,user_id integer NOT NULL
,restaurant_name varchar(255) NOT NULL
,restaurant_location varchar(255) NOT NULL
,hours varchar(255) NOT NULL
,rating varchar(20)
,cuisine varchar(255)
,price_range varchar(255)
,image_url varchar
, UNIQUE(user_id, restaurant_location)
);

ALTER TABLE favorites ADD FOREIGN KEY (user_id) REFERENCES users(id);

COMMIT;