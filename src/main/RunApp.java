package main;

import java.awt.EventQueue;

import control.RestControl;
import db.RestPersistencia;
import view.WPrincipal;
import view.PAddRest;
import view.PSeeRest;

public class RunApp {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				WPrincipal wP = new WPrincipal();
				
				PSeeRest wS = new PSeeRest();
				
				RestPersistencia rP = new RestPersistencia();
				
				PAddRest pAdd = new PAddRest();
				
				RestControl control = new RestControl(wP, wS, rP, pAdd);
				
				wP.hacerVisible();
				
				wP.setControl(control);
				
				wS.setControl(control);
				
				pAdd.setControl(control);
				
			}
		});

	}

}
