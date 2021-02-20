package classiDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import classi.Attivita;

public class AttivitaDAO {
	public ArrayList<Attivita> fetchAttivita() throws SQLException{
		ArrayList<Attivita> att = new ArrayList<Attivita>();
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM attivita");
			while(rs.next()) {
				Attivita a = new Attivita();
				a.setCodA(rs.getInt("coda"));
				a.setCivico(rs.getString("civico"));
				a.setVia(rs.getString("via"));
				a.setOrariA(rs.getString("oraria"));
				att.add(a);
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException(e);
		}
		return att;
	}
}
