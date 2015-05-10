package loginFactory;
import loginModel.loginFunction;
import loginService.loginImpl;

public class loginFactory {

	
		
		private static loginFunction login = new loginImpl();
		public static loginFunction getFactory(){
			return login;
		}
	

}


