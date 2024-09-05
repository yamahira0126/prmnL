drop table if exists account_section_table;
drop table if exists account_table;
drop table if exists budget_section_table;
drop table if exists budget_table;
drop table if exists section_table;
drop table if exists software_section_table;
drop table if exists software_table;
drop table if exists terminal_section_table;
drop table if exists terminal_table;

create table account_section_table(
                                      account_id integer primary key,
                                      section_id integer,
                                      account_section_exist integer default 1
);

create table account_table(
                              account_id serial primary key,
                              account_name varchar(32),
                              account_pass varchar(32),
                              account_mail_address varchar(32),
                              account_exist integer default 1
);

create table budget_section_table(
                                     budget_id integer primary key,
                                     section_id integer,
                                     budget_section_exist integer default 1
);

create table budget_table(
                             budget_id serial primary key,
                             budget_name varchar(32),
                             budget_start_date date,
                             budget_end_date date,
                             budget_exist integer default 1
    //delete_date date
);

create table section_table(
                              section_id integer primary key,
                              section_name varchar(32)
);

create table software_table(
                               software_id serial primary key,
                               software_name varchar(32),
                               software_type varchar(32),
                               total_number varchar(32),
                               software_remarks varchar(32),
                               software_exist integer default 1
);

create table software_section_table(
                                       software_id integer primary key,
                                       section_id integer,
                                       software_section_exist integer default 1
);

create table terminal_section_table(
                                       terminal_id integer primary key,
                                       section_id integer,
                                       terminal_section_exist integer default 1
);

create table terminal_table(
                               terminal_id serial primary key,
                               terminal_name varchar(32),
                               terminal_number varchar(32),
                               terminal_remarks varchar(32),
                               terminal_exist integer default 1
);

insert into account_table(account_name, account_pass, account_mail_address) values ('admin', '123', '@photon'),
                                                                                   ('media', '456', '@photon');


insert into section_table values (1, '管理者'),
                                 (2, '情報メディア課');

insert into account_section_table(account_id, section_id) values (1, 1),
                                                                 (2, 2);

