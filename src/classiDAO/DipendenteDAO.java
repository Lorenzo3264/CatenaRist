package classiDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import classi.Dipendente;

public class DipendenteDAO {
	public ArrayList<Dipendente> fetchDipendete(int codA) throws SQLException {
		ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente> ();
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM dipendente WHERE coda = '"+codA+"'");
			while(rs.next()){
				Dipendente dipendente = new Dipendente();
				dipendente.setCodA(codA);
				dipendente.setCodD(rs.getInt("codd"));
				dipendente.setEmail(rs.getString("email"));
				dipendente.setCellulare(rs.getString("cellulare"));
				dipendente.setVia(rs.getString("via"));
				dipendente.setCivico(rs.getString("civico"));
				dipendente.setNome(rs.getString("nome"));
				dipendente.setCognome(rs.getString("cognome"));
				dipendente.setRuolo(rs.getString("ruolo"));
				dipendente.setDataN(rs.getString("datan"));
				dipendenti.add(dipendente);
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
		return dipendenti;
	}

	public void deleteDipendente(int codD) throws SQLException {
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement st = con.createStatement();
			st.execute("DELETE FROM dipendente WHERE codd = "+codD+";");
			con.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
	}
}
