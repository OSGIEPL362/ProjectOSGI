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

}
