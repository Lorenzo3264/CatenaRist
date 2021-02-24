package classiDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classi.Consegna;

public class ConsegnaDAO {
	public boolean fetchConsegna() { //da vedere con le liste per permettere al rider di avere più consegne da vedere
		return false;// da cambiare
	}

	public void insertConsegna(Consegna consegna) {
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement st = con.createStatement();
			st.execute("INSERT INTO consegna VALUES("+consegna.getCodC()+","+consegna.getCodCl()+","+consegna.getCodA()+",NULL,"+consegna.getPrezzo()+",NULL,"+consegna.getDataO()+",'"+consegna.getMetodoP()+"','"+consegna.getCodCarta()+"','"+consegna.getNote()+"','"+consegna.getVia()+"','"+consegna.getCivico()+"')");
			con.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int fetchCurrentCodC() throws SQLException {
		int codC = 0;
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT codc FROM consegna WHERE codc = (SELECT last_value from seq_codc)");
			rs.next();
			codC = rs.getInt("codc");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException(e);
		}
		return codC;
	}
}
