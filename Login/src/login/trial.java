package login;



import loginModel.loginFunction;
import loginFactory.loginFactory;

public class trial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		loginFunction factory = loginFactory.getFactory();
		int a[] =factory.login("maria01", "pass");
		System.out.println(a[0]+" "+a[1]);
	}

}
