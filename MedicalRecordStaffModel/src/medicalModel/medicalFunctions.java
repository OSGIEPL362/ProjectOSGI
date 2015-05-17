package medicalModel;
import java.sql.ResultSet;

public interface medicalFunctions {

	boolean addPatient(int id, String name,  String Address, int level, String realative, int hurm, int dead);
	boolean addStaff(int id, String name, String Address, String clinic,String username,
			String password,int doc, int nurse, int receptionist, int health, int manager);
	
	ResultSet getPatients();
	ResultSet getStaff();
	
	ResultSet getInfoForPatient(int id);
	ResultSet getInfoForStaff(int id);
	
	boolean editPatient(int id, String name, String Address, int level, String realative, int hurm, int dead);
	
	boolean editStaff(int id, String name, String Address, String clinic,String username,
			String password,int doc, int nurse, int receptionist, int health, int manager);

	
	boolean checkString(String t);
	boolean checkNumber(String s);
	boolean checkEmailAddress(String s);

}
