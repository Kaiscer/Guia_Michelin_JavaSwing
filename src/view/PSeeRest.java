package view;

import javax.swing.JPanel;

import control.RestControl;
import db.RestContract;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;

public class PSeeRest extends JPanel {
	public static final int ANCHO = 700;
	public static final int ALTO = 500;
	public static final String BTN_SEE = "Consultar";
	public static final String BTN_DELETE = "Eliminar";
	public static final String [] REGIONES = {"Todas", "Andalucia", "Asturias", "Isla baleares", "Cantabria",
			"Casttilla - La Mancha", "Cataluña", "Extremadura", "Madird", "Murcia", "Navarra", "País Vasco","La Rioja", "Comunidad Valenciana"}; 
	public static final String COLUM_PRECIO = "PRECIO";
	public static final String [] DISTINCION = {"1 Estrella", "2 Estrella", "3 Estrella"};
	
	private JComboBox<String> cmbRegion;
	private JComboBox<String> cmbDistincion;
	private JButton btnSee;
	private JScrollPane scrollTable;
	private JTable tableRest;
	private DefaultTableModel tModel;
	private JButton btnDelete;
	public PSeeRest() {
		initComponents();
	}
	private void initComponents() {
		setLayout(null);
		
		JLabel lblConsult = new JLabel("Restaurantes con Estrellas Micheline");
		lblConsult.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 25));
		lblConsult.setBounds(45, 6, 474, 27);
		add(lblConsult);
		
		JLabel lblFiltro = new JLabel("Filtros:");
		lblFiltro.setBounds(22, 59, 61, 16);
		add(lblFiltro);
		
		JLabel lblRegion = new JLabel("Región:");
		lblRegion.setBounds(22, 87, 61, 16);
		add(lblRegion);
		
		cmbRegion = new JComboBox<String>();
		cmbRegion.setBounds(74, 83, 178, 27);
		cmbRegion.setModel(new DefaultComboBoxModel<String>(REGIONES));
		add(cmbRegion);
		
		setSize(ANCHO,ALTO);
		
		JLabel lblDistincion = new JLabel("Distinción:");
		lblDistincion.setBounds(315, 87, 72, 16);
		add(lblDistincion);
		
		cmbDistincion = new JComboBox<String>();
		cmbDistincion.setBounds(389, 83, 109, 27);
		cmbDistincion.setModel(new DefaultComboBoxModel<String>(DISTINCION));
		add(cmbDistincion);
		
		btnSee = new JButton(BTN_SEE);
		btnSee.setBounds(381, 154, 117, 29);
		add(btnSee);
		
		scrollTable = new JScrollPane();
		scrollTable.setVisible(false);
		scrollTable.setBounds(19, 204, 555, 316);
		add(scrollTable);
		
		tableRest = new JTable();
		configTable();
		scrollTable.setViewportView(tableRest);
		
		btnDelete = new JButton(BTN_DELETE);
		btnDelete.setBounds(426, 532, 117, 29);
		add(btnDelete);
		centerWs();
		
	}
	private void configTable() {
		
		tModel = new DefaultTableModel() {
			
			public boolean isCallEditable(int row, int column) {
				
				return false;
			}
		};
		
		tableRest.setModel(tModel);
		
		tModel.addColumn(RestContract.COLUMN_NOMBRE);
		tModel.addColumn(RestContract.COLUMN_CIUDAD);
		tModel.addColumn(RestContract.COLUMN_DISTIN);
		tModel.addColumn(RestContract.COLUMN_COCINA);
		tModel.addColumn(COLUM_PRECIO);
		
		tableRest.getColumn(RestContract.COLUMN_NOMBRE).setPreferredWidth(75);
		tableRest.getColumn(RestContract.COLUMN_CIUDAD).setPreferredWidth(75);
		tableRest.getColumn(RestContract.COLUMN_DISTIN).setPreferredWidth(75);
		tableRest.getColumn(RestContract.COLUMN_COCINA).setPreferredWidth(75);
		tableRest.getColumn(COLUM_PRECIO).setPreferredWidth(85);		
	
		
		
	}
	
	
	
	
	
	private void centerWs() {
		
		setPreferredSize(new Dimension(ANCHO, ALTO));  
	       
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();               
		
		Dimension ventana = this.getPreferredSize();               
		       
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
	}
	
	public void setControl(RestControl control) {
		btnSee.addActionListener(control);
	}
}
