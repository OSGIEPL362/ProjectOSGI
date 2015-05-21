package HealthModel;

import java.sql.ResultSet;

public interface HealthFuctions {
	
	ResultSet getClinics();
	String [][] getWeaklyReaport(String clinic);

}
