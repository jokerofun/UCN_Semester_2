package db;

import java.sql.Connection;
import java.sql.SQLException;

import control.DataAccessException;

public class DBReset {
	private static final String script = ""
			+ "alter table customerAddress drop constraint fkcustId;\r\n"
			+ "alter table saleOrder drop constraint customId;\r\n"
			+ "alter table discount drop constraint fksaleOrdId;\r\n"
			+ "alter table salesLine drop constraint fkprodId;\r\n"
			+ "alter table salesLine drop constraint fksaleId;\r\n"
			+ "alter table clothing drop constraint fkproductId;\r\n"
			+ "alter table equipment drop constraint fkproducId;\r\n"
			+ "alter table gunReplica drop constraint fkproId;\r\n"
			+ "alter table productSupplier drop constraint fksuppName;\r\n"
			+ "alter table productSupplier drop constraint fkprId;\r\n"
			+ "drop table customerAddress;\r\n"
			+ "drop table customer;\r\n"
			+ "drop table saleOrder;\r\n"
			+ "drop table discount;\r\n"
			+ "drop table product;\r\n"
			+ "drop table salesline;\r\n"
			+ "drop table clothing;\r\n"
			+ "drop table equipment;\r\n"
			+ "drop table gunReplica;\r\n"
			+ "drop table supplier;\r\n"
			+ "drop table productSupplier;\r\n"
			+ "create table customer(\r\n" + 
			"	customerId   int		   identity(1,1),\r\n" + 
			"    customerName    varchar(30)  not null,\r\n" + 
			"    phoneNumber  varchar(12)          not null,\r\n" + 
			"    CVR          char(9) ,\r\n" + 
			"    primary key (customerId)\r\n" +
			");\r\n" + 
			"\r\n" + 
			"create table customerAddress(\r\n" + 
			"	street       varchar(15)   not null,\r\n" + 
			"    city         varchar(15)   not null,\r\n" + 
			"    zipCode      varchar(6)    not null,\r\n" + 
			"    customerId   int           not null,\r\n" + 
			"    primary key (customerId),\r\n" + 
			"    constraint fkcustId foreign key(customerId) references customer(customerId)\r\n" + 
			"    on delete cascade\r\n" + 
			"    on update cascade\r\n" +
			");\r\n" + 
			"\r\n" + 
			"create table saleOrder(\r\n" + 
			"	saleOrderId    int		   identity(1,1),\r\n" + 
			"    saleDate       date        not null,\r\n" + 
			"    totalPrice    decimal(6,2),\r\n" + 
			"    deliveryStatus bit default 0,\r\n" + 
			"    deliveryDate   date,\r\n" + 
			"    customerId     int,\r\n" + 
			"    primary key (saleOrderId),\r\n" + 
			"    constraint customId foreign key (customerId) references customer(customerId)\r\n" +
			"    on update cascade\r\n" +
			");\r\n" + 
			"\r\n" + 
			"create table discount\r\n" +
			"(discountAmount      decimal(6,2),\r\n" +
			   "discountDescription varchar(15),\r\n" +
			   "saleOrderId         int		   not null,\r\n" +
			   "primary key (saleOrderId),\r\n" +
			   "constraint fksaleOrdId foreign key (saleOrderId) references saleOrder(saleOrderId)\r\n" +
					"on delete cascade\r\n" +
					"on update cascade\r\n" +
				 ");\r\n" +   
				 "\r\n" +
			"create table product\r\n" +
			   "(productId       int		     identity(1,1),\r\n" +
			    "productName     varchar(15)  not null,\r\n" +
				"purchasePrice   decimal(6,2) not null,\r\n" +
				"salesPrice      decimal(6,2) not null,\r\n" +
				"rentPrice       decimal(6,2) not null,\r\n" +
				"countryOfOrigin varchar(15)  not null,\r\n" +
			    "minStock        int          not null,\r\n" +
				"inStock         int          not null,\r\n" +
				"primary key (productId),\r\n" +
				"); \r\n" +
				"\r\n" +
			"create table salesLine\r\n" +
			    "(productId    int         not null,\r\n" +
				 "quantity     int         not null,\r\n" +
				 "isRent       bit         not null,\r\n" +
				 "saleOrderId  int         not null,\r\n" +
				 "primary key (productId, saleOrderId),\r\n" +
				 "constraint fkprodId foreign key (productId) references product(productId)\r\n" +
					"on delete cascade\r\n" +
					"on update cascade,\r\n" +
				 "constraint fksaleId foreign key (saleOrderId) references saleOrder(saleOrderId)\r\n" +
					"on delete cascade\r\n" +
					"on update cascade\r\n" +
				 ");\r\n" +
				 "\r\n" +
			"create table clothing\r\n" +
			   "(productId       int		     not null,\r\n" +
			    "size            varchar(3)   not null,\r\n" +
				"colour          varchar(8)   not null,\r\n" +
				"primary key (productId),\r\n" +
				"constraint fkproductId foreign key (productId) references product(productId)\r\n" +
					"on delete cascade\r\n" +
					"on update cascade,\r\n" +
				");\r\n" +
				"\r\n" +
			"create table equipment\r\n" +
			   "(productId             int		   not null,\r\n" +
			    "equipmentType         varchar(8)   not null,\r\n" +
				"equipmentDescription  varchar(20)  not null,\r\n" +
				"primary key (productId),\r\n" +
				"constraint fkproducId foreign key (productId) references product(productId)\r\n" +
					"on delete cascade\r\n" +
					"on update cascade,\r\n" +
				"); \r\n" +
				"\r\n" +
			"create table gunReplica\r\n" +
			   "(productId       int		        not null,\r\n" +
			    "calibre         decimal(3, 2)   not null,\r\n" +
				"material        varchar(8)      not null,\r\n" +
				"primary key (productId),\r\n" +
				"constraint fkproId foreign key (productId) references product(productId)\r\n" +
					"on delete cascade\r\n" +
					"on update cascade,\r\n" +
				");\r\n" +
				"\r\n" +
			"create table supplier\r\n" +
			   "(supplierName    varchar(15)  not null,\r\n" +
				"supplierAddress varchar(20)  not null,\r\n" +
				"country         varchar(10)  not null,\r\n" +
				"phoneNum        varchar(12)  not null,\r\n" +
				"email           varchar(25)  not null,\r\n" +
				"primary key (supplierName)\r\n" +
				");\r\n" +
				"\r\n" +
			"create table productSupplier\r\n" +
				"(supplierName    varchar(15)  not null,\r\n" +
				 "productId       int		  not null,\r\n" +
				 "primary key (supplierName, productId),\r\n" +
				"constraint fksuppName foreign key (supplierName) references supplier(supplierName)\r\n" +
					"on delete cascade\r\n" +
					"on update cascade,\r\n" +
				"constraint fkprId foreign key (productId) references product(productId)\r\n" +
					"on delete cascade\r\n" +
					"on update cascade,\r\n" +
			");\r\n" +
			"\r\n" + 
			"-- insert data\r\n" + 
			"insert into customer values ('James Cameron','79846518',null);\r\n" +
			"insert into customer values ('Franklin', '86753475','f6ds1f');\r\n" +
			"insert into customer values ('Joyce Richard','87654687',null);\r\n" +
			"insert into customer values ('Ahmad','87543876','4946hrt');\r\n" +
			"\r\n" + 
			"insert into customerAddress values('Denarksgade 32', 'Ulborg', '9002', 4);\r\n" +
			"insert into customerAddress values('ugirewodkpr 123', 'VIborg', '78786', 2);\r\n" +
			"insert into customerAddress values('WFgrtefr 34', 'Aaalborg', '456', 3);\r\n" +
			"insert into customerAddress values('Wgerty 634', 'Bike', '678', 1); \r\n" +
			"\r\n" + 
			"insert into product values ('Pen','12.3','8.4','5.4','Denmark','2','20');\r\n" +
			"insert into product values ('Pistol','16.3','6.4','4.6','Slovakia','7','65');\r\n" +
			"insert into product values ('Pants','15.3','3.4','5.62','USA','5','23'); \r\n" +
			"\r\n" + 
			"insert into gunReplica values(2,'4.2','Iron');\r\n" +
			"insert into equipment values(1,'Bic','Cool');\r\n" +
			"insert into clothing values(3,'M','Pink');\r\n" +
			"\r\n" + 
			"insert into saleOrder values('1753-2-15', '2652.23','Not delivered','1999-2-4', 1);\r\n" +
			"insert into saleOrder values('8-8-1856', '1765.45','Pending','4-2-1999', 3);\r\n" +
			"insert into saleOrder values('4-9-1985', '8456.56','In stock','4-2-1999', 4);\r\n" +
			"\r\n" + 
			"insert into discount values('25.21','Private', 3);\r\n" +
			"insert into discount values('654.45','Club', 2);\r\n" +
			"insert into discount values('5.36','Private', 1); \r\n" +
			"\r\n" + 
			"insert into salesLine values(2, '20', 1, 3);\r\n" +
			"insert into salesLine values(1, '14', 0, 2);\r\n" +
			"insert into salesLine values(3, '5', 1, 1);\r\n" +
			"\r\n" + 
			"insert into supplier values('Misfits','Rngiorwe 32','Denmark','56435564','test@dk');\r\n" +
			"insert into supplier values('TWRhtyh','fergthj 85','USA','894653','test@usa');\r\n" +
			"insert into supplier values('DWOInoifew','IUjkref 32','Turkey','54896','test@tk');\r\n" +
			"\r\n" + 
			"insert into productSupplier values('TWRhtyh', 3);\r\n" +
			"insert into productSupplier values('Misfits', 1);\r\n" +
			"insert into productSupplier values('DWOInoifew', 2);";
	
		public static void resetDB() throws DataAccessException {
			Connection connection = DBConnection.getInstance().getConnection();
			try {
				connection.createStatement().executeUpdate(script);
				System.out.println("DB reset successful!");
			} catch (SQLException e) {
				throw new DataAccessException("Reset unsuccessful!", e);
			}
		}
		
		public static void main(String[] args) throws DataAccessException {
			resetDB();
		}
}
