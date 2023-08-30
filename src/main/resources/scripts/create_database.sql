-- !!!RUN ONCE!!!
drop database if exists `student_directory`;

-- Create Database
create database `student_directory`;

-- Create tables
create table student_directory.students(
	student_id int NOT NULL PRIMARY KEY,
    first_name varchar(64) NOT NULL,
    last_name varchar(64) NOT NULL,
    cohort varchar(64) NOT NULL
);
create table student_directory.assignmnents(
	 assignment_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
     max_score int NOT NULL,
     title varchar(64) NOT NULL,
     due_date date NOT NULL
);
create table student_directory.graded_assignments(
	graded_assignment_id int NOT NULL auto_increment PRIMARY KEY,
    assignment_id int,
    student_id int,
    received_score float NOT NULL,
    FOREIGN KEY (assignment_id) REFERENCES assignmnents(assignment_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id)
);
