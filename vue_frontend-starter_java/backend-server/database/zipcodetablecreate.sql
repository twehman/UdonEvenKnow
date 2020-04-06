BEGIN TRANSACTION;

drop table if exists zip_codes;

create table zip_codes 
(zip int,
city varchar, 
state char(2),  
latitude double precision, 
longitude double precision, 
timezone int, 
flag int, 
geopoint varchar);

COMMIT;

BEGIN TRANSACTION;
copy zip_codes from '/Users/student/workspace/java-final-capstone-team-restaurant-tinder/vue_frontend-starter_java/backend-server/us-zip-code-latitude-and-longitude.csv' DELIMITERS ';' CSV;
COMMIT;