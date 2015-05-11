package nurseFactory;

import nurseModel.nurseFunctions;
import nurseService.nurseFuctionImpl;
import doctorsModel.doctorFunctions;
import doctorsService.doctorFunctionImpl;

public class nurseServiceFactory {
	private static nurseFunctions  nurseService = new nurseFuctionImpl();

	public static  nurseFunctions getFactory() {
	    return nurseService;
	  }
}
