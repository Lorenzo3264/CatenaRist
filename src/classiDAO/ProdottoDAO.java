package classiDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import classi.*;

public class ProdottoDAO {

	public ArrayList<Prodotto> fetchProdotto() throws SQLException {//da fare con le liste?
		ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
		Prodotto prod = new Prodotto();
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM prodotto");
			while(rs.next()) {
				prod.setCodP(rs.getInt("codp"));
				prod.setDescrizione(rs.getString("descrizione"));
				prod.setNome(rs.getString("nome"));
				prod.setPrezzo(rs.getFloat("prezzo"));
				prod.setTipo(rs.getString("tipo"));
				prodotti.add(prod);
			}
			rs.close();
			st.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("errore nella connessione: \n"+e);
			throw new SQLException(e);
		
		}
		return prodotti;
	}
}
