-- !!!RUN ONCE!!!

-- DROP USER springstudent if exists
drop user if exists `springstudent`@`localhost`;

-- create user
create user `springstudent`@`localhost` identified by 'springstudent';

-- grant priv
grant all privileges on *.* to `springstudent`@`localhost`;

