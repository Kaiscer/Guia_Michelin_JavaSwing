package db;

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
									RestContract.COLUMN_PREC_MIN + RestContract.COLUMN_PREC_MAX ;
									
		
		
		
		
		
		
		
		
		
		
		
		return listRest;
	}

}
