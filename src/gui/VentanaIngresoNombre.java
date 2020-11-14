package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.Controlador;

public class VentanaIngresoNombre extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel rotulo;
	private JTextField texto;
	private JButton botonAceptar;
	private Controlador controlador;
	
	public VentanaIngresoNombre(Controlador controlador) {
		configurar();
		this.controlador = controlador;
		
		this.setSize(250, 250);
		this.setVisible(true);
	}
	
	
	private void configurar() {
		Container con = this.getContentPane();
		con.setLayout(null);
		rotulo = new JLabel("Ingrese su nombre");
		texto = new JTextField();
		rotulo.setBounds(10, 20, 150, 18);
		texto.setBounds(10, 60, 150, 30);
		
		con.add(rotulo);
		con.add(texto);
		
		botonAceptar = new JButton();
		con.add(botonAceptar);
		botonAceptar.setText("Aceptar");
		botonAceptar.setBounds(10, 120, 115, 30);
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(texto.getText().toString().
						compareToIgnoreCase("")==0
						|| texto.getText().toString().
						compareToIgnoreCase("Ingrese su nombre")==0){
					JOptionPane.showMessageDialog(null,"Ingrese su nombre",
							"Ingrese su nombre",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				controlador.agregarPuntaje(texto.getText().toString());
				VentanaRanking ranking  = new VentanaRanking(controlador);
				ranking.setVisible(true);
				ranking.setLocationRelativeTo(null);
				getContentPane().setVisible(false);
				dispose();
			}
		});
	}
		
		
}
	
	
