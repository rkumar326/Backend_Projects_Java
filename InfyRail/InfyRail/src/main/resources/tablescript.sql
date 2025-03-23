drop database if exists infyrail;
create database infyrail;
use infyrail;

CREATE TABLE Route (
    id INT PRIMARY KEY,
    source VARCHAR(255),
    destination VARCHAR(255)
);

CREATE TABLE Train (
    id INT PRIMARY KEY,
    trainName VARCHAR(255),
    arrivalTime VARCHAR(255),
    departureTime VARCHAR(255),
    fare DOUBLE
);

ALTER TABLE Route
ADD COLUMN train_id INT,
ADD CONSTRAINT fk_train_id
FOREIGN KEY (train_id)
REFERENCES Train(id);

insert into route values (101,"Bangalore","Chennai");
insert into route values (102,"Pune","Mysore");
insert into route values (103,"Delhi","Mumbai");

insert into train values(16021,"13:00:00", "13:30:00", 834.45, "Kaveri Express",101);
insert into train values(12608, "18:35:00", "18:45:00", 980.98, "Lalbagh SF Express", 101);
insert into train values(12640,"07:45:00", "08:00:00", 1010.65,"Brindavan Express",101);
insert into train values(19968,"16:40:00", "16:50:00", 1915.05,"Palace Queen Humsafar Express",102);
insert into train values(12781,"14:00:00", "14:15:00", 1100.0,"Mysuru Swarna Jayanti SF Express",102);
insert into train values(12910,"07:00:00", "07:15:00", 980.86,"Garib Rath Express",103);
insert into train values(12952, "13:55:00", "14:10:00", 870.98,"Rajdhani Express",103);

commit;

select * from route;
select * from train;




