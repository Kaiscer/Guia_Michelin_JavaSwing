package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import control.RestControl;
import model.Rest;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PAddRest extends JPanel {
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
	private JTextField textField_1;
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
		txtNombre.setBounds(166, 85, 203, 26);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblConica = new JLabel("Cocina:");
		lblConica.setBounds(422, 90, 61, 16);
		add(lblConica);
		
		setSize(ANCHO,ALTO);
		
		cmbCocina = new JComboBox<String>(Rest.COCINA);
		cmbCocina.setBounds(495, 86, 123, 27);
		add(cmbCocina);
		
		JLabel lblRegion = new JLabel("Región:");
		lblRegion.setBounds(105, 145, 61, 16);
		add(lblRegion);
		
		cmbRegion = new JComboBox<String>(Rest.DISTIN);
		cmbRegion.setBounds(166, 141, 179, 27);
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
		txtDirección.setBounds(176, 205, 420, 26);
		add(txtDirección);
		txtDirección.setColumns(10);
		
		JLabel lblDistincion = new JLabel("Distinción:");
		lblDistincion.setBounds(105, 278, 75, 16);
		add(lblDistincion);
		
		spnDistin = new JSpinner();
		spnDistin.setBounds(176, 273, 51, 26);
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
		
		textField_1 = new JTextField();
		textField_1.setBounds(383, 343, 275, 26);
		add(textField_1);
		textField_1.setColumns(10);
		
		btnAdd = new JButton("Guardar datos");
		btnAdd.setBounds(214, 430, 117, 29);
		add(btnAdd);
		
		btnClean = new JButton("Limpiar datos");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClean.setBounds(434, 430, 117, 29);
		add(btnClean);
	}
	public void setControl(RestControl control) {
		
		btnAdd.addActionListener(control);
		btnClean.addActionListener(control);
		
	}
}
