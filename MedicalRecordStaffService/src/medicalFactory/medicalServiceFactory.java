package medicalFactory;

import medicalModel.medicalFunctions;
import medicalService.medicalFunctionsImpl;

public class medicalServiceFactory {
	
	private static medicalFunctions medicalService = new medicalFunctionsImpl();

	  public static medicalFunctions getFactory() {
	    return medicalService;
	  }
}
