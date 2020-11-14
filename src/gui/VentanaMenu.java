package gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Controlador;

public class VentanaMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton botonComenzarPartida;
	private JButton botonVerRanking;
	private JButton botonSalir;
	private Controlador controlador;
	
	
	public VentanaMenu() {
		controlador = new Controlador();
		configurar();
		asignarEventos();
		this.setVisible(true);
		this.setTitle("Space Invaders");
		this.setSize(300, 300);
		this.setLocation(150, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	
	public VentanaMenu(Controlador controlador) {
		this.controlador = controlador;
		configurar();
		asignarEventos();
		this.setVisible(true);
		this.setTitle("Space Invaders");
		this.setSize(300, 300);
		this.setLocation(150, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	private void configurar() {
		Container con = this.getContentPane();
		con.setLayout(new GridLayout(3,1));
		botonComenzarPartida = new JButton("COMENZAR");
		botonVerRanking = new JButton("VER RANKING");
		botonSalir = new JButton("SALIR");
		con.add(botonComenzarPartida);
		con.add(botonVerRanking);
		con.add(botonSalir);
	}
	
	private void asignarEventos() {
		ComienzoPartida objetoComienzoPartida = new ComienzoPartida();
		VistaRanking objetoVistaRanking = new VistaRanking(this);
		FinalizacionJuego objetoFinalizacionJuego = new FinalizacionJuego();

		
		botonComenzarPartida.addActionListener(objetoComienzoPartida);
		botonVerRanking.addActionListener(objetoVistaRanking);
		botonSalir.addActionListener(objetoFinalizacionJuego);
		
	}
	
	class ComienzoPartida implements ActionListener {

		public ComienzoPartida() {
		}
		
		public void actionPerformed(ActionEvent e) {
			new VentanaDificultad(controlador);
			dispose();
		}
	}
	
	class VistaRanking implements ActionListener {

		private JFrame frame;
		
		public VistaRanking(JFrame frame) {
			this.frame = frame;
		}
		
		public void actionPerformed(ActionEvent e) {
			if(controlador.verRanking().size() < 1) {
				JOptionPane.showMessageDialog(frame, "Aún no hay ningún puntaje para visualizar");
			} else {
				new VentanaRanking(controlador);
				dispose();
			}
		}
		
	}
	
	class FinalizacionJuego implements ActionListener {

		
		public FinalizacionJuego() {
		}
		
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}

	
}
