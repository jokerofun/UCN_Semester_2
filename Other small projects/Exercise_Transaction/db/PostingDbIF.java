package db;

import java.sql.SQLException;
import java.util.List;

import model.Account;
import model.Posting;

public interface PostingDbIF {
	
	Posting insert(Posting posting, Account account) throws SQLException;
	 
}
