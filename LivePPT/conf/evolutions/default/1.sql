# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table ppt (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  owner_id                  varchar(255),
  constraint pk_ppt primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  password                  varchar(255),
  expires_in                bigint,
  access_token              varchar(255),
  constraint pk_user primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table ppt;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

