package medicalService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
	
	

	/**
	 * 
	 * This method is used for checking if the given text
	 * is a string without numbers or spaces.
	 *
	 *@param t - input string
	 *@return -true string without numbers or spaces.
	 */
	public  boolean checkString(String t) {

		char[] chars = t.toCharArray();

		for (char c : chars) {
			if (!Character.isLetter(c)&& !(c==' ')) {
				return false;
			}
		}

		return true;

	}
	/**
	 * 
	 * This method is used for checking if the given text
	 * is a number.
	 *
	 *@param s- input string
	 *@return -true if is number
	 */
	public  boolean checkNumber(String s) {

		if (!s.matches("[0-9]+")) {
			return false;
		}
		return true;

	}

	/**
	 * 
	 * This method is used for checking if the given text
	 * is an email.
	 *
	 *@param s -string input
	 *@return -true if string is e-mail address
	 */
	public  boolean checkEmailAddress(String s) {
		String epatern = "^.+@.+";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(epatern);
		java.util.regex.Matcher m = p.matcher(s);
		return m.matches();
	}
	
	public boolean SendEmailtoALL(int p_id, String remail, String Name) {
		try {
			connectDB connection = new connectDB();
			String query = "SELECT Email FROM `staff` WHERE Doctor = 1 or Nurse=1 ";
			connection.resSet = connection.stmt.executeQuery(query);

			while (connection.resSet.next()) {
				send(connection.resSet.getString(1), p_id, Name);
			}
			send(remail, p_id, Name);
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		}
		;

		return false;
	}

	static String d_email = "pparthenhs@gmail.com";// to email mas
	static String d_password = "jgfaksjbfkas8934j@njms$";// o kwdikos mas
	static String d_host = "smtp.gmail.com";// o host
	static String d_port = "465";// to port
	static String m_to = "kkoushi_antria13@hotmail.com";// to email pou phgenei
	static String m_subject = "To email einai etoimo ";
	static String m_text = "Hey, this is a test email.";

	static private class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(d_email, d_password);
		}
	}

	/**
	 * 
	 * This method sent the e-mail to the customer and attached the proper pdf
	 * file
	 *
	 * @param mailtoSend
	 *            - customer email
	 * @param s5
	 *            -id customer
	 * @param s6
	 *            -customer First name
	 * @param s7
	 *            - customer last name
	 * @param s8
	 *            -type of file (find the folder)
	 */
	public static void send(String mailtoSend, int id, String name) {

		// m_to=mailtoSend;
		m_subject = "Self harm Pasient";
		m_text = "The Pesient with name : " + name + "and with ID:" + id
				+ "is a self harm person";
		Properties props = new Properties();// ena hashtable

		props.put("mail.smtp.user", d_email);
		props.put("mail.smtp.host", d_host);
		props.put("mail.smtp.port", d_port);

		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", d_port);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		props.setProperty("mail.user", d_email);
		props.setProperty("mail.password", d_password);

		try {

			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);

			Message msg = new MimeMessage(session);

			msg.setSubject(m_subject);
			msg.setFrom(new InternetAddress(d_email));
			msg.addRecipient(Message.RecipientType.TO,
					new InternetAddress(m_to));

			msg.setText(m_text);

			Transport.send(msg);
			System.out.print("edw");

		} catch (Exception mex) {
			mex.printStackTrace();
		}
	}

}
