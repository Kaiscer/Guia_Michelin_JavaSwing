package main;

import java.awt.EventQueue;

import control.AppController;
import view.WPrincipal;
import view.PSeeRest;

public class RunApp {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				WPrincipal wP = new WPrincipal();
				
				PSeeRest wS = new PSeeRest();
				
				AppController control = new AppController(wP, wS);
				
				wP.hacerVisible();
				
				wP.setControl(control);
				
				wS.setControl(control);
				
			}
		});

	}

}
