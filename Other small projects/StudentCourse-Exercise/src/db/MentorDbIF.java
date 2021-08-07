package db;

import control.DataAccessException;
import model.Mentor;

public interface MentorDbIF {
	 
	 Mentor createWithIdentity(String name)  throws DataAccessException;
	 
	 Mentor update(Mentor mentor) throws DataAccessException;
	 
	 boolean delete(int id) throws DataAccessException;
	 
	 Mentor findById(int id) throws DataAccessException;
	 

}
