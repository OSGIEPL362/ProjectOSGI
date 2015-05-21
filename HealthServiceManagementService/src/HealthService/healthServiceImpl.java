package HealthService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import dataBaseConnect.connectDB;
import HealthModel.HealthFuctions;

public class healthServiceImpl implements HealthFuctions {

	public ResultSet getClinics(){
		try {
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `clinics`";
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
	
	@Override
	public String[][] getWeaklyReaport(String clinic) {
		// TODO Auto-generated method stub
		try {
			connectDB connection = new connectDB();
			String [][] table = new String[7][3];
			for(int i=0;i<table.length; i++){
				
				Integer attend = 0;
				String query = "SELECT * FROM `randevou` WHERE Clinic_Name = '"+clinic+"' and Append = 1 and Date = CURDATE() -"+i+";";
				connection.resSet = connection.stmt.executeQuery(query);
				while(connection.resSet.next()){
					attend++;
				}
				
				Integer drop = 0;
				String query1 = "SELECT * FROM `randevou` WHERE Clinic_Name = '"+clinic+"' and drop_in = 1 and drop_in_date = CURDATE() -"+i+";";
				connection.resSet = connection.stmt.executeQuery(query1);
				while(connection.resSet.next()){
					drop++;
				}
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -i);

				table[i][0]=new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime());
				table[i][1]= attend.toString();
				table[i][2] = drop.toString();
				
			}
			
			return table;

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		}
		;
		return null;
	}

}
