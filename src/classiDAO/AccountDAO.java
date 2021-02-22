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
		Account account = new Account();
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM account WHERE (email = '"+email+"' AND password = '"+password+"')");
			rs.next();
			account.setEmail(rs.getString("email"));
			account.setPassword(rs.getString("password"));
			account.setPermessi(rs.getString("permessi"));
			rs.close();
			st.close();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
		
		return account;
	}
	
}
