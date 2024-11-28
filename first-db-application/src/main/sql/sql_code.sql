create table company
(
    id   serial primary key,
    name varchar(256) not null unique
);


create table employee
(
    id         serial,
    first_name varchar(128) not null,
    last_name  varchar(128) not null,
    company_id int references company (id) on delete cascade,
    salary     int          not null,
    primary key (id, first_name)
);


create schema first_db_homework;


create table authors
(
    id         serial primary key,
    first_name varchar(128) not null,
    last_name  varchar(128) not null
);


create table books
(
    id          serial primary key,
    title       varchar(256) not null,
    year        int,
    pages_count int          not null,
    author_id   int references authors
);


insert into authors (first_name, last_name)
values ('Aleksandr', 'Pushkin'),
       ('Michail', 'Lermontov'),
       ('Nikolay', 'Nosov'),
       ('Samuil', 'Marshak'),
       ('Dzani', 'Rodari');


insert into books (title, year, pages_count, author_id)
values ('Chippolino', 1932, 235, 5),
       ('Lukomorye', 1825, 123, 1),
       ('Lukomorye', 1830, 135, 2),
       ('Lukomorye', 1965, 689, 3),
       ('Lukomorye', 1969, 74, 3),
       ('Lukomorye', 1826, 1250, 1),
       ('Lukomorye', 1929, 420, 5),
       ('Lukomorye', 1956, 80, 4),
       ('Lukomorye', 1952, 93, 4),
       ('Lukomorye', 1827, 913, 2);


create table company
(
    id   serial primary key,
    name varchar(128) not null,
    date date
);


create table employee
(
    id         serial primary key,
    first_name varchar(128) not null,
    last_name  varchar(128) not null,
    salary     int          not null,
    company_id int          not null references company
);


create table contact
(
    id     serial primary key,
    number varchar(256),
    type   varchar(256)
);


create table employee_contact
(
    employee_id integer not null references employee,
    contact_id  integer not null references contact,
    primary key (employee_id, contact_id)
);


insert into company (id, name, date)
values (3, 'TikTok', '1960-10-10'),
       (1, 'Google', '2023-03-24'),
       (2, 'Apple', '2001-01-10');


insert into employee (id, first_name, last_name, salary, company_id)
values (3, 'Lev', 'Dolgorukiy', 5000, 2),
       (4, 'Fedor', 'Pushkin', 15000, 3),
       (1, 'Ira', 'Ivanova', 500, 1),
       (2, 'Petr', 'Petrov', 1000, 1);


insert into contact (id, number, type)
values (1, '375447100500', 'Домашний'),
       (2, '37544000977', 'Рабочий');


insert into employee_contact (employee_id, contact_id)
values (1, 1),
       (1, 2),
       (2, 2),
       (3, 1),
       (3, 2),
       (4, 1);


select e.first_name || ' ' || e.last_name, c.name
from employee e,
     company c
where e.company_id = c.id;


select e.first_name || ' ' || e.last_name, c.name, c2.number || ' ' || c2.type
from employee e
         join company c on c.id = e.company_id
         join employee_contact ec on ec.employee_id = e.id
         join contact c2 on ec.contact_id = c2.id;


select *
from employee
         cross join(select count(*) from employee) t;


select e.first_name || ' ' || e.last_name, c.name, c2.number || ' ' || c2.type
from employee e
         left join company c on c.id = e.company_id
         left join employee_contact ec on ec.employee_id = e.id
         left join contact c2 on ec.contact_id = c2.id;


alter table employee alter column company_id drop not null ;


select c.name, count(c.name)
from employee e
         join company c on e.company_id = c.id
group by c.name
having count(c.name) > 1;


alter table employee
    add column gender char;


update employee set gender = 'M'
where id > 2;


update employee set gender = 'W'
where id <= 2;


alter table employee
    alter column gender set not null;
