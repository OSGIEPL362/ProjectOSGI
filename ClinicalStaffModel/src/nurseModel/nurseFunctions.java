package nurseModel;

import java.sql.ResultSet;

public interface nurseFunctions {

	ResultSet getPatients();
	ResultSet getInfoForPatient(int id);
	ResultSet getDrugs();
	String[] getLastConditionAndMedicationOfPatient(int patient_id);
}
