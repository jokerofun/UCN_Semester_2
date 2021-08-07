package Db;

import java.util.ArrayList;

import controller.DataAccessException;
import model.Entry;

public interface ProcedureODDbIF {

	void create(int procedureA, int procedureB) throws DataAccessException;

	ArrayList<Entry> getAllEntries() throws DataAccessException;
}
