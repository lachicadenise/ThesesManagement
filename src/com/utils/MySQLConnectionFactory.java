package com.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class MySQLConnectionFactory {
	
	private static String KEY_SERVER_NAME = "hostname";
	private static String KEY_DATABASE_NAME = "databaseName";
	private static String KEY_DATABASE_USERNAME = "databaseUsername";
	private static String KEY_DATABASE_PASSWORD = "databasePassword";
	
	private static Properties properties;
	private static MysqlDataSource dataSource;
	
	private static void loadProperties() throws IOException{
		properties = new Properties();
		InputStream inputStream = new FileInputStream("connection.properties");
		properties.load(inputStream);
		inputStream.close();
	}
	
	private static void createProperties() throws IOException{
		if(properties == null){
			properties = new Properties();
			properties.setProperty(KEY_SERVER_NAME, "localhost");
			properties.setProperty(KEY_DATABASE_NAME, "thesesdb");
			properties.setProperty(KEY_DATABASE_USERNAME, "root");
			properties.setProperty(KEY_DATABASE_PASSWORD, "Weaklings11235!");
			OutputStream outputStream = new FileOutputStream("connection.properties");
			properties.store(outputStream, "Theses Database Management Database Connection Properties");
			outputStream.close();
		}
	}
	
	public static Connection createConnection() throws 
			ClassNotFoundException, 
			IOException, SQLException{
		
		if(properties == null){
			try{
				loadProperties();
			} catch(IOException e){
				e.printStackTrace();
				createProperties();
			}
		}
		
		if(dataSource == null){
			dataSource = new MysqlDataSource();
			dataSource.setServerName(properties.getProperty(KEY_SERVER_NAME));
			dataSource.setDatabaseName(properties.getProperty(KEY_DATABASE_NAME));
			dataSource.setUser(properties.getProperty(KEY_DATABASE_USERNAME));
			dataSource.setPassword(properties.getProperty(KEY_DATABASE_PASSWORD));
		}

		Connection connection = dataSource.getConnection();
		connection.setAutoCommit(false);
		return connection;
		
	}
	
}
