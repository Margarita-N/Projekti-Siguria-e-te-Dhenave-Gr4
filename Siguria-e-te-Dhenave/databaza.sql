create database siguria;

create table persoruesit(
username varchar(30),
salt varchar(32),
hashedPassword varchar(100),
primary key(username));

select * from perdoruesit
