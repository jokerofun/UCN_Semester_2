package db;

import java.sql.SQLException;

import model.Account;

public interface AccountDbIF {
	
	Account insert(Account account) throws SQLException;
	 
	
}
