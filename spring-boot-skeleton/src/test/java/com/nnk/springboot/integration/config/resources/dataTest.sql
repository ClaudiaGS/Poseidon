/*Setting up test DB*/
use demoTest;
drop table if exists BidList;
drop table if exists Trade;
drop table if exists CurvePoint;
drop table if exists Rating;
drop table if exists RuleName;
drop table if exists Users;

CREATE TABLE IF NOT EXISTS BidList (
  BidListId tinyint(4) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  account VARCHAR(30),
  type VARCHAR(30),
  bidQuantity DOUBLE,
  askQuantity DOUBLE,
  bid DOUBLE ,
  ask DOUBLE,
  benchmark VARCHAR(125),
  bidListDate TIMESTAMP,
  commentary VARCHAR(125),
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  book VARCHAR(125),
  creationName VARCHAR(125),
  creationDate TIMESTAMP ,
  revisionName VARCHAR(125),
  revisionDate TIMESTAMP ,
  dealName VARCHAR(125),
  dealType VARCHAR(125),
  sourceListId VARCHAR(125),
  side VARCHAR(125)
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Trade (
  TradeId tinyint(4) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  account VARCHAR(30),
  type VARCHAR(30),
  buyQuantity DOUBLE,
  sellQuantity DOUBLE,
  buyPrice DOUBLE ,
  sellPrice DOUBLE,
  tradeDate TIMESTAMP,
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  benchmark VARCHAR(125),
  book VARCHAR(125),
  creationName VARCHAR(125),
  creationDate TIMESTAMP ,
  revisionName VARCHAR(125),
  revisionDate TIMESTAMP ,
  dealName VARCHAR(125),
  dealType VARCHAR(125),
  sourceListId VARCHAR(125),
  side VARCHAR(125)
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS CurvePoint (
  Id tinyint(4) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  CurveId tinyint,
  asOfDate TIMESTAMP,
  term DOUBLE ,
  value DOUBLE ,
  creationDate TIMESTAMP
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Rating (
  Id tinyint(4) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  moodysRating VARCHAR(125),
  sandPRating VARCHAR(125),
  fitchRating VARCHAR(125),
  orderNumber tinyint
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS RuleName (
  Id tinyint(4) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(125),
  description VARCHAR(125),
  json VARCHAR(125),
  template VARCHAR(512),
  sqlStr VARCHAR(125),
  sqlPart VARCHAR(125)
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Users (
  ID tinyint(4) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  username VARCHAR(125),
  password VARCHAR(125),
  fullname VARCHAR(125),
  role VARCHAR(125)
)ENGINE=INNODB;

insert into Users(fullname, username, password, role) values('Administrator', 'admin', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'ADMIN');
insert into Users(fullname, username, password, role) values('User', 'user', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'USER');

insert into BidList(account, type, bidQuantity) values('account1','type1','100');
insert into BidList(account,type, bidQuantity) values('account2','type2','200');

insert into Trade(account,type,buyQuantity) values('account1','type1','1000');
insert into Trade(account,type,buyQuantity) values('account2','type2','2000');

insert into CurvePoint(CurveId,Term,Value) values('1','11','111');
insert into CurvePoint(CurveId, Term, Value) values('2','22','222');

insert into Rating(MoodysRating,SandPRating,FitchRating, orderNumber) values('moodys1','sandPR1','fitch1','1');
insert into Rating(MoodysRating,SandPRating,FitchRating, orderNumber) values('moodys2','sandPR2','fitch2','2');

insert into RuleName(Description, json, template, sqlStr, sqlPart) values('description1','json1','template1','sql1','sqlPart1');
insert into RuleName(Description, json, template, sqlStr, sqlPart) values('description2','json2','template2','sql2','sqlPart2');