
-- SPRING SECURITY TEST TABLES --
-- Version 0.1 --


SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;
SET search_path = sst, pg_catalog;
SET default_tablespace = ''; 
SET default_with_oids = false;

-- 
--
-- TABLE WISH
--
--

CREATE TABLE sst.wish (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    wish character varying,
    creation_date timestamp DEFAULT now(),
    update_date timestamp DEFAULT now()
);

ALTER TABLE sst.wish OWNER TO sst_user;
CREATE SEQUENCE sst.wish_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
ALTER TABLE sst.wish_id_seq OWNER TO sst_user;
ALTER SEQUENCE sst.wish_id_seq OWNED BY wish.id;
ALTER TABLE ONLY sst.wish ALTER COLUMN id SET DEFAULT nextval('wish_id_seq'::regclass);
ALTER TABLE ONLY sst.wish ADD CONSTRAINT pk_wish PRIMARY KEY (id);

-- 
--
-- TABLE USER_CREDENTIALS
--
--

CREATE TABLE sst.user_credentials (
    id bigint NOT NULL,
    username character varying,
    password character varying,
    creation_date timestamp DEFAULT now()
);

ALTER TABLE sst.user_credentials OWNER TO sst_user;
CREATE SEQUENCE sst.user_credentials_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
ALTER TABLE sst.user_credentials_id_seq OWNER TO sst_user;
ALTER SEQUENCE sst.user_credentials_id_seq OWNED BY user_credentials.id;
ALTER TABLE ONLY sst.user_credentials ALTER COLUMN id SET DEFAULT nextval('user_credentials_id_seq'::regclass);
ALTER TABLE ONLY sst.user_credentials ADD CONSTRAINT pk_user_credentials PRIMARY KEY (id);
ALTER TABLE ONLY sst.user_credentials ADD CONSTRAINT u_username UNIQUE (username);

-- 
--
-- TABLE ROLE
--
--

CREATE TYPE user_role AS ENUM ('ADMIN', 'COMMON');

CREATE TABLE sst.role (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    role user_role 
);

ALTER TABLE sst.role OWNER TO sst_user;
CREATE SEQUENCE sst.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
ALTER TABLE sst.role_id_seq OWNER TO sst_user;
ALTER SEQUENCE sst.role_id_seq OWNED BY role.id;
ALTER TABLE ONLY sst.role ALTER COLUMN id SET DEFAULT nextval('role_id_seq'::regclass);
ALTER TABLE ONLY sst.role ADD CONSTRAINT pk_role PRIMARY KEY (id);

--
--
-- FOREIGN KEYS
--
--

ALTER TABLE ONLY sst.role
    ADD CONSTRAINT fk_role_user FOREIGN KEY (user_id) REFERENCES sst.user_credentials(id);
ALTER TABLE ONLY sst.wish
    ADD CONSTRAINT fk_wish_user FOREIGN KEY (user_id) REFERENCES sst.user_credentials(id);

