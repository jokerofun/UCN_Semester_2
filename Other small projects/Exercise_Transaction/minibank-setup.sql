use master;
if exists (select * from sys.databases where name='minibank')
	drop database minibank;
go
create database minibank;
go

use minibank;

create table customer (
	id int primary key identity(1,1),
	name varchar(20) not null
);

create table account (
	id int primary key identity(1, 1),
	name varchar(20) not null,
	customer_id int foreign key references customer(id) on delete set null on update cascade
);

create table posting (
	id int primary key identity(1,1),
	amount decimal not null default 0,
	posting_date date not null,
	description varchar(50),
	account_id int not null foreign key references account(id) on delete cascade on update cascade,
)


insert into customer values ('Joe');
insert into customer values ('Jane');
insert into account values('boat saving', 1);
insert into account values('checking', 2);
insert into account values('retirement', 2);
insert into posting values (200.00,'2018-03-01', '', 1); -- joe saves money
insert into posting values (150.00,'2018-03-03', '',1); -- joe saves money
insert into posting values (2200.00, '2018-01-30', 'salary',2); -- jane gets her salary
insert into posting values (2250.00, '2018-02-27', 'salary',2); -- jane gets her salary after a raise
insert into posting values (700.00, '2018-03-05', '', 3); -- jane saves some money for retirement



