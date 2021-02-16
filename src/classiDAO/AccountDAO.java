package classiDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import classi.Account;

import org.postgresql.*;



public class AccountDAO {
	
	public Account fetchAccount(String email, String password) throws SQLException {
		Account a = new Account();
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM account WHERE (email = '"+email+"' AND password = '"+password+"')");
			rs.next();
			a.setEmail(rs.getString("email"));
			a.setPassword(rs.getString("password"));
			a.setPermessi(rs.getString("permessi"));
			rs.close();
			st.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("errore nella connessione: \n"+e);
			throw new SQLException(e);
		}
		
		return a;
	}
	
}
