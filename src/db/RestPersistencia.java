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
		
		String query  = "SELECT " + RestContract.COLUMN_NOMBRE + ", " + 
									RestContract.COLUMN_CIUDAD + ", " + 
									RestContract.COLUMN_DISTIN + ", " + 
									RestContract.COLUMN_COCINA + ", " +
									RestContract.COLUMN_PREC_MIN + ", " + 
									RestContract.COLUMN_PREC_MAX + " FROM " + RestContract.NOMBRE_TABLA;
									
		
		Connection con = null;
		Statement stml = null;
		ResultSet rslt = null;
		
		
		try {
			con = acceso.getConnection();
			
			stml = con.createStatement();
			
			rslt = stml.executeQuery(query);
			
		Rest rest;
		String nombre;
		String ciudad;
		String disti;
		String cocina;
		String pMax;
		String pMin;
		
		while (rslt.next()) {
			
			nombre = rslt.getString(2);
			ciudad = rslt.getString(3);
			disti = rslt.getString(4);
			cocina = rslt.getString(5);
			pMax = rslt.getString(6);
			pMin = rslt.getString(7);
			
			
		}
					
					
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		return listRest;
	}

}
