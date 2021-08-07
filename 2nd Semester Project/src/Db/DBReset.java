package Db;

import java.sql.Connection;
import java.sql.SQLException;

import controller.DataAccessException;

public class DBReset {
	// @formatter:off
	private static final String scriptCreateTables = ""
			+ "alter table employee drop constraint fkPos;\r\n"
			+ "alter table address drop constraint fkCustId;\r\n"
			+ "alter table address drop constraint fkEmpId;\r\n"
			+ "alter table offerProcedures drop constraint fkON;\r\n"
			+ "alter table offerProcedures drop constraint fkOP;\r\n"
			+ "alter table product drop constraint fkpso;\r\n"
			+ "drop table saleOrder;\r\n"
			+ "drop table offer;\r\n"
			+ "drop table procedures;\r\n"
			+ "drop table offerProcedures;\r\n"
			+ "drop table procedureOrder;\r\n"
			+ "drop table employeeProcedure;\r\n"
			+ "drop table product;\r\n"
			+ "drop table position;\r\n"
			+ "drop table employee;\r\n"
			+ "drop table customer;\r\n"
			+ "drop table address;\r\n"
			+ "drop table tool;\r\n"
			+ "drop table machine;\r\n"
			+ "drop table material;\r\n"
			+ "create table saleOrder(\r\n" +
				"saleOrderNumber int identity(1,1),\r\n" +
				"customerName varchar(30) not null,\r\n" +
				"orderDate date not null,\r\n" +
				"dueDate date not null,\r\n" +
				"offerNumber int not null,\r\n" +
				"totalPrice decimal(6,2) not null,\r\n" +
				"version BIGINT not null\r\n" +
				"primary key (saleOrderNumber)\r\n" +
				");\r\n" +
				"\r\n" +
				"create table offer(\r\n" +
				"offerNumber int identity(1,1),\r\n" +
				"dueDate date not null,\r\n" +
				"materialPrice decimal(6,2) not null,\r\n" +
				"totalPrice decimal(6,2) not null,\r\n" +
				"version BIGINT not null\r\n" +
				"primary key (offerNumber)\r\n" +
				");\r\n" +
				"\r\n" +
				"create table procedures(\r\n" +
				"code int identity(1,1),\r\n" +
				"procedureName varchar(30) not null,\r\n" +
				"pricePerHour decimal(6,2) not null,\r\n" +
				"employeeTypeRequired varchar(30) not null,\r\n" +
				"done int not null,\r\n" +
				"version BIGINT not null\r\n" +
				"primary key (code)\r\n" +
				");\r\n" +
				"\r\n" +
				"create table offerProcedures(\r\n" +
				"offerNumber int not null,\r\n" +
				"productName varchar(30) not null,\r\n" +
				"quantity int not null,\r\n" +
				"hours int not null,\r\n" +
				"code int not null,\r\n" +
				"version BIGINT not null\r\n" +
				"constraint onpncUnique unique(offerNumber, productName, code)\r\n" +
				"constraint fkON foreign key(offerNumber) references Offer(offerNumber)\r\n" +
				"on delete cascade\r\n" +
				"on update cascade,\r\n" +
				"constraint fkOP foreign key(code) references Procedures(code)\r\n" +
				"on delete cascade\r\n" +
				"on update cascade,\r\n" +
				");\r\n" +
				"\r\n" +
				"create table product(\r\n" +
				"productId int identity(1,1),\r\n" +
				"productName varchar(30) not null,\r\n" +
				"productDescription varchar(30) not null,\r\n" +
				"productPrice decimal(6,2) not null,\r\n" +
				"productQuantity int not null,\r\n" +
				"saleOrderNumber int not null,\r\n" +
				"version BIGINT not null\r\n" +
				"primary key (productId)\r\n" +
				"constraint fkpso foreign key(saleOrderNumber) references saleOrder(saleOrderNumber)\r\n" +
				"on delete cascade\r\n" +
				"on update cascade\r\n" +
				");\r\n" +
				"\r\n" +
				"create table procedureOrder(\r\n" +
				"procedureA int, \r\n"+
				"procedureB int \r\n" +
				"constraint ppUnique unique(procedureA,procedureB)" +
				");\r\n" +
				"\r\n" +
				"create table employeeProcedure(\r\n" +
				"employeeId int, \r\n"+
				"code int \r\n" +
				");\r\n" +
				"\r\n" +
				"create table customer(\r\n" +
				"customerId int identity(1,1),\r\n" +
				"companyName varchar(30) not null,\r\n" +
				"CUI varchar(30) not null,\r\n" +
				"phoneNumber varchar(30) not null,\r\n" +
				"email varchar(30) not null,\r\n" +
				"version BIGINT not null\r\n" +
				"primary key (customerId)\r\n" +
				");\r\n" +
				"\r\n" +
				"create table position(\r\n" +
				"positionName varchar(30) not null,\r\n" +
				"clearance varchar(30) not null,\r\n" +
				"version BIGINT not null\r\n" +
				"primary key (positionName)\r\n" +
				");\r\n" +
				"\r\n" +
				"create table employee(\r\n" +
				"employeeId int identity(1,1),\r\n" +
				"firstName varchar(30) not null,\r\n" +
				"middleName varchar(30) not null,\r\n" +
				"lastName varchar(30) not null,\r\n" +
				"salaryPerHour decimal(6,2) not null,\r\n" +
				"phoneNumber varchar(30) not null,\r\n" +
				"email varchar(30) not null,\r\n" +
				"position varchar(30) not null,\r\n" +
				"version BIGINT not null\r\n" +
				"primary key (employeeId)\r\n" +
				"constraint fkPos foreign key(position) references position(positionName)\r\n" +
				"on update cascade\r\n" +
				");\r\n" +
				"\r\n" +
				"create table address(\r\n" +
				"zipcode varchar(30) not null,\r\n" +
				"city varchar(30) not null,\r\n" +
				"street varchar(30) not null,\r\n" +
				"customerId int,\r\n" +
				"employeeId int,\r\n" +
				"version BIGINT not null\r\n" +
				"constraint zcsUnique unique(zipcode, city, street)\r\n" +
				"constraint fkCustId foreign key(customerId) references customer(customerId)\r\n" +
				"on delete cascade\r\n" +
				"on update cascade,\r\n" +
				"constraint fkEmpId foreign key (employeeId) references employee(employeeId)\r\n" +
				"on delete cascade\r\n" +
				"on update cascade\r\n" +
				");\r\n" +
				"\r\n" +
				"create table tool(\r\n" +
				"toolId int identity(1,1),\r\n" +
				"toolName varchar(30) not null,\r\n" +
				"toolLength float not null,\r\n" +
				"toolDiameter float not null,\r\n" +
				"toolWear float not null,\r\n" +
				"toolQuantity int not null,\r\n" +
				"version BIGINT not null\r\n" +
				"primary key (toolId)\r\n" +
				");\r\n" +
				"\r\n" +
				"create table machine(\r\n" +
				"machineId int identity(1,1),\r\n" +
				"machineName varchar(30) not null,\r\n" +
				"machineDimension float not null,\r\n" +
				"version BIGINT not null\r\n" +
				"primary key (machineId)\r\n" +
				");\r\n" +
				"\r\n" +
				"create table material(\r\n" +
				"materialId int identity(1,1),\r\n" +
				"materialName varchar(30) not null,\r\n" +
				"materialDimension float not null,\r\n" +
				"version BIGINT not null\r\n" +
				"primary key (materialId)\r\n" +
				");\r\n" +
				"\r\n" +
				"\r\n";
	
	private static final String insertTestData = ""
			+ "";

	// @formatter:on
	public static void resetDB() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			connection.createStatement().executeUpdate(scriptCreateTables);
			System.out.println("DB reset successful!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertTestData() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			connection.createStatement().executeUpdate(insertTestData);
			System.out.println("Test data inserted successfully!");
		} catch (SQLException e) {
			throw new DataAccessException("Test data insertion unsuccessful!", e);
		}
	}

	public static void main(String[] args) throws DataAccessException {
		resetDB();
		// insertTestData();
	}
}
