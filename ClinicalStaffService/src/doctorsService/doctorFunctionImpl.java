package doctorsService;

import java.sql.ResultSet;
import java.sql.SQLException;

import dataBaseConnect.connectDB;
import doctorsModel.doctorFunctions;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;

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
			String query = "SELECT * FROM `randevou` WHERE Patient_ID = "+patient_id+" and date IN (SELECT max(date) FROM `randevou` WHERE Date < CURDATE() and Append = 1 and Updated = 1 and Patient_ID = "+patient_id+")";
			//String query = "SELECT * FROM `randevou` WHERE date IN (SELECT max(date) FROM `randevou` WHERE Date < CURDATE() and Append = 1 and Updated = 1 and Patient_ID = "+patient_id+")";
			//SELECT * FROM `randevou` WHERE Patient_ID = "+patient_id+" and date IN (SELECT max(date) FROM `randevou` WHERE Date < CURDATE() and Append = 1 and Updated = 1 and Patient_ID = "+patient_id+")";
			connection.resSet = connection.stmt.executeQuery(query);			
			//return connection.resSet;
			
			String [] a = new String[3];
			if(connection.resSet.next()){
				a[0] = connection.resSet.getString("Conditions");
				a[1] = connection.resSet.getString("Medication_ID");
				a[2]=TakeAllCommendsFromPesient(patient_id);
			}
			return a;
		}catch(SQLException se){
		      se.printStackTrace();
		} catch (Exception ex) {
			System.out.println("ERROR" + ex);
		};
		return null;
	}

	
	public String TakeAllCommendsFromPesient(int patient_id){
		String s="";
		try{
		connectDB connection = new connectDB();
		String query = "SELECT * FROM `randevou` WHERE Updated=1 and Append=1 and Date<CURDATE() and Patient_ID="+patient_id+"";
		connection.resSet = connection.stmt.executeQuery(query);	
		while(connection.resSet.next()){
			s=s+"---------------"+connection.resSet.getString("Date")+"--------"+"\n";
			s=s+connection.resSet.getString("Comments")+"\n";
		}
		return s+"\n";
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
			
			
			String query3 = "SELECT * FROM patients  WHERE Patient_ID="+patient_id;
			connection.resSet = connection.stmt.executeQuery(query3);
			if(connection.resSet.next()){
				System.out.print(connection.resSet);
		
			}
			
			
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
			String query = "SELECT * FROM `randevou`, `patients` WHERE randevou.Patient_ID = patients.Patient_ID and randevou.Append=1 and randevou.Updated = 0 and randevou.Doctor_ID= "+doctor_id+" and CURDATE() > randevou.date and patients.Dead=0;";
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

	static String d_email = "pparthenhs@gmail.com";//to email mas 
	static String d_password = "jgfaksjbfkas8934j@njms$";// o kwdikos mas 
	static String d_host = "smtp.gmail.com";// o host
	static String d_port = "465";// to port
	static String m_to = "pparthenhs@yahoo.gr";//to email pou phgenei
	static String m_subject = "To email einai etoimo ";
	static String m_text = "Hey, this is a test email.";
    
	static private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(d_email, d_password);
        }
    }
    
	/**
	 * 
	 * This method sent the e-mail to the customer and 
	 * attached the proper pdf file 
	 *
	 *@param mailtoSend - customer email
	 *@param s5-id customer 
	 *@param s6-customer First name 
	 *@param s7- customer last name
	 *@param s8-type of file (find the folder)
	 */
    public static void send(String mailtoSend,String s5,String s6,String s7,String s8){
    
    	m_to=mailtoSend;
    	
        Properties props = new Properties();//ena hashtable
        
        props.put("mail.smtp.user", d_email);
        props.put("mail.smtp.host", d_host);
        props.put("mail.smtp.port", d_port);
        
        
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", d_port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        
        props.setProperty("mail.user", d_email);
        props.setProperty("mail.password", d_password);
        
        try {
        	
           Authenticator auth = new SMTPAuthenticator();
           Session session = Session.getInstance(props,auth); 
              
            Message msg = new MimeMessage(session);
             
            	msg.setSubject(m_subject);
            	msg.setFrom(new InternetAddress(d_email));
            	msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
            
            BodyPart messageBodyPart = new MimeBodyPart();
            
            	messageBodyPart.setText(m_text);
            
            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(messageBodyPart);
            
            messageBodyPart = new MimeBodyPart();
            
            String file = "C:/Users/Panos/Desktop/TEAM_D_361/"+s8+"/"+ s5 +"_" + s6 + "_"+ s7 + ".pdf";
            
            String fileName =  s5 +"_" + s6 + "_"+ s7 + ".pdf";
            
            DataSource source = new FileDataSource(file);
            
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);

            msg.setText(m_text);
            
            msg.setContent(multipart);
          
          
            Transport.send(msg);
            System.out.print("edw");
   
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }
   

}
