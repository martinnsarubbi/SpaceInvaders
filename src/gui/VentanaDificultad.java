package gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.Controlador;

public class VentanaDificultad extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton botonFacil;
	private JButton botonMedio;
	private JButton botonDificil;
	private Controlador controlador;
	
	public VentanaDificultad(Controlador controlador) {
		this.controlador = controlador;
		configurar();
		asignarEventos();
		this.setVisible(true);
		this.setTitle("Seleccione la dificultad");
		this.setSize(300, 300);
		this.setLocation(150, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void configurar() {
		Container con = this.getContentPane();
		con.setLayout(new GridLayout(3,1));
		botonFacil = new JButton("Fácil");
		botonMedio = new JButton("Medio");
		botonDificil = new JButton("Difícil");
		con.add(botonFacil);
		con.add(botonMedio);
		con.add(botonDificil);
	}
	
	private void asignarEventos() {
		ComienzoPartidaFacil objetoComenzarPartidaFacil = new ComienzoPartidaFacil();
		ComienzoPartidaMedio objetoComenzarPartidaMedio = new ComienzoPartidaMedio();
		ComienzoPartidaDificil objetoComenzarPartidaDificil = new ComienzoPartidaDificil();

		
		botonFacil.addActionListener(objetoComenzarPartidaFacil);
		botonMedio.addActionListener(objetoComenzarPartidaMedio);
		botonDificil.addActionListener(objetoComenzarPartidaDificil);
	}
		
	class ComienzoPartidaFacil implements ActionListener {

		public ComienzoPartidaFacil() {
		}
		
		public void actionPerformed(ActionEvent e) {
			controlador.iniciarPartida("Facil");
			new VentanaJuego(controlador);
			dispose();
		}
	}
	
	class ComienzoPartidaMedio implements ActionListener {

		public ComienzoPartidaMedio() {
		}
		
		public void actionPerformed(ActionEvent e) {
			controlador.iniciarPartida("Medio");
			new VentanaJuego(controlador);
			dispose();
		}
	}
	
	class ComienzoPartidaDificil implements ActionListener {

		public ComienzoPartidaDificil() {
		}
		
		public void actionPerformed(ActionEvent e) {
			controlador.iniciarPartida("Dificil");
			new VentanaJuego(controlador);
			dispose();
		}
	}
	
}
