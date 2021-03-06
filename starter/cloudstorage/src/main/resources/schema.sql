CREATE TABLE IF NOT EXISTS USERS (
  userid INT PRIMARY KEY auto_increment,
  username VARCHAR(20),
  salt VARCHAR,
  password VARCHAR,
  firstname VARCHAR(20),
  lastname VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS NOTES (
    noteid INT PRIMARY KEY auto_increment,
    notetitle VARCHAR(20),
    notedescription VARCHAR (1000),
    userid INT,
    foreign key (userid) references USERS(userid)
);

CREATE TABLE IF NOT EXISTS FILES (
    fileId INT PRIMARY KEY auto_increment,
    filename VARCHAR,
    contenttype VARCHAR,
    filesize VARCHAR,
    userid INT,
    filedata BLOB,
    foreign key (userid) references USERS(userid)
);

CREATE TABLE IF NOT EXISTS CREDENTIALS (
    credentialid INT PRIMARY KEY auto_increment,
    url VARCHAR(100),
    username VARCHAR (30),
    key VARCHAR,
    password VARCHAR,
    userid INT,
    foreign key (userid) references USERS(userid)
);

--INSERT INTO USERS (username, salt, password, firstname, lastname)
--VALUES('crised','7CemDn6zw2J1sQ7kUX1piQ==', 'n97FCJAX2rWyq51+3oeANA==', 'cristian', 'edwards');
--
--INSERT INTO FILES (filename, contenttype, filesize, userid, filedata)
--VALUES ('cat.txt', 'txt', '2', 1, '1122');
--
--INSERT INTO NOTES (notetitle, notedescription, userid)
--VALUES ('Important note', 'Take the trash can!', 1);