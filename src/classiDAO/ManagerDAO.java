package classiDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classi.Cliente;
import classi.Manager;

public class ManagerDAO {
	
	public void updateManager(Manager managerEdit, int codM) throws SQLException {
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement st = con.createStatement();
			st.execute("UPDATE manager SET nome = '"+managerEdit.getNome()+"', cognome ='"+managerEdit.getCognome()+"', email ='"+managerEdit.getEmail()+"', password ='"+managerEdit.getPassword()+"', cellulare ='"+managerEdit.getCellulare()+"', datan ='"+managerEdit.getDataN()+"' WHERE codm = "+codM);
			con.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
	}

	public Manager fetchManager(String email) throws SQLException {
		Manager manager = new Manager();
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM manager WHERE email = '"+email+"'");
			rs.next();
			manager.setCodM(rs.getInt("codm"));
			manager.setCodA(rs.getInt("coda"));
			manager.setNome(rs.getString("nome"));
			manager.setCognome(rs.getString("cognome"));
			manager.setDataN(rs.getString("datan"));
			manager.setEmail(rs.getString("email"));
			manager.setPassword(rs.getString("password"));
			manager.setCellulare(rs.getString("cellulare"));
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
		return manager;
	}
}
