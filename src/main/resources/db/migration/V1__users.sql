CREATE SCHEMA core;

ALTER SCHEMA core OWNER TO postgres;

CREATE TABLE core.users (
	id integer NOT NULL GENERATED ALWAYS AS IDENTITY,
	first_name varchar(400) NOT NULL,
	last_name varchar(400) NOT NULL,
	password varchar(400) NOT NULL,
	email varchar(400) NOT NULL,
	address varchar(4000) NULL,
	phone varchar(40) NULL,
	photo_id integer NULL,
	city varchar(4000) NULL,
	registration_date date NOT NULL,
	secret bytea NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id),
	CONSTRAINT email_uq UNIQUE (email)
);

CREATE TABLE core.attachments(
	 id integer NOT NULL GENERATED ALWAYS AS IDENTITY,
	 filename varchar(4000) NOT NULL,
	 data BYTEA NOT NULL,
	 type varchar(50) NOT NULL,
	 CONSTRAINT attachments_pk PRIMARY KEY (id)
);

ALTER TABLE core.users ADD CONSTRAINT fk_users_attachments FOREIGN KEY (photo_id)
	REFERENCES core.attachments (id) MATCH FULL
	ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;

ALTER TABLE core.attachments OWNER TO postgres;
ALTER TABLE core.users OWNER TO postgres;