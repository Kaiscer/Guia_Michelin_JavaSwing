package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class AccesoDB {
	
	private String	driver;
	private String url;
	
	
	
	public AccesoDB() {
		Properties prop = new Properties(); // Creamos un objeto de tipo properties para poder manejar el configDB
		InputStream is = null; // Usamos un atributo del tipo String el cual sera el canal de comunicacion con la DB
		
		try {
			is = new FileInputStream("ConfigDB.properties");
			//Controlamos la exception en caso de que el nombre o la ruta sean incorrecta
			prop.load(is);
			
			driver = prop.getProperty("DRIVER");
			url = prop.getProperty("URL");
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			// Esta exception la genera si hay un error en la entra o salida con la infomación del fichero 
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName(driver);
		
		Connection con = DriverManager.getConnection(url);
	
		
		
		return con;
	}
	

}
