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
			
			rest = new Rest(nombre, region, ciudad, disti, direc, pMin, pMax, cocina, telefono, web);
			
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

	public String findRest(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> requestRegiones() {
		ArrayList<String> listRegiones = new ArrayList<String>();
		
		String query = "SELECT DISTINCT " + RestContract.COLUMN_REGION + " FROM " + RestContract.NOMBRE_TABLA;
		
		Connection con = null;
		Statement stml = null;
		ResultSet rstl = null;
		
		try {
			
			con = acceso.getConnection();
			
			stml = con.createStatement();
			
			rstl = stml.executeQuery(query);
			
			
			String regiones;
			
			while (rstl.next()) {
				
				regiones = rstl.getString(RestContract.COLUMN_REGION);
				
				listRegiones.add(regiones);
			
			}
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			 
				try {
					if (rstl != null)rstl.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} {
				
			}
		}
		
		
		
		return listRegiones;
	}

	public ArrayList<Rest> filterTable(int distin) {
		ArrayList<Rest> listFilter = new ArrayList<Rest>();
		 
		String query = "SELECT * " + " FROM " + RestContract.NOMBRE_TABLA + " WHERE " + RestContract.COLUMN_DISTIN + " = ?";
		
		Connection con = null;
		PreparedStatement pstml = null;
		ResultSet rstl = null;
		
		
		try {
			con = acceso.getConnection();
			pstml = con.prepareStatement(query);
			
			pstml.setInt(1, distin);
			
			rstl = pstml.executeQuery();
			
			Rest rest;
			
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
			
			while (rstl.next()) {
				
				
				nombre = rstl.getString(2);
				region = rstl.getString(3);
				ciudad = rstl.getString(4);
				disti = rstl.getInt(5);
				direc = rstl.getString(6);
				pMin = rstl.getDouble(7);
				pMax = rstl.getDouble(8);
				cocina = rstl.getString(9);
				telefono = rstl.getString(10);
				web = rstl.getString(11);
				
				rest = new Rest(nombre, region, ciudad, disti, direc, pMin, pMax, cocina, telefono, web);
				
				listFilter.add(rest);
			}
			
			
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				if (rstl != null) rstl.close(); 
				if (pstml != null) pstml.close();	
				if (con != null) con.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		return listFilter;
	}
	

}
