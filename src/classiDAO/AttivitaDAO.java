package classiDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import classi.Attivita;
import classi.Collaborazione;
import classi.Rider;

public class AttivitaDAO {
	public ArrayList<Attivita> fetchAttivita() throws SQLException{
		ArrayList<Attivita> attivita = new ArrayList<Attivita>();
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
				attivita.add(a);
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
		return attivita;
	}

	public ArrayList<Attivita> fetchAttivita(Rider rider) throws SQLException {
		ArrayList<Attivita> collab = new ArrayList<Attivita>();
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT coda, oraria, via, civico FROM attivita NATURAL JOIN collaborazione WHERE codR='"+rider.getCodR()+"';");
			while(rs.next()) {
				Attivita a = new Attivita();
				a.setCodA(rs.getInt("coda"));
				a.setCivico(rs.getString("civico"));
				a.setVia(rs.getString("via"));
				a.setOrariA(rs.getString("oraria"));
				collab.add(a);
				rs.close();
				st.close();
				con.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new SQLException(e);
		}
		return collab;
	}
}
