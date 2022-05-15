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
import view.PAddRest;
import view.PSeeRest;

public class RestControl implements ActionListener {
	
	private static ArrayList<Rest> listRest;
		WPrincipal wP;
		PSeeRest pSee;
		RestPersistencia rP;
		PAddRest pAdd;
		
		
		

	public RestControl(WPrincipal wP, PSeeRest wS, RestPersistencia rP, PAddRest pAdd) {
			this.wP = wP;
			this.pSee = wS;
			this.rP = rP;
			this.pAdd = pAdd;
			listRest = new ArrayList<Rest>();
		}




	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JMenuItem) {
			
			if (e.getActionCommand().equals(WPrincipal.ITEM_SEE)) {
//				System.out.println("Test");
				wP.uploadPanel(pSee);
				pSee.hacerVisible(false);
		
				
			}else if (e.getActionCommand().equals(WPrincipal.ITEM_ADD)) {
				wP.uploadPanel(pAdd);
				
				
			}else if (e.getActionCommand().equals(WPrincipal.ITEM_EXIT)) {
				
				int option = JOptionPane.showConfirmDialog(wP, "¿Estas seguro que deseas salir?", "Confirmar Salida", 
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		}else if (e.getSource() instanceof JButton){
			if (e.getActionCommand().equals(PSeeRest.BTN_SEE)) {
				if (pSee.getcmbRegion().getSelectedIndex() == 0 && pSee.getCmbDistincion().getSelectedIndex()== 0) {
					//System.out.println("testButton");
					listRest = rP.seeRest();
					pSee.fillTable(listRest);
					pSee.hacerVisible(true);
				}									

			
				
				
				
			}
			
		}
		
		

	}

}
