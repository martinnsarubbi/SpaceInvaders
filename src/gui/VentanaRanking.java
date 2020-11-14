package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


import controller.Controlador;


public class VentanaRanking extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel titulo;
	private Controlador controlador;
	private JButton menuPrincipal;
	
	public VentanaRanking(Controlador controlador) {
		this.controlador = controlador;
		configurar();
		
		
		this.setSize(250, 300);
		this.setVisible(true);
	}
	
	
	private void configurar() {
		Container con = this.getContentPane();
		con.setLayout(null);
		titulo = new JLabel("Ranking");
		con.add(titulo);
		titulo.setBounds(10, 10, 50, 30);
		
		for(int i = 0; i < controlador.verRanking().size(); i++) {
			String nombreJugador = controlador.verRanking().get(i).getNombreJugador();
			JLabel rankNombre = new JLabel(nombreJugador);
			con.add(rankNombre);
			rankNombre.setBounds(10, 50 + i * 25, 50, 10);
			
			
			String puntajeJugador = Integer.toString(controlador.verRanking().get(i).getPuntajeJugador());
			JLabel rankJugador = new JLabel(puntajeJugador);
			con.add(rankJugador);
			rankJugador.setBounds(120, 50 + i * 25 , 30, 10);
		}
		
		menuPrincipal = new JButton();
		getContentPane().add(menuPrincipal);
		menuPrincipal.setText("Volver al menu");
		menuPrincipal.setBounds(25, 200, 150, 30);
		menuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenu ventanaMenu = new VentanaMenu(controlador);
				ventanaMenu.setVisible(true);
				ventanaMenu.setLocationRelativeTo(null);
				getContentPane().setVisible(false);
				dispose();
			}
		});
		
	}
		
		
}

