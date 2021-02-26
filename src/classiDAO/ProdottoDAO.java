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
		
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CatenaRist","postgres","admin");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM prodotto ORDER BY tipo, prezzo");
			while(rs.next()) {
				Prodotto prodotto = new Prodotto();
				prodotto.setCodP(rs.getInt("codp"));
				prodotto.setDescrizione(rs.getString("descrizione"));
				prodotto.setNome(rs.getString("nome"));
				prodotto.setPrezzo(rs.getFloat("prezzo"));
				prodotto.setTipo(rs.getString("tipo"));
				prodotti.add(prodotto);
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
