SET SEARCH_PATH TO oblig3;

DROP TABLE IF EXISTS ansatt;

CREATE TABLE ansatt(
	aid int GENERATED ALWAYS AS IDENTITY,
	brukernavn varchar(4) NOT NULL UNIQUE,
	fornavn varchar NOT NULL,
	etternavn varchar NOT NULL,
	ansettelsesdato date NOT NULL,
	stilling varchar NOT NULL,
	maanedslonn numeric NOT NULL,
	PRIMARY KEY (aid)
);

CREATE TABLE avdeling(
    avdelignsid int GENERATED ALWAYS AS IDENTITY,
    navn varchar UNIQUE NOT NULL,
    leder varchar NOT NULL,
    PRIMARY KEY (avdelignsid),
    FOREIGN KEY (leder) REFERENCES ansatt(aid)
);

-- En-til-mange
CREATE TABLE horertilavdeling(
  ansattid int,
  avdelingnavn varchar,
  CONSTRAINT deltakelsePK PRIMARY KEY (ansattid,avdelingnavn),
  CONSTRAINT ansattFK FOREIGN KEY (ansattid) REFERENCES ansatt(aid)
);

INSERT INTO ansatt(brukernavn, fornavn, etternavn, ansettelsesdato, stilling, maanedslonn)
VALUES
	('Per', 'Peter', 'Petterson', '2020-12-10', 'Leder', 20.00),
	('Pål', 'Polly', 'Crumblecookie', '2019-10-13', 'Nestleder', 15.15),
	('Pil', 'Pontius', 'Pilatus', '2022-04-24', 'Medlem', 10.20)
;

SELECT * FROM ansatt;