DROP DATABASE IF EXISTS customers_db;
DROP SCHEMA IF EXISTS customers_schema;
DROP USER IF EXISTS customers_user;
DROP USER IF EXISTS customers_apps;
CREATE USER customers_user WITH PASSWORD 'customers_user' CREATEDB;
CREATE USER customers_apps WITH PASSWORD 'customers_apps';
CREATE DATABASE customers_db WITH OWNER = customers_user;

--- execute using customers_user
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE SCHEMA customers_user AUTHORIZATION customers_user;
ALTER USER customers_user SET search_path to 'customers_user';
GRANT ALL ON SCHEMA "customers_user" TO customers_user;
GRANT ALL ON SCHEMA "customers_user" TO customers_apps;