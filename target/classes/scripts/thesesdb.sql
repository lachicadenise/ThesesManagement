-- database script for MySQL

create database if not exists thesesdb;
use thesesdb;

drop table if exists advisers;
create table advisers(
	id int not null auto_increment,
	title varchar(32) default '',
	lastname varchar(32) not null default '',
	firstname varchar(32) not null default '',
	middlename varchar(32) default '',
	isDeleted tinyint(1) not null default 0,
	creationTime timestamp not null default current_timestamp,
	primary key(id)
);

drop table if exists proponents;
create table proponents(
	id int not null auto_increment,
	thesisId int not null default 0,
	title varchar(32) default '',
	lastname varchar(32) not null default '',
	firstname varchar(32) not null default '',
	middlename varchar(32) default '',
	isDeleted tinyint(1) not null default 0,
	creationTime timestamp not null default current_timestamp,
	primary key(id)
);

drop table if exists tags;
create table tags(
	id int not null auto_increment,
	name varchar(32) not null default '',
	isDeleted tinyint(1) not null default 0,
	creationTime timestamp not null default current_timestamp,
	primary key(id),
	unique key(name)
);

drop table if exists theses;
create table theses(
	id int not null auto_increment,
	title varchar(32) not null default '',
	summary text default '',
	isDeleted tinyint(1) not null default 0,
	creationTime timestamp not null default current_timestamp,
	primary key(id)
);

drop table if exists userAccounts;
create table userAccounts(
	id int not null auto_increment,
	username varchar(32) not null default '',
	password varchar(32) default '',
	lastname varchar(32) not null default '',
	firstname varchar(32) not null default '',
	middlename varchar(32) default '',
	isDeleted tinyint(1) not null default 0,
	creationTime timestamp not null default current_timestamp,
	primary key(id),
	unique key(username)
);
insert into userAccounts(id, username, password) values(1, 'admin', 'password01');

-- create linking table for theses and tags
drop table if exists theses_tags;
create table theses_tags(
	id int not null auto_increment,
	thesisId int not null,
	tagId int not null,
	score int not null default 1,
	primary key(id),
	unique key(thesisId, tagId)
);

-- create linking table for theses and advisers
drop table if exists theses_advisers;
create table theses_advisers(
	id int not null auto_increment,
	thesisId int not null,
	adviserId int not null,
	primary key(id),
	unique key(thesisId, adviserId)
);
