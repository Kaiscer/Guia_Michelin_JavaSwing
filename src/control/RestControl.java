package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import db.RestPersistencia;
import model.Rest;
import view.WPrincipal;
import view.PAddRest;
import view.PModRest;
import view.PSeeRest;

public class RestControl implements ActionListener {
	
	private static ArrayList<Rest> listRest;
		WPrincipal wP;
		PSeeRest pSee;
		RestPersistencia rP;
		PAddRest pAdd;
		PModRest pMod;
		
		
		

	public RestControl(WPrincipal wP, PSeeRest pSee, RestPersistencia rP, PAddRest pAdd, PModRest pMod) {
			this.wP = wP;
			this.pSee = pSee;
			this.rP = rP;
			this.pAdd = pAdd;
			this.pMod = pMod;
			listRest = new ArrayList<Rest>();
			
			
			
		}




	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JMenuItem) {
			
			if (e.getActionCommand().equals(WPrincipal.ITEM_SEE)) {
				pSee.fillCmbRegion(rP.fillRegiones());
				wP.uploadPanel(pSee);
				pSee.hacerVisible(false);
		
				
			}else if (e.getActionCommand().equals(WPrincipal.ITEM_ADD)) {
				wP.uploadPanel(pAdd);
				pAdd.cleanForm();
				
				
			}else if (e.getActionCommand().equals(WPrincipal.ITEM_MODIFICACION)) {
				pMod.enableComponents(false);
				wP.uploadPanel(pMod);
				pMod.cleanForm();
				
			}else if (e.getActionCommand().equals(WPrincipal.ITEM_EXIT)) {
				
				int option = JOptionPane.showConfirmDialog(wP, "¿Estas seguro que deseas salir?", "Confirmar Salida", 
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
			
			
			
			
			
		}else if (e.getSource() instanceof JButton){
			if (e.getActionCommand().equals(PSeeRest.BTN_SEE)) {
				SeeRest();
				
			}else if (e.getActionCommand().equals(PSeeRest.BTN_DELETE)) {
				
				deleteRest();

					
			}else if (e.getActionCommand().equals(PAddRest.BTN_ADD)) {
				
				addRest();
			
			}else if (e.getActionCommand().equals(PAddRest.BTN_CLEAN)) {
				pAdd.cleanForm();
				
			}else if (e.getActionCommand().equals(PModRest.BTN_SEARCH)) {
				searchModRes();
				
			}else if (e.getActionCommand().equals(PModRest.BTN_SAVE_MOD)) {
				updateData();
				
			}else if (e.getActionCommand().equals(PModRest.BTN_CANCEL_MOD)) {
				pMod.cleanForm();
				pMod.enableComponents(false);
			}
			
		}
		
		

	}




	private void updateData() {
		
		Rest restMod = pMod.exceptionTxt();
		
		if (restMod != null) {
			
			int result = rP.updateRes(restMod);
			
			if (result > 0) {
				
				pMod.setMsg("Los datos se actualizaron correctamente");
				pMod.cleanForm();
				
			}else {
				pMod.setError("Error!!! No fue posible actualizar los datos");
			}
		}
		
	}

	private void searchModRes() {
		String name = pMod.txtNombreMod.getText().trim();
		
		
		if (!name.isBlank()) {			

			
			Rest rest = rP.getRest(name);
			
			if (rest != null) {
								
				pMod.fillRestMod(rest);
				pMod.enableComponents(true);
				
				
				
			}else {
				pMod.setError("El restaurante (" + pMod.txtNombreMod.getText() +  ") no se encuentra registrado");
			}
			
			
		}else {
			
			pMod.setError("Debes introducir el nombre del restaurante");
		}
	}
	




	private void addRest() {
		Rest rest = pAdd.newDataRest();
		
		if (rest != null) {
			
			String seeNameDb = rP.searchName(rest.getNombre());
			
			if (seeNameDb != null) {
				pAdd.setError("El restaurante ya se encutra registrado en la base de datos");
			}else {
				int newRest = rP.addNewRest(rest);
				
				
				if (newRest > 0) {
					pAdd.setMsg("Restaurante añadido con exito");
					pAdd.cleanForm();
				}else {
					pAdd.setError("No se ha podido resgistrar el nuevo Restaurante");
				}
			}
			
			
		}
		
	}

	private void SeeRest() {
		if (pSee.getCmbRegion().getSelectedIndex() == 0 && pSee.getCmbDistincion().getSelectedIndex()== 0) {
			
			listRest = rP.requestData();
			
			
		} else if (pSee.getCmbRegion().getSelectedIndex() == 0) {
				
			listRest = rP.filterTable(pSee.getCmbDistincion().getSelectedIndex(), (String) pSee.getCmbRegion().getSelectedItem());
			
			
		} else if (pSee.getCmbDistincion().getSelectedIndex()== 0) {
			
			listRest = rP.filterTable(pSee.getCmbDistincion().getSelectedIndex(), (String) pSee.getCmbRegion().getSelectedItem());
		
			
		}else  {
			
			listRest = rP.filterTable(pSee.getCmbDistincion().getSelectedIndex(), (String)pSee.getCmbRegion().getSelectedItem());
			
			
		}
		
		if (listRest.isEmpty()) {
			pSee.setError("No se han encontrado datos para el filtro introducido");
			pSee.hacerVisible(false);
		} else {
			pSee.fillTable(listRest);
			pSee.hacerVisible(true);
		}
	}
	private void deleteRest() {
		Rest rest = pSee.restSelect();
		
		if (rest == null) {
			pSee.setError("Debe seleccionar el registro a eliminar");
			
		}else {
			
			int answer = JOptionPane.showConfirmDialog(pSee, "Se va a elimiar el registro seleccionado¿deseas continuar?","Confirmación",
					JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
				if (answer == JOptionPane.YES_OPTION ) {
					
					
				
					int result = rP.deleteRest(rest.getId());
						listRest = rP.requestData();
						pSee.fillTable(listRest);
					
						if (result > 0) {
							JOptionPane.showMessageDialog(pSee, "Restaurante eliminado con exito");
						}else {
							System.out.println("Error en la Query");
						}
					
				}else {
					listRest = rP.requestData();
				}
		}
		
		
	}

}
