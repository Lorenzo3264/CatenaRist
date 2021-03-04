package classiDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import classi.Acquisto;

public class AcquistoDAO {

	public ArrayList<Acquisto> fetchAcquisto(int codA) throws SQLException {
		ArrayList<Acquisto> acquisti = new ArrayList<Acquisto>();
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist", "postgres",
					"admin");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT A.codc, A.codp, quantita FROM acquisto AS A JOIN consegna AS C ON A.codc = C.codc WHERE C.coda = "+codA+";");
			while (rs.next()) {
				Acquisto acquisto = new Acquisto();
				acquisto.setCodC(rs.getInt("codc"));
				acquisto.setCodP(rs.getInt("codp"));
				acquisto.setQuantita(rs.getInt("quantita"));
				acquisti.add(acquisto);
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
		return acquisti;
	}
	
	public void insertAcquisto(ArrayList<Acquisto> acquisto, int codC) throws SQLException {
		String sql = "INSERT INTO acquisto VALUES";
		Iterator<Acquisto> acq = acquisto.iterator();
		
		while(acq.hasNext()) {
			Acquisto a = acq.next();
			sql = sql+"("+a.getCodP()+","+codC+","+a.getQuantita()+"),";
		}
		sql = sql.substring(0, sql.length()-1);
		sql = sql+";";
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement statement = con.createStatement();
			statement.execute(sql);
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
		
	}
}
