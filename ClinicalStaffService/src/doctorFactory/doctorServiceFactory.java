package doctorFactory;

import doctorsModel.doctorFunctions;
import doctorsService.doctorFunctionImpl;

public class doctorServiceFactory {
	private static doctorFunctions doctorService = new doctorFunctionImpl();

	public static doctorFunctions getFactory() {
	    return doctorService;
	  }
}
