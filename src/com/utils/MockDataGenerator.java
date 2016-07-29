package com.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.beans.UserAccount;
import com.dao.UserAccountDao;

public class MockDataGenerator {

	public static void main(String[] args){
		
		String[] names = {
				"Hayley Hukill",
				"Gianna Schall", 
				"Amina Dunston", 
				"Caroline Becton", 
				"Carola Braden", 
				"Foster Farraj", 
				"Alexander Carlucci", 
				"Tasha Poole", 
				"Regine Schaber", 
				"Sara Ballantyne", 
				"Alix Faries", 
				"Cherlyn Scalzo", 
				"Booker Mehring", 
				"Larisa Vidaurri", 
				"Kam Hedman", 
				"Danika Borchers", 
				"Carman Bresnahan", 
				"Lani Mengel", 
				"Kimbery Merle", 
				"Belva Thames", 
				"Thurman Revels", 
				"Xenia Mastropietro", 
				"Tuan Mccutchen", 
				"Terrance Grose", 
				"Lory Lewter", 
				"Myles Mancini", 
				"Audria Dunton", 
				"Jeana Gideon", 
				"Carry Cassano", 
				"May Worster", 
				"Page Goltz", 
				"Juliette Stembridge", 
				"Sumiko Ferreri", 
				"Russel Sadberry", 
				"Ja Priolo", 
				"Alexia Hope", 
				"Hal Odell", 
				"Fernando Hornung", 
				"Sandie Zackery", 
				"Aileen Rosen", 
				"Sixta Haddox", 
				"Amada Boring", 
				"Tam Krzeminski", 
				"Shavonda Mccaffrey", 
				"Shameka Bryden", 
				"Shonta Layfield", 
				"Demetria Zupan", 
				"Adelina Omarah", 
				"Barbara Rathjen", 
				"Wilfred Toy",
				"Corine Kleve", 
				"Milissa Ripple", 
				"Deedra Puterbaugh", 
				"Angelic Demming", 
				"Mica Gowins", 
				"Dell Jeon", 
				"Sharron Ho", 
				"Shanti Schubert", 
				"Maude Burry", 
				"Kathaleen Greaver", 
				"Alma Marie", 
				"Maira Carver", 
				"Suzann Stumbo", 
				"Carletta Wakeland", 
				"Franklin Polly", 
				"Lita Monterroso", 
				"Nathanial Sund", 
				"Ranae Stanley", 
				"Allena Figaro", 
				"Peggy Post", 
				"Tempie Christoff", 
				"Rosalyn Howerton", 
				"Carolynn Redwood", 
				"Loris Lopresti", 
				"Clarita Toombs", 
				"Marla Nodine", 
				"Fredric Bosak", 
				"Kacie Alcon", 
				"Carlie Owen", 
				"Rosella Alequin", 
				"Allyn Hamlett", 
				"Ruth Schumacher", 
				"Cole Cantwell", 
				"Melodee Wint", 
				"Manie Brumbaugh", 
				"Pamela Hyatt", 
				"Teena Dinatale", 
				"Izetta Toews", 
				"Nilsa Silverio", 
				"Kris Olveda", 
				"Junko Pedone", 
				"Velda Davisson", 
				"Audra Bitting", 
				"Vincent Morlan", 
				"Oralia Herring", 
				"Sachiko Samples", 
				"Diana Pitzer", 
				"Shala Mcgregor", 
				"Mitchel Viau", 
				"Marguerita Milera"
		};
		
		Connection connection = null;
		try {
			connection = MySQLConnectionFactory.createConnection();
			UserAccountDao dao = new UserAccountDao();
			for(String name : names){
				String[] parts = name.split(" ");
				String username = parts[1] + parts[0];
				if(!dao.usernameExists(username, connection)){
					UserAccount userAccount = new UserAccount(
							username, 
							parts[1],
							parts[1],
							parts[0],
							""
							);
					dao.create(userAccount, connection);
				}
				connection.commit();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(connection != null){
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
