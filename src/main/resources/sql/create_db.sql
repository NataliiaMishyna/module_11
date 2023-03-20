CREATE TABLE IF NOT EXISTS client(
	ID INT  PRIMARY KEY,
	NAME VARCHAR(200) NOT NULL,
	    CHECK(LENGTH(NAME)>2)
);

CREATE TABLE IF NOT EXISTS PLANET(
	ID VARCHAR PRIMARY KEY,
	NAME VARCHAR(500) NOT NULL
	    CHECK(LENGTH(NAME)>1)
	CONSTRAINT id_Only_Latin_and_Numbers
       CHECK (id NOT LIKE '%[^A-Z0-9]%'),
    CONSTRAINT id_upper
    check (upper(id) = id)
);

CREATE TABLE TICKET(
    ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    CREATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CLIENT_ID INT,
    FROM_PLANET_ID VARCHAR,
    TO_PLANET_ID VARCHAR,
    PRIMARY KEY(id)
);