package receptionistModel;

import java.sql.ResultSet;

public interface receptionistFunctions {
	
	ResultSet getPatients();
	
	boolean checkAvaliability(String date, int time, int d_id);
	
	ResultSet getStaff(String clinic);
	
}
