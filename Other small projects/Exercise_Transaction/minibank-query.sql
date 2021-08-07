--select account_id, sum(posting.posting) as balance from posting group by account_id;

select cust_name, account_name, balance from (
		(select account.id as acc_id, customer.name as cust_name , account.name as account_name from customer, account where customer.id = account.customer_id ) foo
	inner join 
		(select account_id, sum(posting.amount) as balance from posting group by account_id) bal 
	on foo.acc_id = bal.account_id
);
