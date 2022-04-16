show databases;
use usermanagement;
show tables;

Insert Into country_master (COUNTRY_ID, COUNTRY_CODE, COUNTRY_NAME) Values(1,'+91','India');
Insert Into country_master (COUNTRY_ID, COUNTRY_CODE, COUNTRY_NAME) Values(2,'+1','USA');


Insert Into states_master (STATE_ID, COUNTRY_ID, STATE_NAME) Values(1,1,'Andhra Pradesh');
Insert Into states_master (STATE_ID, COUNTRY_ID, STATE_NAME) Values(2,1,'Karnataka');
Insert Into states_master (STATE_ID, COUNTRY_ID, STATE_NAME) Values(3,2,'New Jersy');
insert into states_master (STATE_ID, COUNTRY_ID, STATE_NAME) values(4,2,'Ohio');


Insert Into cities_master (CITY_ID, CITY_NAME, STATE_ID) Values(1,'Vizag',1);
Insert Into cities_master (CITY_ID, CITY_NAME, STATE_ID) Values(2,'Guntur',1);
Insert Into cities_master (CITY_ID, CITY_NAME, STATE_ID) Values(3,'Banglore',2);
Insert Into cities_master (CITY_ID, CITY_NAME, STATE_ID) Values(4,'Mysore',2);
Insert Into cities_master (CITY_ID, CITY_NAME, STATE_ID) Values(5,'Maywood',3);
Insert Into cities_master (CITY_ID, CITY_NAME, STATE_ID) Values(6,'Westwood',3);
Insert Into cities_master (CITY_ID, CITY_NAME, STATE_ID) Values(7,'Oakwood',4);
Insert Into cities_master (CITY_ID, CITY_NAME, STATE_ID) Values(8,'Cuyahoga County',4);

