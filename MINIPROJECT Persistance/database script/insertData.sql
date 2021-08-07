use dmai0919_1081927;

insert into customer values ('James Cameron','79846518',null);
insert into customer values ('Franklin', '86753475','f6ds1f');
insert into customer values ('Joyce Richard','87654687',null);
insert into customer values ('Ahmad','87543876','4946hrt');

insert into customerAddress values('Denarksgade 32', 'Ulborg', '9002', 4);
insert into customerAddress values('ugirewodkpr 123', 'VIborg', '78786', 2);
insert into customerAddress values('WFgrtefr 34', 'Aaalborg', '456', 3);
insert into customerAddress values('Wgerty 634', 'Bike', '678', 1);

insert into product values ('Pen','12.3','8.4','5.4','Denmark','2','20');
insert into product values ('Pistol','16.3','6.4','4.6','Slovakia','7','65');
insert into product values ('Pants','15.3','3.4','5.62','USA','5','23');

insert into gunReplica values(2,'4.2','Iron');

insert into equipment values(1,'Bic','Cool');

insert into clothing values(3,'M','Pink');

insert into saleOrder values('1753-2-15', '2652.23','Not delivered','1999-2-4', 1);
insert into saleOrder values('8-8-1856', '1765.45','Pending','4-2-1999', 3);
insert into saleOrder values('4-9-1985', '8456.56','In stock','4-2-1999', 4);

insert into discount values('25.21','Private', 3);
insert into discount values('654.45','Club', 2);
insert into discount values('5.36','Private', 1);

insert into salesLine values(2, '20', 1, 3);
insert into salesLine values(1, '14', 0, 2);
insert into salesLine values(3, '5', 1, 1);

insert into supplier values('Misfits','Rngiorwe 32','Denmark','56435564','test@dk');
insert into supplier values('TWRhtyh','fergthj 85','USA','894653','test@usa');
insert into supplier values('DWOInoifew','IUjkref 32','Turkey','54896','test@tk');

insert into productSupplier values('TWRhtyh', 3);
insert into productSupplier values('Misfits', 1);
insert into productSupplier values('DWOInoifew', 2);



