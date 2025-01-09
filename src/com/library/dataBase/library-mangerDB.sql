CREATE DATABASE IF NOT EXISTS library_db;

-- Use the database
USE library_db;
-- Create the admin_users table
CREATE TABLE IF NOT EXISTS admin_users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL
);

USE library_db;
-- Insert admin user with plain text (not recommended in production)
INSERT INTO admin_users(username,password) VALUES ('admin', '123');

USE library_db;
CREATE TABLE librarain (
    librarain_id int auto_increment PRIMARY KEY,
    username     varchar(50) UNIQUE,
    password     varchar(50) NOT NULL,
    name         varchar(50) NOT NULL,
    adress       varchar(50) NOT NULL,
    city         varchar(50) NOT NULL,
    contact_name varchar(50) NOT NULL
);

USE library_db;
CREATE TABLE books (
    bookColl_id   int auto_increment primary KEY,
    book_name varchar(50) NOT NULL,
    author    varchar(50) NOT NULL,
    adress    varchar(50) NOT NULL,
    publisher varchar(50) NOT NULL,
    quantity  varchar(15) NOT NULL
);

USE library_db;
CREATE TABLE IF NOT EXISTS issue_books(
    book_id    int ,
    student_id      int PRIMARY KEY,
    student_name    varchar(50) NOT NULL,
    student_contact varchar(50) NOT NULL
);

    