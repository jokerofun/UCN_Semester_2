package db;

import java.sql.Date;
import java.sql.SQLException;

import model.Account;
import model.Posting;

public class PostingDb implements PostingDbIF {

	@Override
	public Posting insert(Posting posting, Account account) throws SQLException {
		String sql = String.format("insert into posting values (%d, '%s', '%s', %d)", 
				posting.getAmount(), Date.valueOf(posting.getDate()), posting.getDescription(), account.getId());
		int id = DBConnection.getInstance().executeInsertWithIdentity(sql);
		posting.setId(id);
		return posting;
	}

}
