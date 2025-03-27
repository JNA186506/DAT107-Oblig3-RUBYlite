SET
SEARCH_PATH TO oblig3;

DROP TABLE IF EXISTS horertilavdeling;
DROP TABLE IF EXISTS avdeling;
DROP TABLE IF EXISTS ansatt;

CREATE TABLE ansatt
(
    aid             int GENERATED ALWAYS AS IDENTITY,
    brukernavn      varchar(4) NOT NULL UNIQUE,
    fornavn         varchar    NOT NULL,
    etternavn       varchar    NOT NULL,
    ansettelsesdato date       NOT NULL,
    stilling        varchar    NOT NULL,
    maanedslonn     numeric    NOT NULL,
    PRIMARY KEY (aid)
);

CREATE TABLE avdeling
(
    avdelignsid int GENERATED ALWAYS AS IDENTITY,
    navn        varchar UNIQUE NOT NULL,
    leder       int            NOT NULL,
    PRIMARY KEY (avdelignsid),
    FOREIGN KEY (leder) REFERENCES ansatt (aid)
);

-- En-til-mange
CREATE TABLE horertilavdeling
(
    ansattid   int,
    avdelingid int,
    CONSTRAINT deltakelsePK PRIMARY KEY (ansattid, avdelingid),
    CONSTRAINT ansattFK FOREIGN KEY (ansattid) REFERENCES ansatt (aid),
    CONSTRAINT avdelingFK FOREIGN KEY (avdelingid) REFERENCES avdeling (avdelignsid)
);

INSERT INTO ansatt(brukernavn, fornavn, etternavn, ansettelsesdato, stilling, maanedslonn)
VALUES ('Per', 'Peter', 'Petterson', '2020-12-10', 'Leder', 20.00),
       ('Pål', 'Polly', 'Crumblecookie', '2019-10-13', 'Nestleder', 15.15),
       ('Pil', 'Pontius', 'Pilatus', '2022-04-24', 'Medlem', 10.20),
       ('Ann', 'Anna', 'Andersen', '2021-06-01', 'Medlem', 12.50),
       ('Jon', 'Jonathan', 'Jensen', '2018-03-15', 'Senior Medlem', 18.00),
       ('Eva', 'Evangeline', 'Evans', '2023-01-20', 'Medlem', 11.75),
       ('Leo', 'Leonard', 'Larsson', '2020-08-08', 'Medlem', 13.25),
       ('Siv', 'Sivert', 'Svensson', '2017-05-30', 'Nestleder', 16.00),
       ('Tom', 'Tommy', 'Thompson', '2019-11-25', 'Leder', 22.50),
       ('Liz', 'Elizabeth', 'Lund', '2021-09-10', 'Medlem', 12.00)
;

INSERT INTO avdeling(navn, leder)
VALUES ('IT', 1),
       ('HR', 2),
       ('Finance', 10)
;

INSERT INTO horertilavdeling(ansattid, avdelingid)
VALUES (1, 1), -- Per in IT
       (2, 2), -- Pål in HR
       (3, 3), -- Pil in Finance
       (4, 1),
       (5, 1),
       (6, 2),
       (7, 3),
       (8, 1),
       (9, 2),
       (10, 3)
;



SELECT *
FROM ansatt;
