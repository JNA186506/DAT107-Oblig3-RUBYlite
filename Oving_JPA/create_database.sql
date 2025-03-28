SET SEARCH_PATH TO oblig3;

DROP TABLE IF EXISTS ansatt CASCADE;
DROP TABLE IF EXISTS avdeling CASCADE;

CREATE TABLE avdeling
(
    avdelingsid int GENERATED ALWAYS AS IDENTITY,
    navn        varchar UNIQUE NOT NULL,
    leder       int            NOT NULL,
    PRIMARY KEY (avdelingsid)
);

CREATE TABLE ansatt
(
    aid             int GENERATED ALWAYS AS IDENTITY,
    brukernavn      varchar(4) NOT NULL UNIQUE,
    fornavn         varchar    NOT NULL,
    etternavn       varchar    NOT NULL,
    ansettelsesdato date       NOT NULL,
    stilling        varchar    NOT NULL,
    maanedslonn     numeric    NOT NULL,
    avdelingsid     int        NOT NULL,
    PRIMARY KEY (aid),
    FOREIGN KEY (avdelingsid) REFERENCES avdeling(avdelingsid)
);

INSERT INTO avdeling(navn, leder)
VALUES ('IT', NULL),
       ('HR', NULL),
       ('Finance', NULL);

INSERT INTO ansatt (brukernavn, fornavn, etternavn, ansettelsesdato, stilling, maanedslonn, avdelingsid)
VALUES
    ('Per', 'Peter', 'Petterson', '2020-12-10', 'Leder', 20.00, 1),
    ('Pål', 'Polly', 'Crumblecookie', '2019-10-13', 'Nestleder', 15.15, 1),
    ('Pil', 'Pontius', 'Pilatus', '2022-04-24', 'Medlem', 10.20, 2),
    ('Ann', 'Anna', 'Andersen', '2021-06-01', 'Medlem', 12.50, 2),
    ('Jon', 'Jonathan', 'Jensen', '2018-03-15', 'Senior Medlem', 18.00, 1),
    ('Eva', 'Evangeline', 'Evans', '2023-01-20', 'Medlem', 11.75, 2),
    ('Leo', 'Leonard', 'Larsson', '2020-08-08', 'Medlem', 13.25, 3),
    ('Siv', 'Sivert', 'Svensson', '2017-05-30', 'Nestleder', 20.00, 2),
    ('Tom', 'Tommy', 'Thompson', '2019-11-25', 'Leder', 22.50, 1),
    ('Liz', 'Elizabeth', 'Lund', '2021-09-10', 'Medlem', 12.00, 3);


UPDATE avdeling SET leder = 1 WHERE navn = 'IT';
UPDATE avdeling SET leder = 2 WHERE navn = 'HR';
UPDATE avdeling SET leder = 9 WHERE navn = 'Finance';


SELECT * FROM ansatt;
SELECT * FROM avdeling;