package doctorsModel;

import java.sql.ResultSet; 

public interface doctorFunctions {

	ResultSet getRandevous (int doctor_id);
	ResultSet getRandevouInfo (int randevou_id);
	String[] getLastConditionAndMedicationOfPatient(int patient_id);
	ResultSet getDrugs ();
	boolean updateRandevou(int randevou_id, String Condition, int Medication, String comments,int overule, int lm, int dead, int sh, int patient_id);
	
	ResultSet getNotUpdatedRandevous(int doctor_id);
	boolean insertIncentend(int patient_id, String details, int medication);
	
	String checkForAllergies(int p , int m);
	
	String [] getDrugInfo (int dr_id);
	
	boolean updateDropIn(int randevou, int d_id);
	
}
