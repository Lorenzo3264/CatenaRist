package classiDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classi.Cliente;

public class ClienteDAO {

	public Cliente fetchCliente(String email) throws SQLException {
		Cliente cl = new Cliente();
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM cliente WHERE email = '"+email+"'");
			rs.next();
			cl.setCodCl(rs.getString("codcl"));
			cl.setNome(rs.getString("nome"));
			cl.setCognome(rs.getString("cognome"));
			cl.setDataN(rs.getString("datan"));
			cl.setEmail(rs.getString("email"));
			cl.setPassword(rs.getString("password"));
			cl.setCellulare(rs.getString("cellulare"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException(e);
		}
		return cl;// da cambiare
		
	}
	
	public boolean insertCliente(Cliente cl) {
		return false;// da cambiare
	}
}
