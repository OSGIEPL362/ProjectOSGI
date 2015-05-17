package receptionistService;

import java.sql.ResultSet;
import java.sql.SQLException;

import dataBaseConnect.connectDB;
import receptionistModel.receptionistFunctions;

public class receptionistService implements receptionistFunctions {

	@Override
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
	
	public boolean checkAvaliability(String date, int time, int d_id){
		try{
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `randevou` WHERE date = '"+date+"' and time = "+time+" and Doctor_ID="+d_id+";";
			connection.resSet = connection.stmt.executeQuery(query);
			if(connection.resSet.next()){
				return false;
			}
			return true;
		}catch(SQLException se){
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		
		return false;
	}

	public ResultSet getStaff(String clinic){
		try{
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `staff` WHERE Doctor = 1 and Clinic_Name='"+clinic+"';";
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
	
	public 	String getCliniName(int id){
		try{
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `staff` WHERE Staff_ID="+id;
			connection.resSet = connection.stmt.executeQuery(query);
			if(connection.resSet.next()){
				String clinic = connection.resSet.getString("Clinic_Name");
				return clinic;
			}
		}catch(SQLException se){
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		return null;
	}
	
	
	public 	boolean creareNewRandevou(int pid, String date, int time, String clinic, int did){
		try {
			connectDB connection = new connectDB();
			String query1= "INSERT INTO randevou (Patient_ID, Date, Time, Clinic_Name, Doctor_ID) VALUES ("
			+pid+", '"+date+"', "+time+", '"+clinic+"',"+did+");";
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
	
	public String[] getLastConditionAndMedicationOfPatient(int patient_id) {
		// TODO Auto-generated method stub
	
		try{
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `randevou` WHERE Patient_ID = "+patient_id+" and date IN (SELECT max(date) FROM `randevou` WHERE Date < CURDATE() and Append = 1 and Updated = 1 and Patient_ID = "+patient_id+")";
			connection.resSet = connection.stmt.executeQuery(query);
			//return connection.resSet;
			String cond;
			int med;
			String [] ReternTable = new String [2];
			if(connection.resSet.next()){
				ReternTable[0] = connection.resSet.getString("Conditions");
				med = connection.resSet.getInt("Medication_ID");
				
				String q = "SELECT * FROM `drugs` WHERE Medication_ID ="+med+";";
				connection.resSet = connection.stmt.executeQuery(q);
				if(connection.resSet.next()){
					ReternTable[1] = connection.resSet.getString("Medication_Name");
					return ReternTable;
				}
				
			}
			
		}catch(SQLException se){
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		return null;
	}
	
	public 	ResultSet getRandevous (){
		try{
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `randevou`, `patients` WHERE randevou.Patient_ID = patients.Patient_ID and  randevou.Date = CURDATE() and randevou.Append=0 and patients.Dead=0;";
			connection.resSet = connection.stmt.executeQuery(query);
			return connection.resSet;
			
		}catch(SQLException se){
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		return null;
	}
	
	public String getDoctorName(int did){
		try{
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `staff` WHERE Staff_ID = "+did+";";
			connection.resSet = connection.stmt.executeQuery(query);
			
			if(connection.resSet.next()){
				return connection.resSet.getString("Name");
			}
		}catch(SQLException se){
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		
		return null;
	}
	
	public boolean attendRandevou(int rid){
		
		try {
			connectDB connection = new connectDB();
			String query1= "UPDATE randevou SET  Append = 1 WHERE Randevou_ID = "+rid+";";
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
