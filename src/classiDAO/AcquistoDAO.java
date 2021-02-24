package classiDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import classi.Acquisto;

public class AcquistoDAO {

	public boolean fetchAcquisto() { //con le liste?
		return false;// da cambiare
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException(e);
		}
		
	}
}
