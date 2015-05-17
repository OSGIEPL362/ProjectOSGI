package nurseFactory;

import nurseModel.nurseFunctions;
import nurseService.nurseFuctionImpl;


public class nurseServiceFactory {
	private static nurseFunctions  nurseService = new nurseFuctionImpl();

	public static  nurseFunctions getFactory() {
	    return nurseService;
	  }
}
