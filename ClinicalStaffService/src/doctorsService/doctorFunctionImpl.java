package doctorsService;

import java.sql.ResultSet;
import java.sql.SQLException;

import dataBaseConnect.connectDB;
import doctorsModel.doctorFunctions;

public class doctorFunctionImpl implements doctorFunctions {

	@Override
	public ResultSet getRandevous(int doctor_id) {
		// TODO Auto-generated method stub
		try{
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `randevou`, `patients` WHERE randevou.Patient_ID = patients.Patient_ID and randevou.Doctor_ID= "
						+doctor_id+" and  randevou.Date = CURDATE() and patients.Dead=0;";
			connection.resSet = connection.stmt.executeQuery(query);
			return connection.resSet;
			
		}catch(SQLException se){
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		
		return null;
	}

	@Override
	public ResultSet getRandevouInfo(int randevou_id) {
		// TODO Auto-generated method stub
		try{
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `randevou`, `patients` WHERE randevou.Patient_ID = patients.Patient_ID and randevou.Randevou_ID = "+randevou_id+" ;";
			connection.resSet = connection.stmt.executeQuery(query);
			return connection.resSet;
		}catch(SQLException se){
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		return null;
	}

	@Override
	public String[] getLastConditionAndMedicationOfPatient(int patient_id) {
		// TODO Auto-generated method stub
	
		try{
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `randevou` WHERE date IN (SELECT max(date) FROM `randevou` WHERE Date < CURDATE() and Append = 1 and Patient_ID = "+patient_id+")";
			connection.resSet = connection.stmt.executeQuery(query);
			//return connection.resSet;
			String [] a = new String[2];
			if(connection.resSet.next()){
				a[0] = connection.resSet.getString("Conditions");
				a[1] = connection.resSet.getString("Medication_ID");
			}
			return a;
		}catch(SQLException se){
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		return null;
	}

	@Override
	public ResultSet getDrugs() {
		// TODO Auto-generated method stub
		try{
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `drugs` ;";
			connection.resSet = connection.stmt.executeQuery(query);
			return connection.resSet;
		}catch(SQLException se){
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		return null;
	}

	@Override
	public boolean updateRandevou(int randevou_id, String Condition, int Medication, String comments,
			int overule, int lm, int dead, int sh, int patient_id) {
		// TODO Auto-generated method stub
		try {
			connectDB connection = new connectDB();
			String query1= "UPDATE randevou SET  Medication_ID = "+Medication+", Conditions = '"+Condition+"', Comments = '"+comments+
					"', Overule = "+overule+ " , Updated = "+1+" WHERE Randevou_ID = "+randevou_id+";";
					System.out.println(query1);
			connection.stmt.executeUpdate(query1); 
			
			String query2 = "UPDATE patients SET Madness_level = "+lm+", Dead = "+dead+", Self_harm = "+sh+" WHERE Patient_ID="+patient_id;
			connection.stmt.executeUpdate(query2); 
			return true;
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		return false;
		
	}

	@Override
	public ResultSet getNotUpdatedRandevous(int doctor_id) {
		// TODO Auto-generated method stub
		try{
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `randevou`, `patients` WHERE randevou.Patient_ID = patients.Patient_ID and randevou.Updated = 0 and randevou.Doctor_ID= "+doctor_id+" and CURDATE() > randevou.date ;";
			connection.resSet = connection.stmt.executeQuery(query);
			System.out.println(query);
			return connection.resSet;
		}catch(SQLException se){
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		return null;
	}

	@Override
	public boolean insertIncentend(int patient_id, String details, int medication) {
		// TODO Auto-generated method stub
		try {
			connectDB connection = new connectDB();
			String query1= "INSERT INTO incidents (Details, Patient_ID, Date, Medication_ID) VALUES ('"
			+details+"', "+patient_id+", CURDATE() ,"+medication+");";
			System.out.println(query1);
			connection.stmt.executeUpdate(query1); 
			return true;
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		      return false;
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
			return false;
		}
	}
	
	public 	String checkForAllergies(int p , int m){
		try{
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `allergies` WHERE allergies.Patient_ID = "+p+" and allergies.Medication_ID= "+m+" ;";
			connection.resSet = connection.stmt.executeQuery(query);
			System.out.println(query);
			
			if(connection.resSet.next()){
				return connection.resSet.getString("Details");
			}
		}catch(SQLException se){
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		return "";
	}
	
	public String [] getDrugInfo (int dr_id){
		try{
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `drugs` WHERE drugs.Medication_ID = "+dr_id+";";
			connection.resSet = connection.stmt.executeQuery(query);
			String [] a = new String[2];
			if (connection.resSet.next()){
				a[0]=connection.resSet.getString("Medication_Name");
				a[1] = connection.resSet.getString("Discreption");
			}
			return a;
		}catch(SQLException se){
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		return null;
	}
	
	public boolean updateDropIn(int randevou, int d_id){
		try {
			connectDB connection = new connectDB();
			String query1= "UPDATE randevou SET  drop_in = 1, Drop_in_date = NOW() ,Drop_in_doc = "+d_id+ " WHERE Randevou_ID = "+randevou+";";
					System.out.println(query1);
			connection.stmt.executeUpdate(query1); 
			
			return true;
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		return false;
	}


}
