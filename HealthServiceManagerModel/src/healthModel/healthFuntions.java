package healthModel;

import java.sql.ResultSet;

public interface healthFuntions {
	
	ResultSet getPatientsList();
	
	ResultSet getPatientInfo(int id);
	
	ResultSet getClinicsList();
	
	ResultSet getClinicInfo(int id);
	
	
	
}
