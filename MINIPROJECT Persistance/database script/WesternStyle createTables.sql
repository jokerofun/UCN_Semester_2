
use WesternStyle;

create table customer
   ( customerId   int		   identity(1,1),
     customerName    varchar(30)  not null,
     phoneNumber  varchar(12)          not null,
     CVR          char(9) ,
     primary key (customerId));

create table customerAddress
   (street       varchar(15)   not null,
    city         varchar(15)   not null,
	zipCode      varchar(6)    not null,
	customerId   int           not null,
	primary key (customerId),
	constraint fkcustId foreign key (customerId) references customer(customerId)
		on delete cascade
		on update cascade,
	);   

create table saleOrder
   (saleOrderId    int		   identity(1,1),
    saleDate       date        not null,
	totalPrice     decimal(6,2),
	deliveryStatus bit default 0,
    deliveryDate   date,
	customerId     int,
	primary key (saleOrderId),
	constraint customId foreign key (customerId) references customer (customerId)
	on update cascade
	);   

create table discount
  (discountAmount      decimal(6,2),
   discountDescription varchar(15),
   saleOrderId         int		   not null,
   primary key (saleOrderId),
   constraint fksaleOrdId foreign key (saleOrderId) references saleOrder(saleOrderId)
		on delete cascade
		on update cascade
	 );   

create table product
   (productId       int		     identity(1,1),
    productName     varchar(15)  not null,
	purchasePrice   decimal(6,2) not null,
	salesPrice      decimal(6,2) not null,
	rentPrice       decimal(6,2) not null,
	countryOfOrigin varchar(15)  not null,
    minStock        int          not null,
	inStock         int          not null,
	primary key (productId),
	); 

create table salesLine
    (productId    int           not null,
	 quantity     int           not null,
	 isRent       bit           not null,
	 saleOrderId  int           not null,
	 totalPrice   decimal(6, 2) not null,
	 primary key (productId, saleOrderId),
	 constraint fkprodId foreign key (productId) references product(productId)
		on delete cascade
		on update cascade,
	 constraint fksaleId foreign key (saleOrderId) references saleOrder(saleOrderId)
		on delete cascade
		on update cascade
	 );   

create table clothing
   (productId       int		     not null,
    size            varchar(3)   not null,
	colour          varchar(8)   not null,
	primary key (productId),
	constraint fkproductId foreign key (productId) references product(productId)
		on delete cascade
		on update cascade,
	); 

create table equipment
   (productId             int		   not null,
    equipmentType         varchar(8)   not null,
	equipmentDescription  varchar(20)  not null,
	primary key (productId),
	constraint fkproducId foreign key (productId) references product(productId)
		on delete cascade
		on update cascade,
	); 

create table gunReplica
   (productId       int		        not null,
    calibre         decimal(3, 2)   not null,
	material        varchar(8)      not null,
	primary key (productId),
	constraint fkproId foreign key (productId) references product(productId)
		on delete cascade
		on update cascade,
	); 
  
create table supplier 
   (supplierName    varchar(15)  not null,
	supplierAddress varchar(20)  not null,
	country         varchar(10)  not null,
	phoneNum        varchar(12)  not null,
	email           varchar(25)  not null,
	primary key (supplierName)
	);

create table productSupplier
	(supplierName    varchar(15)  not null,
	 productId       int		  not null,
	 primary key (supplierName, productId),
	constraint fksuppName foreign key (supplierName) references supplier(supplierName)
		on delete cascade
		on update cascade,
	constraint fkprId foreign key (productId) references product(productId)
		on delete cascade
		on update cascade,
);