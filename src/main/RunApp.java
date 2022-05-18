package main;

import java.awt.EventQueue;

import control.RestControl;
import db.RestPersistencia;
import view.WPrincipal;
import view.PAddRest;
import view.PModRest;
import view.PSeeRest;

public class RunApp {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				WPrincipal wP = new WPrincipal();
				
				PSeeRest pS = new PSeeRest();
				
				RestPersistencia rP = new RestPersistencia();
				
				PAddRest pAdd = new PAddRest();
				
				PModRest pMod = new PModRest();
				
				RestControl control = new RestControl(wP, pS, rP, pAdd, pMod);
				
				wP.hacerVisible();
				
				wP.setControl(control);
				
				pS.setControl(control);
				
				pAdd.setControl(control);
				
				pMod.setControl(control);
				
			}
		});

	}

}
