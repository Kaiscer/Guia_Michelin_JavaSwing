package db;

import java.sql.*;

import java.util.ArrayList;



import model.Rest;

public class RestPersistencia {
	
	private AccesoDB acceso;
	
	public RestPersistencia() {
		
		acceso = new AccesoDB();
	}

	public ArrayList<Rest> seeRest() {
		ArrayList<Rest> listRest = new ArrayList<Rest>();
		
		String query  = "SELECT * " +  " FROM " + RestContract.NOMBRE_TABLA;
									
		
		Connection con = null;
		Statement stml = null;
		ResultSet rslt = null;
		
		
		try {
			con = acceso.getConnection();
			
			stml = con.createStatement();
			
			rslt = stml.executeQuery(query);
			
		Rest rest;
		int id;
		String nombre;
		String region;
		String ciudad;
		int disti;
		String direc;
		String cocina;
		double pMax;
		double pMin;
		String telefono;
		String web;
		
		while (rslt.next()) {
			
			id = rslt.getInt(1);
			nombre = rslt.getString(2);
			region = rslt.getString(3);
			ciudad = rslt.getString(4);
			disti = rslt.getInt(5);
			direc = rslt.getString(6);
			pMin = rslt.getDouble(7);
			pMax = rslt.getDouble(8);
			cocina = rslt.getString(9);
			telefono = rslt.getString(10);
			web = rslt.getString(11);
			
			rest = new Rest(id, nombre, region, ciudad, disti, direc, pMin, pMax, cocina, telefono, web);
			
			listRest.add(rest);
		}
					
					
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos");
			e.printStackTrace();
		}finally {
			try {
				if (rslt != null) rslt.close(); 
				if (stml != null) stml.close();	
				if (con != null) con.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		return listRest;
	}
	

}
