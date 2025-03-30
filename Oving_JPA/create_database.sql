SET SEARCH_PATH TO oblig3;

DROP TABLE IF EXISTS ansatt CASCADE;
DROP TABLE IF EXISTS avdeling CASCADE;
DROP TABLE IF EXISTS prosjekt CASCADE;

CREATE TABLE avdeling
(
    avdelingsid int GENERATED ALWAYS AS IDENTITY,
    navn        varchar UNIQUE,
    leder       int,
    PRIMARY KEY (avdelingsid)
);

CREATE TABLE prosjekt
(
    prosjektid int GENERATED ALWAYS AS IDENTITY,
    prosjektnavn varchar NOT NULL UNIQUE,
    PRIMARY KEY (prosjektid)
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
    prosjektid int NOT NULL,
    PRIMARY KEY (aid),
    FOREIGN KEY (avdelingsid) REFERENCES avdeling(avdelingsid),
    FOREIGN KEY (prosjektid) REFERENCES prosjekt(prosjektid)
);


INSERT INTO avdeling(navn, leder)
VALUES ('IT', NULL),
       ('HR', NULL),
       ('Finance', NULL);

INSERT INTO prosjekt (prosjektnavn)
VALUES
    ('Shell'),
    ('Statoil'),
    ('Laks');


INSERT INTO ansatt (brukernavn, fornavn, etternavn, ansettelsesdato, stilling, maanedslonn, avdelingsid, prosjektid)
VALUES
    ('Per', 'Peter', 'Petterson', '2020-12-10', 'Leder', 20.00, 1,1),
    ('Pål', 'Polly', 'Crumblecookie', '2019-10-13', 'Nestleder', 15.15, 1,2),
    ('Pil', 'Pontius', 'Pilatus', '2022-04-24', 'Medlem', 10.20, 2,3),
    ('Ann', 'Anna', 'Andersen', '2021-06-01', 'Medlem', 12.50, 2,1),
    ('Jon', 'Jonathan', 'Jensen', '2018-03-15', 'Senior Medlem', 18.00, 1,2),
    ('Eva', 'Evangeline', 'Evans', '2023-01-20', 'Medlem', 11.75, 2,3),
    ('Leo', 'Leonard', 'Larsson', '2020-08-08', 'Medlem', 13.25, 3,1),
    ('Siv', 'Sivert', 'Svensson', '2017-05-30', 'Nestleder', 20.00, 2,2),
    ('Tom', 'Tommy', 'Thompson', '2019-11-25', 'Leder', 22.50, 1,3),
    ('Liz', 'Elizabeth', 'Lund', '2021-09-10', 'Medlem', 12.00, 3,2);


UPDATE avdeling SET leder = 1 WHERE navn = 'IT';
UPDATE avdeling SET leder = 2 WHERE navn = 'HR';
UPDATE avdeling SET leder = 9 WHERE navn = 'Finance';


SELECT * FROM ansatt;
SELECT * FROM avdeling;