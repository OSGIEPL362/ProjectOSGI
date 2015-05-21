import static org.junit.Assert.*;
import medicalFactory.medicalServiceFactory;
import medicalModel.medicalFunctions;

import org.hamcrest.Factory;
import org.junit.Test;


public class TestForMedicalRecordStaff {

	@Test
	public void checkStringTest() {
		medicalFunctions test = medicalServiceFactory.getFactory();
		assertTrue("[ OK ]", test.checkString("I am a string"));
		assertFalse("[ error checkStringTest ]",test.checkString("I am not a string 1234"));
	}
	
	@Test
	public void checkNumberTest(){
		medicalFunctions test = medicalServiceFactory.getFactory();
		assertTrue("[ OK ]", test.checkNumber("99123465376498"));
		assertFalse("[ error checkNumberTest ]",test.checkNumber("I am not number"));
	}
	
	@Test
	public void checkEmailTest(){	
		medicalFunctions test = medicalServiceFactory.getFactory();
		assertTrue("[ OK ]", test.checkEmailAddress("pparthenhs@gmail.com"));
		assertFalse("[ error checkEmailTest]",test.checkEmailAddress("I am not email"));
	}
	
	
}
