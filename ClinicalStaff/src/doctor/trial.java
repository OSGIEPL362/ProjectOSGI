package doctor;

import java.sql.ResultSet;
import java.sql.SQLException;

import doctorFactory.doctorServiceFactory;
import doctorsModel.doctorFunctions;

public class trial {
	
	public static void main(String[] args) {
		
		//get randevous
		doctorFunctions factory = doctorServiceFactory.getFactory();
		ResultSet rs = factory.getRandevous(1111);
		try {
			while (rs.next()) {
				String name = rs.getString("Name");
				//String surname = rs.getString("Surname");
				System.out.println("Patient "+name );
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ResultSet rs1 = factory.getRandevouInfo(1);
		try {
			while (rs1.next()) {
				String name = rs1.getString("Date");
				String surname = rs1.getString("Name");
				System.out.println("Randevou "+name + "Patienr "+surname );
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String [] a= factory.getLastConditionAndMedicationOfPatient(1112);
		System.out.println("c: "+a[0]+" m: "+a[1]);
		
		String ale = factory.checkForAllergies(1112,1);
		System.out.println("Details: "+ale);
		
		ResultSet rs2 = factory.getDrugs();
		try {
			while (rs2.next()) {
				String name = rs2.getString("Medication_Name");
				//String surname = rs.getString("Surname");
				System.out.println("Drug "+name );
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		factory.updateRandevou(1, "kolos",12, "vlimma",0, 1, 1, 1, 1112);
		
		ResultSet rs3 = factory.getNotUpdatedRandevous(1111);
		
		try {
			while (rs3.next()) {
				String name = rs3.getString("Clinic_Name");
				//String surname = rs.getString("Surname");
				System.out.println("Clinic "+name );
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(factory.insertIncentend(1112, "apla en qbs", 1)){
			System.out.println("new incented insert");
		}
		System.out.println("aaa");
		
		
	}
	
}
