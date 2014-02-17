CREATE DATABASE IF NOT EXISTS Registration;

USE Registration;

CREATE TABLE IF NOT EXISTS course
  (name VARCHAR(50),
   credits INTEGER);
   
CREATE TABLE IF NOT EXISTS schedule
  (name VARCHAR(100),
   offeringId INTEGER);
   
CREATE TABLE IF NOT EXISTS offering
  (id INTEGER,
   name VARCHAR(50),
   daysTimes VARCHAR(100));
   
CREATE TABLE IF NOT EXISTS student
  (id INTEGER,
   name VARCHAR(50));