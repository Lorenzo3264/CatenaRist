package classiDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classi.Rider;

public class RiderDAO {

	public Rider fetchRider(String email) throws SQLException{
		Rider rider = new Rider();
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM rider WHERE email = '"+email+"'");
			rs.next();
			rider.setCodR(rs.getInt("codr"));
			rider.setNome(rs.getString("nome"));
			rider.setCognome(rs.getString("cognome"));
			rider.setDataN(rs.getString("datan"));
			rider.setEmail(rs.getString("email"));
			rider.setPassword(rs.getString("password"));
			rider.setCellulare(rs.getString("cellulare"));
			rider.setMezzo(rs.getString("mezzo"));
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
		return rider;
	}
	
	public boolean updateRider() {
		return false;
	}
}
