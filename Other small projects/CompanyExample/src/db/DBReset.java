package db;

import java.sql.Connection;
import java.sql.SQLException;

import ctrl.DataAccessException;

public class DBReset {
	private static final String script = ""
			+ "alter table department drop constraint fk_DepEmpl;\r\n"
			+ "drop table dependent;\r\n"
			+ "drop table works_on;\r\n"
			+ "drop table project;\r\n"
			+ "drop table dept_locations;\r\n"
			+ "drop table employee;\r\n"
			+ "drop table department;\r\n"
			+ "create table Department(\r\n" + 
			"	dname      varchar(15) not null,\r\n" + 
			"    dnumber    int         not null,\r\n" + 
			"    mgr_ssn     char(9)     not null,\r\n" + 
			"    mgr_start_date datetime,\r\n" + 
			"    primary key (dnumber),\r\n" + 
			"    unique(dname)\r\n" + 
			");\r\n" + 
			"\r\n" + 
			"create table Employee(\r\n" + 
			"	fname       varchar(15) not null,\r\n" + 
			"    minit       char,\r\n" + 
			"    lname       varchar(15) not null,\r\n" + 
			"    ssn         char(9),\r\n" + 
			"    bdate       datetime,\r\n" + 
			"    address     varchar(30),\r\n" + 
			"    sex         char,\r\n" + 
			"    salary      decimal(10,2),\r\n" + 
			"    super_ssn    char(9),\r\n" + 
			"    dno         int         not null,\r\n" + 
			"    primary key (ssn),\r\n" + 
			"    foreign key(super_ssn) references employee(ssn),\r\n" + 
			"    foreign key (dno) references Department(dnumber)\r\n" + 
			");\r\n" + 
			"\r\n" + 
			"create table Dept_Locations(\r\n" + 
			"	dnumber     int        not null,\r\n" + 
			"    dlocation   varchar(15) not null,\r\n" + 
			"    primary key(dnumber, dlocation),\r\n" + 
			"    foreign key (dnumber) references Department(dnumber)\r\n" + 
			");\r\n" + 
			"\r\n" + 
			"create table Project(\r\n" + 
			"	pname      varchar(15)  not null,\r\n" + 
			"    pnumber    int          not null,\r\n" + 
			"    plocation  varchar(15),\r\n" + 
			"    dnum       int          not null,\r\n" + 
			"    primary key (pnumber),\r\n" + 
			"    unique(pname),\r\n" + 
			"    foreign key (dnum) references Department(dnumber)\r\n" + 
			");\r\n" + 
			"\r\n" + 
			"create table Works_on(\r\n" + 
			"	essn       char(9)      not null,\r\n" + 
			"    pno        int          not null,\r\n" + 
			"    hours      decimal(3,1) not null,\r\n" + 
			"    primary key(essn, pno),\r\n" + 
			"    foreign key(essn) references Employee(ssn),\r\n" + 
			"    foreign key(pno) references Project(pnumber)\r\n" + 
			");\r\n" + 
			"\r\n" + 
			"create  table Dependent(\r\n" + 
			"	essn           char(9)     not null,\r\n" + 
			"    dependent_name varchar(15) not null,\r\n" + 
			"    sex            char,\r\n" + 
			"    bdate          datetime,\r\n" + 
			"    relationship   varchar(8),\r\n" + 
			"    primary key (essn,dependent_name),\r\n" + 
			"    foreign key (essn) references Employee(ssn)\r\n" + 
			");\r\n" + 
			"\r\n" + 
			"-- insert data\r\n" + 
			"insert into department values('Research', 5, '333445555','19880522');\r\n" + 
			"insert into department values('Administration',4,'987654321','19950101');\r\n" + 
			"insert into department values('Headquarters',1,'888665555','19810619');\r\n" + 
			"\r\n" + 
			"insert into employee values ('James','E','Borg','888665555','19371110','450 Stone, Houston,TX','M',55000,null,1);\r\n" + 
			"insert into employee values ('Franklin','T', 'Wong','333445555','19551208','638 Voss, Houston, TX','M',40000,'888665555',5);\r\n" + 
			"insert into employee values ('Jennifer','S','Wallace','987654321','19410620','291,Berry, Belaire,TX','F',43000,'888665555',4);\r\n" + 
			"insert into employee values ('Alicia','J','Zelaya','999887777','19680119','3321 Castle, Spring,TC','F',25000,'987654321',4);\r\n" + 
			"insert into employee values ('Ramesh','K','Narayalan','666884444','19620915','975, Fire Oak, Humble, TX','M',38000,'333445555',5);\r\n" + 
			"insert into employee values ('John','B','Smith','123456789','19650109','731 Fondren, Houston,TX','M',30000,'333445555',5);\r\n" + 
			"insert into employee values ('Joyce','A','English','453453453','19720731','5631Rice Houston,TX','F',25000,'333445555',5);\r\n" + 
			"insert into employee values ('Ahmad','V','Jabbar','987987987','19690329','980 Dallas Houston, TX','M',25000,'987654321',4);\r\n" + 
			"\r\n" + 
			"insert into dept_locations values(1,'Houston');\r\n" + 
			"insert into dept_locations values(4,'Stafford');\r\n" + 
			"insert into dept_locations values (5,'Bellaire');\r\n" + 
			"insert into dept_locations values (5,'Sugarland');\r\n" + 
			"insert into dept_locations values (5,'Houston');\r\n" + 
			"\r\n" + 
			"insert into project values ('ProductX',1,'Bellaire',5);\r\n" + 
			"insert into project values ('ProductY',2,'Sugarland',5);\r\n" + 
			"insert into project values ('ProductZ',3,'Houston',5);\r\n" + 
			"insert into project values ('Computerzation',10,'Stafford',4);\r\n" + 
			"insert into project values ('Reorganization',20,'Houston',1);\r\n" + 
			"insert into project values ('Newbenefits',30,'Stafford',4);\r\n" + 
			"\r\n" + 
			"insert into works_on values ('123456789',1,32.5);\r\n" + 
			"insert into works_on values ('123456789',2,7.5);\r\n" + 
			"insert into works_on values ('666884444',3,40);\r\n" + 
			"insert into works_on values ('453453453',1,20);\r\n" + 
			"insert into works_on values ('453453453',2,20);\r\n" + 
			"insert into works_on values ('333445555',2,10);\r\n" + 
			"insert into works_on values ('333445555',3,10);\r\n" + 
			"insert into works_on values ('333445555',10,10);\r\n" + 
			"insert into works_on values ('333445555',20,10);\r\n" + 
			"insert into works_on values ('999887777',30,30);\r\n" + 
			"insert into works_on values ('999887777',10,10);\r\n" + 
			"insert into works_on values ('987987987',10,35);\r\n" + 
			"insert into works_on values ('987987987',30,5);\r\n" + 
			"insert into works_on values ('987654321',30,20);\r\n" + 
			"insert into works_on values ('987654321',20,15);\r\n" + 
			"insert into works_on values ('888665555',20,0);\r\n" + 
			"\r\n" + 
			"insert into dependent values('333445555','Alice','F','19860405','Daugther');\r\n" + 
			"insert into dependent values('333445555','Theodore','M','19831025','Son');\r\n" + 
			"insert into dependent values('333445555','Joy','F','19580503','Spouse');\r\n" + 
			"insert into dependent values('987654321','Abner','M','19420228','Spouse');\r\n" + 
			"insert into dependent values('123456789','Michael','M','19880104','Son');\r\n" + 
			"insert into dependent values('123456789','Alice','F','19881230','Daugther');\r\n" + 
			"insert into dependent values('123456789','Elizabeth','F','19670505','Spouse');\r\n" + 
			"\r\n" + 
			"ALTER TABLE Department\r\n" + 
			"ADD CONSTRAINT fk_DepEmpl\r\n" + 
			"foreign key(mgr_ssn) references employee(ssn)";
	
		public static void resetDB() throws DataAccessException {
			Connection connection = DBConnection.getInstance().getConnection();
			try {
				connection.createStatement().executeUpdate(script);
			} catch (SQLException e) {
				//e.printStackTrace();
				throw new DataAccessException("It seems UNpossible to reset the beloved database.\n"
						+ "Maybe you should check the DBConnection settings? Has the \"company\" database been created in MS-SQL? \nTry \"create database company\" in SSMS.", e);
			}
		}
		
		public static void main(String[] args) throws DataAccessException {
			resetDB();
		}
}
