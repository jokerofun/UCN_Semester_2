package control;

import java.util.List;
import db.MentorDb;
import model.Mentor;

/**
 * Write a description of class MentorController here.
 *
 * @author Gianna
 * @version (a version number or a date)
 */

public class MentorController {
    private MentorDb mentorDb;
    /**
     * Constructor for objects of class MentorController
     */
    public MentorController() {
        mentorDb = new MentorDb();
    }
    
    public boolean addMentor(String name) {
 
    	//TODO check parameters
    	try {
			mentorDb.createWithIdentity(name);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return true;
    }
    
    public boolean removeMentor(int id){
    	//TODO check parameters 
    	try {
			mentorDb.delete(id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return true;
    }

}
