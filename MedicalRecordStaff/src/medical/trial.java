package medical;

import java.sql.ResultSet;
import java.sql.SQLException;

import medicalFactory.medicalServiceFactory;
import medicalModel.medicalFunctions;

public class trial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		medicalFunctions factory = medicalServiceFactory.getFactory();
		
//	if(factory.addPatient(1, "a", "a",  1,"a", 0, 0)){
//		System.out.println("new Patient insert");
//	}
		
		
		ResultSet rs = factory.getPatients();
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
		
		ResultSet rs1 = factory.getStaff();
		try {
			while (rs1.next()) {
				String name = rs1.getString("Name");
				//String surname = rs.getString("Surname");
				System.out.println("Patient "+name );
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		factory.editPatient(1, "aaaa", "a",  1,"a", 0, 0);
		factory.editStaff(123, "Maria", "1, Maria Address Str", "Clinic12","maria01", "pass",0,1,0,0,0);
		
		//medicalFunctions factory = medicalServiceFactory.getFactory();
//		if(factory.addPatient(1, "a", "a", "a", 1,"a", 0, 0)){
//			System.out.print("new Patient insert");
//		}
		
//		if(factory.addStaff(123, "Maria", "Maria Address", "Clinic1","maria01", "pass",0,1,0,0,0)){
//			System.out.print("new Staff insert");
//		}

	}

}
