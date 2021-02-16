package classiDAO;
import java.sql.Connection;
import java.sql.DriverManager;

import org.postgresql.*;



public class AccountDAO {

	
	public AccountDAO() {
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist");
		}catch(Exception e) {
			
		}
	}
	
	public boolean fetchAccount() {
		return false; //da cambiare
	}
	
}
