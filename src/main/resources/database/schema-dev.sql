DROP DATABASE IF EXISTS dev_films_db;
CREATE DATABASE dev_films_db;


USE mysql;

DROP USER IF EXISTS 'dev-film-user'@'localhost';
DROP USER IF EXISTS 'dev-film-user'@'%';

CREATE USER 'dev-film-user'@'localhost' IDENTIFIED BY 'dev';
CREATE USER 'dev-film-user'@'%' IDENTIFIED BY 'dev';

GRANT ALL ON dev_films_db.* TO 'dev-film-user'@'localhost' WITH GRANT OPTION;
GRANT ALL ON dev_films_db.* TO 'dev-film-user'@'%' WITH GRANT OPTION;


use dev_films_db;


CREATE TABLE Film (
  id bigint AUTO_INCREMENT PRIMARY KEY NOT NULL
  , timestamp DATETIME
  , title varchar(255) NOT NULL
  , releaseDate DATE NOT NULL
  , imdbRating numeric(15,2)
  , director varchar(50)
  , genre varchar(50)
  , isAwardWinning boolean default false NOT NULL
);

CREATE TABLE Film_Audit (
  auditDateTime DATETIME
  , auditType int
  , id bigint
  , timestamp DATETIME
  , releaseDate DATE
  , imdbRating numeric(15,2)
  , director varchar(50)
  , genre varchar(50)
  , isAwardWinning boolean
);

CREATE TRIGGER update_Film
AFTER UPDATE ON Film
FOR EACH ROW
  INSERT INTO Film_Audit
    (auditDateTime, auditType, id, timestamp, releaseDate, imdbRating, director, genre, isAwardWinning)
  VALUES
    (SYSDATE(), 1, OLD.id, OLD.timestamp, OLD.releaseDate, OLD.imdbRating, OLD.director, OLD.genre, OLD.isAwardWinning);

CREATE TRIGGER delete_Film
AFTER DELETE ON Film
FOR EACH ROW
  INSERT INTO Film_Audit
    (auditDateTime, auditType, id, timestamp, releaseDate, imdbRating, director, genre, isAwardWinning)
  VALUES
    (SYSDATE(), 2, OLD.id, OLD.timestamp, OLD.releaseDate, OLD.imdbRating, OLD.director, OLD.genre, OLD.isAwardWinning);

-----------------------------------------------------------------

-- For Spring Security
-- Usually need Authorities table too, but this app doesn't use it
CREATE TABLE User (
  id bigint AUTO_INCREMENT PRIMARY KEY NOT NULL
  , email varchar(50) NOT NULL
  , password varchar(100) NOT NULL
--  , enabled boolean default false NOT NULL
);