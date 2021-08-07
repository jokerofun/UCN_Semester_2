package db;

import java.sql.ResultSet;
import java.util.List;

import control.DataAccessException;
import model.Team;

public interface TeamDbIF {
	
	 //Team create(Team team) throws DataAccessException;
	 
	 Team createWithIdentity(String prefix, String description)  throws DataAccessException;
	 
	 Team update(Team team) throws DataAccessException;
	 
	 boolean delete(int id) throws DataAccessException;
	 
	 Team buildObject(ResultSet rs,boolean fullAssociation) throws DataAccessException;
	 
	 Team findById(int id, boolean fullAssociation) throws DataAccessException;
	 
	 Team findByPrefix(String prefix, boolean fullAssociation) throws DataAccessException;
	 
	 List<Team> getAll(boolean fullAssociation) throws DataAccessException;
	 
}
