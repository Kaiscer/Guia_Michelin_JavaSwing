package view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.JSeparator;

import control.RestControl;


public class WPrincipal extends JFrame {
	public static final int ANCHO = 800;
	public static final int ALTO = 600;
	public static final String ITEM_SEE = "Consultar Restaurantes";
	public static final String ITEM_ADD = "Registro de Restaurante";
	public static final String ITEM_MODIFICACION = "Modificación de Restaurante";
	public static final String ITEM_EXIT = "Salir";
	
	
	private JScrollPane scrollContainer;
	private JMenuBar menuBar;
	private JMenuItem mItemSee;
	private JMenuItem mItemAddRest;
	private JMenuItem mItemModify;
	private JSeparator separator;
	private JSeparator separator_1;
	private JMenuItem mItemSalir;
	
	public WPrincipal() {
		initComponents();
	}
	private void initComponents() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		scrollContainer = new JScrollPane();
		getContentPane().add(scrollContainer, BorderLayout.CENTER);
		setSize(ANCHO,ALTO);
		makeMenu();
		centerWp();
	}
	private void centerWp() {
		
				setPreferredSize(new Dimension(ANCHO, ALTO));  
			       
				Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();               
				
				Dimension ventana = this.getPreferredSize();               
				       
				setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
				
				
		
	}
	private void makeMenu() {
		
		menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		setJMenuBar(menuBar);
		
		JMenu mnManageMenu = new JMenu("Manager Restaurant ");
		menuBar.add(mnManageMenu);
		
		mItemSee = new JMenuItem(ITEM_SEE);
		mnManageMenu.add(mItemSee);
		
		separator = new JSeparator();
		mnManageMenu.add(separator);
		
		mItemAddRest = new JMenuItem(ITEM_ADD);
		mnManageMenu.add(mItemAddRest);
		
		separator_1 = new JSeparator();
		mnManageMenu.add(separator_1);
		
		mItemModify = new JMenuItem(ITEM_MODIFICACION);
		mnManageMenu.add(mItemModify);
		
		mItemSalir = new JMenuItem(ITEM_EXIT);
		menuBar.add(mItemSalir);
	}
	
	public void hacerVisible() {
		
		setVisible(true);
	}
	
	public void uploadPanel(JPanel panel) {
		
		scrollContainer.setViewportView(panel);
	}
	
	public void setControl(RestControl control) {
		
		mItemSee.addActionListener(control);
		mItemAddRest.addActionListener(control);
		mItemModify.addActionListener(control);
		
		mItemSalir.addActionListener(control);
		
	}
	
	

}
