package nurseService;

import java.sql.ResultSet;
import java.sql.SQLException;

import dataBaseConnect.connectDB;
import nurseModel.nurseFunctions;

public class nurseFuctionImpl implements nurseFunctions{
	public ResultSet getPatients(){
		
		try{
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `patients`";
			connection.resSet = connection.stmt.executeQuery(query);
			return connection.resSet;
		}catch(SQLException se){
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		return null;
	}
	
	public ResultSet getInfoForPatient(int id){
		try{
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `patients` WHERE Patient_ID="+id;
			connection.resSet = connection.stmt.executeQuery(query);
			return connection.resSet;
		}catch(SQLException se){
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		return null;
	}
	
	public ResultSet getDrugs() {
		// TODO Auto-generated method stub
		try {
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `drugs` ;";
			connection.resSet = connection.stmt.executeQuery(query);
			return connection.resSet;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		}
		;
		return null;
	}
	
	
	public String[] getLastConditionAndMedicationOfPatient(int patient_id) {
		// TODO Auto-generated method stub

		try {
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `randevou` WHERE Patient_ID = "
					+ patient_id
					+ " and date IN (SELECT max(date) FROM `randevou` WHERE Date < CURDATE() and Append = 1 and Updated = 1 and Patient_ID = "
					+ patient_id + ")";
			// String query =
			// "SELECT * FROM `randevou` WHERE date IN (SELECT max(date) FROM `randevou` WHERE Date < CURDATE() and Append = 1 and Updated = 1 and Patient_ID = "+patient_id+")";
			// SELECT * FROM `randevou` WHERE Patient_ID = "+patient_id+" and
			// date IN (SELECT max(date) FROM `randevou` WHERE Date < CURDATE()
			// and Append = 1 and Updated = 1 and Patient_ID = "+patient_id+")";
			connection.resSet = connection.stmt.executeQuery(query);
			// return connection.resSet;

			String[] a = new String[3];
			if (connection.resSet.next()) {
				a[0] = connection.resSet.getString("Conditions");
				a[1] = connection.resSet.getString("Medication_ID");
				a[2] = TakeAllCommendsFromPesient(patient_id);
			}
			return a;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		}
		;
		return null;
	}
	
	public String TakeAllCommendsFromPesient(int patient_id) {
		String s = "";
		try {
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `randevou` WHERE Updated=1 and Append=1 and Date<CURDATE() and Patient_ID="
					+ patient_id + "";
			connection.resSet = connection.stmt.executeQuery(query);
			while (connection.resSet.next()) {
				s = s + "---------------" + connection.resSet.getString("Date")
						+ "--------" + "\n";
				s = s + connection.resSet.getString("Comments") + "\n";
			}
			return s + "\n";
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		}
		;
		return null;
	}
}
