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

}
