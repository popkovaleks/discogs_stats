-- liquibase formatted sql

-- changeset popkovalex:005-add-column-salt
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.columns where table_name = 'users' and column_name = 'salt';
alter table users add column salt varchar(16) not null;