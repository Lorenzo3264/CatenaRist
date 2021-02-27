package classiDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import classi.Consegna;
import classi.Rider;

public class ConsegnaDAO {

	public void insertConsegna(Consegna consegna) {
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement st = con.createStatement();
			if(consegna.getMetodoP().equals("cartaC")) {
				st.execute("INSERT INTO consegna VALUES("+consegna.getCodC()+","+consegna.getCodCl()+","+consegna.getCodA()+",NULL,"+consegna.getPrezzo()+",NULL,"+consegna.getDataO()+",'"+consegna.getMetodoP()+"','"+consegna.getCodCarta()+"','"+consegna.getNote()+"','"+consegna.getVia()+"','"+consegna.getCivico()+"')");	
			}else {
				st.execute("INSERT INTO consegna VALUES("+consegna.getCodC()+","+consegna.getCodCl()+","+consegna.getCodA()+",NULL,"+consegna.getPrezzo()+",NULL,"+consegna.getDataO()+",'"+consegna.getMetodoP()+"',NULL,'"+consegna.getNote()+"','"+consegna.getVia()+"','"+consegna.getCivico()+"')");
			}
			
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
			rs.close();
			statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException(e);
		}
		return codC;
	}

	public ArrayList<Consegna> fetchConsegna(Rider rider) throws SQLException {
		ArrayList<Consegna> consegne = new ArrayList<Consegna>();
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM consegna WHERE codr = "+rider.getCodR()+"AND datac is null;");
			while(rs.next()) {
				Consegna c = new Consegna();
				c.setCodC(rs.getString("codc"));
				c.setCodA(rs.getInt("coda"));
				c.setCodCl(rs.getInt("codcl"));
				c.setCodR(rs.getInt("codr"));
				c.setDataO(rs.getString("datao"));
				c.setDataC(rs.getString("datac"));
				c.setVia(rs.getString("via"));
				c.setCivico(rs.getString("civico"));
				c.setMetodoP(rs.getString("metodop"));
				c.setPrezzo(rs.getFloat("prezzo"));
				c.setNote(rs.getString("note"));
                consegne.add(c);  			
			}
			rs.close();
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
		return consegne;
	}

	public ArrayList<Consegna> fetchOrdini(Rider rider) throws SQLException {
		ArrayList<Consegna> consegne = new ArrayList<Consegna>();
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from consegna where codr is null and datac is null and coda in ( SELECT coda from attivita natural join collaborazione where codr="+rider.getCodR()+");");
			while(rs.next()) {
				Consegna c = new Consegna();
				c.setCodC(rs.getString("codc"));
				c.setCodA(rs.getInt("coda"));
				c.setCodCl(rs.getInt("codcl"));
				c.setCodR(rs.getInt("codr"));
				c.setDataO(rs.getString("datao"));
				c.setDataC(rs.getString("datac"));
				c.setVia(rs.getString("via"));
				c.setCivico(rs.getString("civico"));
				c.setMetodoP(rs.getString("metodop"));
				c.setPrezzo(rs.getFloat("prezzo"));
				c.setNote(rs.getString("note"));
                consegne.add(c);  			
			}
			rs.close();
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
		return consegne;
	}

	public void completaConsegna(String codC) throws SQLException {
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement st = con.createStatement();
			st.execute("UPDATE consegna SET datac = current_timestamp WHERE codc = "+codC+";");
			con.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
	}

	public void prendiOrdine(String codC, int codR) throws SQLException {
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement st = con.createStatement();
			st.execute("UPDATE consegna SET codr = "+codR+" WHERE codc = "+codC+";");
			con.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
	}
}
