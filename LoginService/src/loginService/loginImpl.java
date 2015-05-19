package loginService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import dataBaseConnect.connectDB;
import loginModel.loginFunction;

public class loginImpl implements loginFunction {

	@Override
	public int[] login(String username, String password) {
		// TODO Auto-generated method stub.
		try{
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `staff` WHERE staff.Username = '"+username+"' and staff.Password = '"+password+"' ;";
			connection.resSet = connection.stmt.executeQuery(query);

			int[] a = new int[2];
			
			//ResultSet rs = factory.getRandevous(1111);
			try {
				if (connection.resSet.next()) {
					
					if (connection.resSet.getInt("Doctor")==1) 
						a[0]=0;					    
					if (connection.resSet.getInt("Nurse")==1) 
						a[0]=1;
					if (connection.resSet.getInt("Receptionist")==1) 
						a[0]=2;
					if (connection.resSet.getInt("Manager")==1) 
						a[0]=3;
					if (connection.resSet.getInt("Medical")==1) 
						a[0]=4;
					
					WriteFile(connection.resSet.getString(1),connection.resSet.getString(2));
				}
				else{
					a[0]=-1;
					return a;
				}				
				a[1]=connection.resSet.getInt("Staff_ID");
				return a;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}catch(SQLException se){
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		
		
		return null;
	}
	
	
	
	
	static void WriteFile(String id,String name){
		
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		try {
          PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("logFile.txt", true)));
          out.println(id +" "+name+" "+timeStamp);
          out.close();

	    } catch ( IOException e ) {
	       e.printStackTrace();
	    }
        
        
	}
	

}
