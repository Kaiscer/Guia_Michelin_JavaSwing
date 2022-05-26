package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import control.RestControl;
import model.Rest;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PAddRest extends JPanel {
	
	
	public static final String BTN_ADD = "Añadir Rest";
	public static final String BTN_CLEAN = "Limpiar datos";
	
	public static final int ANCHO = 750;
	public static final int ALTO = 550;
	private JTextField txtNombre;
	private JComboBox<String> cmbRegion;
	private JComboBox<String> cmbCocina;
	private JTextField txtCiudad;
	private JTextField txtDirección;
	private JTextField txtPrecioMin;
	private JTextField txtPrecioMax;
	private JSpinner spnDistin;
	private JTextField txtTlfno;
	private JTextField txtWeb;
	private JButton btnAdd;
	
	private JButton btnClean;
	public PAddRest() {
		initComponents();
	}
	private void initComponents() {
		setLayout(null);
		
		JLabel lblAddRest = new JLabel("Resgistrar Restaurante");
		lblAddRest.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 20));
		lblAddRest.setBounds(272, 6, 236, 25);
		add(lblAddRest);
		
		JLabel lblNameRest = new JLabel("Nombre:");
		lblNameRest.setBounds(105, 86, 63, 25);
		add(lblNameRest);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(166, 85, 228, 26);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblConica = new JLabel("Cocina:");
		lblConica.setBounds(422, 90, 61, 16);
		add(lblConica);
		
		
		
		cmbCocina = new JComboBox<String>();
		cmbCocina.setBounds(495, 86, 123, 27);
		cmbCocina.setModel(new DefaultComboBoxModel<String>(Rest.COCINA));
		add(cmbCocina);
		
		JLabel lblRegion = new JLabel("Región:");
		lblRegion.setBounds(105, 145, 61, 16);
		add(lblRegion);
		
		cmbRegion = new JComboBox<String>();
		cmbRegion.setBounds(166, 141, 179, 27);
		cmbRegion.setModel(new DefaultComboBoxModel<String>(Rest.REGIONES));
		add(cmbRegion);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(422, 145, 61, 16);
		add(lblCiudad);
		
		txtCiudad = new JTextField();
		txtCiudad.setBounds(480, 140, 184, 26);
		add(txtCiudad);
		txtCiudad.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setBounds(105, 210, 75, 16);
		add(lblDireccion);
		
		txtDirección = new JTextField();
		txtDirección.setBounds(176, 205, 483, 26);
		add(txtDirección);
		txtDirección.setColumns(10);
		
		JLabel lblDistincion = new JLabel("Distinción:");
		lblDistincion.setBounds(105, 278, 75, 16);
		add(lblDistincion);
		
		spnDistin = new JSpinner();
		spnDistin.setBounds(176, 273, 51, 26);
		spnDistin.setEditor(new JSpinner.DefaultEditor(spnDistin));
		spnDistin.setModel(new SpinnerNumberModel(1, 1, 3, 1));
		add(spnDistin);
		
		JLabel lblPrecioMin = new JLabel("Precio mínimo:");
		lblPrecioMin.setBounds(239, 278, 106, 16);
		add(lblPrecioMin);
		
		txtPrecioMin = new JTextField();
		txtPrecioMin.setBounds(345, 273, 106, 26);
		add(txtPrecioMin);
		txtPrecioMin.setColumns(10);
		
		JLabel lblPrecioMax = new JLabel("máximo:");
		lblPrecioMax.setBounds(489, 278, 75, 16);
		add(lblPrecioMax);
		
		txtPrecioMax = new JTextField();
		txtPrecioMax.setBounds(562, 273, 95, 26);
		add(txtPrecioMax);
		txtPrecioMax.setColumns(10);
		
		JLabel lblTlfn = new JLabel("Teléfono:");
		lblTlfn.setBounds(105, 348, 61, 16);
		add(lblTlfn);
		
		txtTlfno = new JTextField();
		txtTlfno.setBounds(166, 343, 130, 26);
		add(txtTlfno);
		txtTlfno.setColumns(10);
		
		JLabel lblWeb = new JLabel("Web:");
		lblWeb.setBounds(345, 348, 61, 16);
		add(lblWeb);
		
		txtWeb = new JTextField();
		txtWeb.setBounds(383, 343, 275, 26);
		add(txtWeb);
		txtWeb.setColumns(10);
		
		btnAdd = new JButton(BTN_ADD);
		btnAdd.setBounds(214, 430, 117, 29);
		add(btnAdd);
		
		btnClean = new JButton(BTN_CLEAN);
		btnClean.setBounds(422, 430, 117, 29);
		add(btnClean);
		
		setSize(ANCHO,ALTO);
		
	}
	public void setControl(RestControl control) {
		
		btnAdd.addActionListener(control);
		btnClean.addActionListener(control);
		
		
	}
	public Rest newDataRest() {
		Rest rest = null;
		
		int id = 0;
		String nombre  = txtNombre.getText().trim();
				
		if (nombre.isBlank()) {
			setError("Debes introducir el nombre");
			return rest;
		}
		
		String cocina = (String)cmbCocina.getSelectedItem();
		String region = (String)cmbRegion.getSelectedItem();
		
		String ciudad = txtCiudad.getText().trim();
		
		if (ciudad.isBlank()) {
			setError("Debes introducir la ciudad");
			return rest;
		}
		
		String direc = txtDirección.getText();
		
		int disti = (int)spnDistin.getValue();
		
		try {
					
			double pMin = Double.parseDouble(txtPrecioMin.getText());
			double pMax = Double.parseDouble(txtPrecioMax.getText());
			
			if (pMin == 0 || pMax == 0) {
				throw new NumberFormatException();
			}else {
				if (pMin >= pMax) {
					setError("El precio mínimo no puede ser superior al máximo");
				
				}else {
					
					String tlnf = txtTlfno.getText();
					String web = txtWeb.getText();
					
					rest = new Rest(id,nombre, region, ciudad, disti, direc, pMin, pMax, cocina, tlnf, web);
				}
			}
			
		}catch (NumberFormatException e) {
			setError("El precio mínimo como máximo deben ser distintos a 0 y valores numéricos");
		}
		
		
		
		return rest;
		
		
	}
	public void setError(String error) {
		JOptionPane.showMessageDialog(this, error, "Error de datos",JOptionPane.ERROR_MESSAGE);
		
	}
	public void cleanForm() {
		
		txtNombre.setText("");
		cmbCocina.setSelectedItem(Rest.COCINA[0]);
		cmbRegion.setSelectedItem(Rest.REGIONES[0]);
		txtCiudad.setText("");
		txtDirección.setText("");
		spnDistin.setValue(1);
		txtPrecioMin.setText("");
		txtPrecioMax.setText("");
		txtTlfno.setText("");
		txtWeb.setText("");
	
		
	}
	public void setMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Mesensaje de Confirmacion", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
