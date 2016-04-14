package it.polito.tdp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class AnagrammaDAO {

	public boolean cercaInDAO(String s) {
	
		boolean result=false;
		
		final String sql = "SELECT * FROM parola where nome=?";

		int contatore=0;
	

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, s);
			

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				contatore++;
				
			}
			if(contatore==0){result=false;}else{result=true;}
			st.close();
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
		
		
	}

	
	
	
}
