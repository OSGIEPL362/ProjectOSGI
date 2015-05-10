package medicalService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import medicalModel.medicalFunctions;
import dataBaseConnect.connectDB;


public class medicalFunctionsImpl implements  medicalFunctions{
	
	public boolean addPatient(int id, String name,  String Address, int level, String realative, int hurm, int dead){
		
		try {
			connectDB connection = new connectDB();
			String query1= "INSERT INTO patients (Patient_ID, Name, Address, Madness_level, Dead, Self_harm, Relative_Email) VALUES ("
			+id+", '"+name+"', '"+Address+"', "+level+","+dead+","+hurm+",'" +realative+"');";
			System.out.println(query1);
			connection.stmt.executeUpdate(query1); 
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		      return false;
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
			return false;
		};
		
		return true;
	}
	public boolean addStaff(int id, String name, String Address, String clinic,String username,
			String password,int doc, int nurse, int receptionist, int health, int manager){
		try {
			connectDB connection = new connectDB();
			String query1= "INSERT INTO staff (Staff_ID, Name, Email,Clinic_Name, Username, Password, Doctor,Nurse, Receptionist, Manager, Medical)"+
					 " VALUES (" +id+", '"+name+"', '"+Address+"', '"+clinic+"','"+username+"', '"+password+"'," +doc+", "+nurse+","+receptionist+
					 ","+ health+", "+manager+ " );";
			System.out.println(query1);
			connection.stmt.executeUpdate(query1); 
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		
		return true;
	}
	
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
	
	public ResultSet getStaff(){
		try{
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `staff`";
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
	public ResultSet getInfoForStaff(int id){
		try{
			connectDB connection = new connectDB();
			String query = "SELECT * FROM `staff` WHERE Staff_ID="+id;
			connection.resSet = connection.stmt.executeQuery(query);
			return connection.resSet;
		}catch(SQLException se){
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		return null;	
	}
	
	
	public boolean editPatient(int id, String name,  String Address, int level, String realative, int hurm, int dead){
		//UPDATE MyGuests SET lastname='Doe' WHERE id=2
		try {
			connectDB connection = new connectDB();
			String query1= "UPDATE patients SET Name = '"+name+"', Address = '"+Address+"', Madness_level = '"+level+
					"', Dead = "+dead+", Self_harm = "+hurm+", Relative_Email = '"+realative+"' WHERE Patient_ID ="+id+";";
			
			System.out.println(query1);
			connection.stmt.executeUpdate(query1); 
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		      return false;
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
			return false;
		};
		
		return true;
	}
	public boolean editStaff(int id, String name, String Address, String clinic,String username,
			String password,int doc, int nurse, int receptionist, int health, int manager){
		
		try {
			connectDB connection = new connectDB();
			String query1= "UPDATE staff SET Name ='"+name+"', Email='"+Address+"',Clinic_Name = '"+clinic+"', Username='"+username+
					"', Password='"+password+"', Doctor ="+doc+",Nurse="+nurse+", Receptionist= "+receptionist+", Manager="+health+
					", Medical="+manager+ " WHERE Staff_ID=" +id+";";
					 System.out.println(query1);
			connection.stmt.executeUpdate(query1); 
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		
		return true;
	}

}
