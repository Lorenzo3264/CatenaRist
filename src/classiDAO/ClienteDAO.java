package classiDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classi.Cliente;

public class ClienteDAO {

	public Cliente fetchCliente(String email) throws SQLException {
		Cliente cliente = new Cliente();
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM cliente WHERE email = '"+email+"'");
			rs.next();
			cliente.setCodCl(rs.getString("codcl"));
			cliente.setNome(rs.getString("nome"));
			cliente.setCognome(rs.getString("cognome"));
			cliente.setDataN(rs.getString("datan"));
			cliente.setEmail(rs.getString("email"));
			cliente.setPassword(rs.getString("password"));
			cliente.setCellulare(rs.getString("cellulare"));
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException(e);
		}
		return cliente;
		
	}
	
	public void insertCliente(Cliente cliente) throws SQLException {
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement st = con.createStatement();
			st.execute("INSERT INTO cliente VALUES ("+cliente.getCodCl()+",'"+cliente.getEmail()+"','"+cliente.getPassword()+"','"+cliente.getNome()+"','"+cliente.getCognome()+"','"+cliente.getCellulare()+"','"+cliente.getDataN()+"');");
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
	}

	public void updateCliente(Cliente clienteEdit, String codCl) throws SQLException {
		// TODO Auto-generated method stub
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement st = con.createStatement();
			st.execute("UPDATE cliente SET nome = '"+clienteEdit.getNome()+"', cognome ='"+clienteEdit.getCognome()+"', email ='"+clienteEdit.getEmail()+"', password ='"+clienteEdit.getPassword()+"', cellulare ='"+clienteEdit.getCellulare()+"', datan ='"+clienteEdit.getDataN()+"' WHERE codcl = "+codCl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException(e);
		}
	}
}
