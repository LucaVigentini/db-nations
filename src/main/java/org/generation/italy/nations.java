package org.generation.italy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class nations {

	public static void main(String[] args) {

		String URL = "jdbc:mysql://localhost:3306/db_nation";
		String USER = "root";
		String PASSWORD = "rootpassword";

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

//			System.out.println(con.isClosed());
			Scanner scn = new Scanner(System.in);
			System.out.println("Inserisci una stringa da cercare all'interno delle nazioni");
			String nazione = scn.nextLine();
			String sql = "select c.name, r.region_id, r.name, c2.name \r\n" + "from countries c\r\n"
					+ "join regions r on c.region_id = r.region_id\r\n"
					+ "join continents c2 on r.continent_id  = c2.continent_id\r\n" + "WHERE c.name LIKE ?\r\n"
					+ "order by c.name asc;";

			try (PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setString(1, "%" + nazione + "%");

				try (ResultSet rs = ps.executeQuery()) {

					while (rs.next()) {
						System.out.print(rs.getString(1) + " ");
						System.out.print(rs.getString(2) + " ");
						System.out.print(rs.getString(3) + " ");
						System.out.println(rs.getString(4));
					}

				}

			}
			scn.close();
		} catch (SQLException e) {
			System.out.println("Si è verificato un errore!");
			e.printStackTrace();
		}

	}

}
 