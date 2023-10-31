DROP  DATABASE IF EXISTS pr70;

CREATE DATABASE pr70;

USE pr70;

CREATE TABLE priorities (
    id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE categories (
    id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    color varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users (
    id int(11) NOT NULL AUTO_INCREMENT,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    admin boolean NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tasks (
    id int(11) NOT NULL AUTO_INCREMENT,
    user_id int(11) NOT NULL,
    category_id int(11) NOT NULL,
    name varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    start_date date NOT NULL,
    deadline date NOT NULL,
    priority_id varchar(255) NOT NULL,
    completed boolean NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

INSERT INTO priorities (name) VALUES ('Low');
INSERT INTO priorities (name) VALUES ('Medium');
INSERT INTO priorities (name) VALUES ('High');