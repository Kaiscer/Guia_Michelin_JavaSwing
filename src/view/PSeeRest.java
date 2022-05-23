package view;

import javax.swing.JPanel;

import control.RestControl;
import db.RestContract;
import model.Rest;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

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
	
	public static final String [] DISTIN = {"TODAS", "1 Estrella", "2 Estrellas", "3 Estrellas"};

	public static final String REGIONES = "TODAS"; 
	 
	public static final int ANCHO = 800;
	public static final int ALTO = 650;
	public static final String BTN_SEE = "Consultar";
	public static final String BTN_DELETE = "Eliminar";
	public static final String COLUM_PRECIO = "PRECIO";
	
	
	private JComboBox<String> cmbRegion;
	private JComboBox<String> cmbDistincion;
	private JButton btnSee;
	private JScrollPane scrollTable;
	private JTable tableRest;
	private DefaultTableModel tModel;
	private DefaultComboBoxModel<String> cmbModel;
	private JButton btnDelete;
	private ArrayList<Rest> datosTabla;
	
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
		cmbModel = new DefaultComboBoxModel<String>();
		cmbRegion.setModel(cmbModel);
		
		add(cmbRegion);
		
		setSize(ANCHO,ALTO);
		
		JLabel lblDistincion = new JLabel("Distinción:");
		lblDistincion.setBounds(315, 87, 72, 16);
		add(lblDistincion);
		
		cmbDistincion = new JComboBox<String>();
		cmbDistincion.setBounds(389, 83, 109, 27);
		cmbDistincion.setModel(new DefaultComboBoxModel<String>(DISTIN));
		add(cmbDistincion);
		
		btnSee = new JButton(BTN_SEE);
		btnSee.setBounds(402, 166, 117, 29);
		add(btnSee);
		
		scrollTable = new JScrollPane();
		scrollTable.setVisible(false);
		scrollTable.setBounds(19, 204, 634, 316);
		add(scrollTable);
		
		tableRest = new JTable();
		configTable();
		
		scrollTable.setViewportView(tableRest);
		
		btnDelete = new JButton(BTN_DELETE);
		btnDelete.setBounds(531, 166, 117, 29);
		add(btnDelete);
		centerWs();
		
	}

	public void fillCmbRegion(ArrayList<String> listRegiones) {
		
		cmbModel.removeAllElements();
		cmbModel.addElement(REGIONES);
		cmbModel.addAll(listRegiones);
	}
	
	
	public void hacerVisible(boolean b) {
		
		scrollTable.setVisible(b);
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
		
		tableRest.getColumn(RestContract.COLUMN_NOMBRE).setPreferredWidth(100);
		tableRest.getColumn(RestContract.COLUMN_CIUDAD).setPreferredWidth(100);
		tableRest.getColumn(RestContract.COLUMN_DISTIN).setPreferredWidth(50);
		tableRest.getColumn(RestContract.COLUMN_COCINA).setPreferredWidth(60);
		tableRest.getColumn(COLUM_PRECIO).setPreferredWidth(95);		
	
		
		
	}
		
	private void centerWs() {
		
		setPreferredSize(new Dimension(ANCHO, ALTO));  
	       
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();               
		
		Dimension ventana = this.getPreferredSize();               
		       
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
	}
	
	public void setControl(RestControl control) {
		btnSee.addActionListener(control);
		btnDelete.addActionListener(control);
	}
	
	public JComboBox<String> getCmbRegion() {
		
		return cmbRegion;
	}
	
	public JComboBox<String> getCmbDistincion(){
		
		return cmbDistincion;
	}
	
	
	public void fillTable(ArrayList<Rest> listRest) {
		
		datosTabla = listRest;
		tModel.getDataVector().clear();
		
		Object[]row = new Object [5];
		
		for (Rest rest : listRest) {
			
			row [0] = rest.getNombre();
			row [1] = rest.getCiudad();
			if (rest.getDistincion() == 1) {
				row [2] = "*";
			}else if(rest.getDistincion() == 2) {
				row [2] = "**";
			}else {
				row [2] = "***";
			}
			
			row [3] = rest.getCocina();
			row [4] = rest.getPrecio_Min()+"€" + " - " + rest.getPrecio_Max()+"€";
			
			tModel.addRow(row);
		}
		
		
		
		
	}
	public void setError(String error) {
		JOptionPane.showMessageDialog(this, error, "Resultado de Consulta", JOptionPane.ERROR_MESSAGE);
		
	}
	
	public Rest restSelect() {
		Rest restSel = null;
		int pos = tableRest.getSelectedRow();
		
		if (pos >= 0) {
			
			restSel = datosTabla.get(pos);
			
		}else {
			
			restSel = null;
		}
		
		return restSel ;
	}
	
	
}
