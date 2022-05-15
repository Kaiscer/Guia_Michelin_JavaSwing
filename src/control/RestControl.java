package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import db.RestPersistencia;
import model.Rest;
import view.WPrincipal;
import view.PSeeRest;

public class RestControl implements ActionListener {
	
	private static ArrayList<Rest> listRest;
		WPrincipal wP;
		PSeeRest pS;
		RestPersistencia rP;
		
		
		

	public RestControl(WPrincipal wP, PSeeRest wS, RestPersistencia rP) {
			this.wP = wP;
			this.pS = wS;
			this.rP = rP;
			listRest = new ArrayList<Rest>();
		}




	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JMenuItem) {
			
			if (e.getActionCommand().equals(WPrincipal.ITEM_SEE)) {
//				System.out.println("Test");
				wP.uploadPanel(pS);
				
			}else if (e.getActionCommand().equals(WPrincipal.ITEM_EXIT)) {
				
				int option = JOptionPane.showConfirmDialog(wP, "¿Estas seguro que deseas salir?", "Confirmar Salida", 
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		}else if (e.getSource() instanceof JButton){
			if (e.getActionCommand().equals(PSeeRest.BTN_SEE)) {
			
				
				
				for (int i = 0; i < PSeeRest.REGIONES.length; i++) {
					
					
				}
					
					listRest = rP.seeRest();
				
					
			
				
				
				
			}
			
		}
		
		

	}

}
