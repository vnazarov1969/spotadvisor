-- This SQL contains a "create table" that can be used to create a table that JdbcUsersConnectionRepository can persist
-- connection in. It is, however, not to be assumed to be production-ready, all-purpose SQL. It is merely representative
-- of the kind of table that JdbcUsersConnectionRepository works with. The table and column names, as well as the general
-- column types, are what is important. Specific column types and sizes that work may vary across database vendors and
-- the required sizes may vary across API providers.
drop index if exists UserConnectionRank;
drop table if exists UserConnection;

create table UserConnection (userId varchar(255) not null,
	providerId varchar(255) not null,
	providerUserId varchar(255),
	rank int not null,
	displayName varchar(255),
	profileUrl varchar(512),
	imageUrl varchar(512),
	accessToken varchar(512) not null,
	secret varchar(512),
	refreshToken varchar(512),
	expireTime bigint,
	primary key (userId, providerId, providerUserId));
create unique index UserConnectionRank on UserConnection(userId, providerId, rank);

drop table if exists users;
create table users (
    id varchar(255) not null,
	user_name varchar(255),
	created timestamp  DEFAULT now(),
	primary key (id));

drop table if exists city;
create table city (
    id serial,
    country_id varchar(2) not null,
    name varchar(64),
    name_local varchar(64),
    name_short varchar(64),
    primary key (id));

drop table if exists spot;
create table spot (
    id serial,
	city_id integer not null,
	name varchar(64),
    name_local varchar(64),
	latitude  decimal(12,9),
	longitude decimal(12,9),
	status varchar(10) default 'NEW',
	created timestamp DEFAULT now(),
	primary key (id));


drop table if exists workout;
create table workout (
    id serial,
	user_id varchar(255),
	start_time timestamp DEFAULT now(),
	end_time timestamp ,
	spot_id integer not null,
	fun_rate integer,
	condition_rate integer,
--	fun_rate integer,
--	wind_min_ms integer,
--	wind_max_ms integer,
--	temp_air integer,
--	temp_water integer,
	notes varchar(4096),
	type varchar(16),
	primary key (id));

drop table if exists stuff;
create table stuff(
    id serial,
	user_id varchar(255),
	start_time timestamp,
	end_time timestamp,
	name varchar(64),
	model varchar(64),
	model_brand varchar(64),
	model_year integer,
    model_size varchar(10),
	notes varchar(4096),
	stuff_type_id integer,
	primary key (id));

drop table if exists stuff_type;
create table stuff_type (
    id serial,
	name varchar(10),
    size_unit varchar(10),
	size_validate varchar(64),
	size_cast varchar(64),
	primary key (id));

drop table if exists workout_stuff;
create table workout_stuff (
	workout_id integer not null ,
	stuff_id integer not null,
    created timestamp DEFAULT now(),
    primary key (workout_id, stuff_id));

drop table if exists country;
create table country (
    id varchar(2) not null,
    name varchar(64),
    name_local varchar(64),
    primary key (id));

