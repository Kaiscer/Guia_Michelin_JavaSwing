package db;

import java.sql.*;

import java.util.ArrayList;



import model.Rest;
import view.PSeeRest;

public class RestPersistencia {
	
	private AccesoDB acceso;
	
	public RestPersistencia() {
		
		acceso = new AccesoDB();
	}

	public ArrayList<Rest> requestData() {
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
					if (stml != null) stml.close();	
					if (con != null) con.close();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				} {
				
			}
		}
		
		
		
		return listRegiones;
	}


//	
	public ArrayList<Rest> filterTable(int distin, String reg) {
		ArrayList<Rest> listFilter = new ArrayList<Rest>();
		 
		// distin = 0 y reg = "TODAS"
		String query = "SELECT * " + 
						" FROM " + RestContract.NOMBRE_TABLA;
		if (distin != 0 && reg.equals(PSeeRest.REGIONES)) {
			query += " WHERE " + RestContract.COLUMN_DISTIN + " = ?" ;
			
		} else if (distin == 0 && !reg.equals(PSeeRest.REGIONES)) {
			query += " WHERE " + RestContract.COLUMN_REGION + " = ?" ;
			
		} else if (distin != 0 && !reg.equals(PSeeRest.REGIONES)) {
			query += " WHERE " + RestContract.COLUMN_DISTIN + " = ?" 
					+ " and " + RestContract.COLUMN_REGION + " = ?" ;
		}
						
		
		Connection con = null;
		PreparedStatement pstml = null;
		ResultSet rstl = null;
		
		
		try {
			con = acceso.getConnection();
			pstml = con.prepareStatement(query);
			
			if (distin != 0 && reg.equals(PSeeRest.REGIONES)) {
				pstml.setInt(1, distin);
				
			} else if (distin == 0 && !reg.equals(PSeeRest.REGIONES)) {
				pstml.setString(1, reg);
				
			} else if (distin != 0 && !reg.equals(PSeeRest.REGIONES)) {
				pstml.setInt(1, distin);
				pstml.setString(2, reg);
			}
			
		
			rstl = pstml.executeQuery();
			
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
			
			while (rstl.next()) {
				
				id = rstl.getInt(1);
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
				
				rest = new Rest(id, nombre, region, ciudad, disti, direc, pMin, pMax, cocina, telefono, web);
				
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

	public int deleteRest(int id) {
		int result = 0;
		
		String query = "DELETE FROM " + RestContract.NOMBRE_TABLA + 
						" WHERE " + RestContract.COLUMN_ID + " = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = acceso.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			
			result = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		}finally {
			
				try {
					if (pstmt != null)pstmt.close();
					if (con != null)con.close();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				} {
				
			}
			
		}
		
		
		
		return result;
	}

	public String searchName(String name) {
		String newName = null;
		
		String query = "SELECT " + RestContract.COLUMN_NOMBRE + 
						" FROM " + RestContract.NOMBRE_TABLA + 
						" WHERE " + RestContract.COLUMN_NOMBRE + " = ?";
//		System.out.println(query);
//		System.out.println(name);
//		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rstl = null ;
		
		try {
			con = acceso.getConnection();
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, name);
			
			rstl = pstmt.executeQuery();
			
			if (rstl.next()) {
				
				newName = rstl.getString(1);
				
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		}finally {
			
			
				try {
					if (rstl != null)rstl.close();
					if (pstmt != null)pstmt.close();
					if (con != null)con.close();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				} {
				
			}
		}
		
		
		
		
		
		return newName;
	}

	public int addNewRest(Rest rest) {
		int result = 0;
		
		String query = "INSERT INTO " + RestContract.NOMBRE_TABLA + " (" + RestContract.COLUMN_NOMBRE + ", " 
									+ RestContract.COLUMN_REGION + ", " + RestContract.COLUMN_CIUDAD + ", " 
									+ RestContract.COLUMN_DISTIN + ", " + RestContract.COLUMN_DIREC + ", " 
									+ RestContract.COLUMN_PREC_MIN + ", " + RestContract.COLUMN_PREC_MAX + ", " 
									+ RestContract.COLUMN_COCINA + ", " + RestContract.COLUMN_TELEF + ", " + RestContract.COLUMN_WEB 
									+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = acceso.getConnection();
			pstmt = con.prepareStatement(query);
			
			
			pstmt.setString(1, rest.getNombre());
			pstmt.setString(2, rest.getRegion());
			pstmt.setString(3, rest.getCiudad());
			pstmt.setInt(4, rest.getDistincion());
			pstmt.setString(5, rest.getDireccion());
			pstmt.setDouble(6, rest.getPrecio_Min());
			pstmt.setDouble(7, rest.getPrecio_Max());
			pstmt.setString(8, rest.getCocina());
			pstmt.setString(9, rest.getTelefono());
			pstmt.setString(10, rest.getWeb());
			
			result = pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			
			e.printStackTrace();
		}finally {
			
			
				try {
					if (pstmt != null)pstmt.close();
					if (con != null)con.close();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			
			
		}
		
		
		
		
		
		return result;
	}

	

	

	

}
