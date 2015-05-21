package HealthFactory;

import HealthModel.HealthFuctions;
import HealthService.healthServiceImpl;



public class HealthFactoryService {

	private static HealthFuctions healthService = new healthServiceImpl();

	public static HealthFuctions getFactory() {
	    return healthService;
	  }
}
