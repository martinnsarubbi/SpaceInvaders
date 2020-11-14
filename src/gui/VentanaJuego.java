package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import controller.Controlador;
import negocio.Invasor;
import negocio.Muro;
import negocio.Proyectil;

public class VentanaJuego extends JFrame {
	private static final long serialVersionUID = 1L;
	private Controlador controlador;
	private Timer timer;
	private JLabel bateriaLabel;
	private ArrayList<JLabel> invasorLabel;
	private ArrayList<JLabel> muroLabel;
	private ArrayList<JLabel> proyectiles;
	private JLabel puntajeLabel;
	private JLabel vidasLabel;
	private JLabel nivelLabel;
	private int contadorParaDisparar;
	private int iteracionesParaDisparar = 60;
	
	public VentanaJuego(Controlador controlador) {
		this.controlador = controlador;
		configurar();
		asignarEventos();
		this.setSize(650, 650);
		this.setVisible(true);
	}
	
	
	private void configurar() {
		Container con = this.getContentPane();
		con.setBackground(Color.black);
		con.setLayout(null);
		
		bateriaLabel = new JLabel();
		bateriaLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("bateria.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		bateriaLabel.setBounds(controlador.getPosicionBateriaX(), controlador.getPosicionBateriaY(), 40, 40);
		con.add(bateriaLabel);
		
		invasorLabel = new ArrayList<>();
		crearLabelInvasores();
		
		muroLabel = new ArrayList<>();
		crearLabelMuros();
		
		proyectiles  = new ArrayList<>();
		
		vidasLabel = new JLabel();
		
		nivelLabel = new JLabel();
		con.add(nivelLabel);
		nivelLabel.setText("Nivel: " + String.valueOf(controlador.getNivel()));
		nivelLabel.setForeground(new java.awt.Color(255,255,255));
		nivelLabel.setBounds(300, 570 , 125, 50);
		nivelLabel.setFont(new Font("Impact",1,24));
		
		puntajeLabel = new JLabel();
		con.add(puntajeLabel);
		puntajeLabel.setText("Puntaje: " + String.valueOf(controlador.getPuntaje()));
		puntajeLabel.setForeground(new java.awt.Color(255,255,255));
		puntajeLabel.setBounds(480, 570 , 125, 50);
		puntajeLabel.setFont(new Font("Impact",1,24));
		
		vidasLabel = new JLabel();
		con.add(vidasLabel);
		vidasLabel.setText("Puntaje: " + String.valueOf(controlador.getPuntaje()));
		vidasLabel.setForeground(new java.awt.Color(255,255,255));
		vidasLabel.setBounds(10, 570 , 125, 50);
		vidasLabel.setFont(new Font("Impact",1,24));
		
	}
	
	
	
	private void asignarEventos() {
		timer = new Timer(41, new ManejoTimer());
		timer.start();
		
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 39) {
					if (controlador.bateriaPuedeMoverseDerecha()) {
						controlador.moverBateriaDerecha();
						bateriaLabel.setBounds(controlador.getPosicionBateriaX(), bateriaLabel.getY(), bateriaLabel.getWidth(), bateriaLabel.getHeight());
					}
				} else if(e.getKeyCode() == 37) {
					if (controlador.bateriaPuedeMoverseIzquierda()) {
						controlador.moverBateriaIzquierda();
						bateriaLabel.setBounds(controlador.getPosicionBateriaX(), bateriaLabel.getY(), bateriaLabel.getWidth(), bateriaLabel.getHeight());
					}
				} else if (e.getKeyCode() == 32) {
					controlador.dispararProyectil();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				/*controlador.moverHorda();*/
			}
		}); 

	}
	
	private void actualizarInvasores(){
		for (Invasor inv: controlador.getInvasores()) {
			if(inv.isVivo()){
				invasorLabel.get(controlador.getInvasores().indexOf(inv)).setBounds(inv.getPosicionX(), inv.getPosicionY(), 50,50);
			}
			else
				invasorLabel.get(controlador.getInvasores().indexOf(inv)).setVisible(false);
		}
	}
	
	private void actualizarMuros(){
		for (Muro muro: controlador.getMuros()) {
			if(muro.isVivo()){
				muroLabel.get(controlador.getMuros().indexOf(muro)).setBounds(muro.getPosicionX(), muro.getPosicionY(), 50,20);
			}
			else
				muroLabel.get(controlador.getMuros().indexOf(muro)).setVisible(false);
		}
	}
	
	private void iniciarNuevoNivel() {
		for (Muro muro: controlador.getMuros()) {
			muroLabel.get(controlador.getMuros().indexOf(muro)).setVisible(false);
		}
		invasorLabel = new ArrayList<>();
		muroLabel = new ArrayList<>();
		crearLabelInvasores();
		crearLabelMuros();
		for (JLabel inv : invasorLabel) {
			inv.setVisible(true);			
		}
		for (JLabel muro : muroLabel) {
			muro.setVisible(true);			
		}
	}
	
	private void hacerDesaparecerDisparos(){
		for (JLabel proyectil : proyectiles) {
			proyectil.setVisible(false);			
		}
		for(int i = 0; i < proyectiles.size(); i++)
			proyectiles.remove(i) ;
	}
	private void actualizarProyectiles(){
		hacerDesaparecerDisparos();
		proyectiles = new ArrayList<>();		
		for (Proyectil proyectil : controlador.getProyectiles()) {
			JLabel proyect = new JLabel();
			getContentPane().add(proyect);
			proyect.setBounds(proyectil.getPosicionX(), proyectil.getPosicionY(), 25, 25);
			proyectiles.add(proyect);
			proyect.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("proyectil.png")).getImage().getScaledInstance(5, 30, Image.SCALE_DEFAULT)));
		}
	}
	
	private void crearLabelInvasores( ) {
		for(int i = 0; i < controlador.getPosicionesInvasoresX().size(); i++) {
			JLabel invLabel = new JLabel();
			invLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("alien.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
			invasorLabel.add(invLabel);
			invLabel.setBounds(controlador.getPosicionesInvasoresX().get(i), controlador.getPosicionesInvasoresY().get(i), 50, 50);
			getContentPane().add(invLabel);
		}
	}
	
	private void crearLabelMuros() {
		for(int i = 0; i < controlador.getMuros().size(); i++) {
			JLabel wallLabel = new JLabel();
			wallLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("muro.png")).getImage().getScaledInstance(100, 20, Image.SCALE_DEFAULT)));
			muroLabel.add(wallLabel);
			wallLabel.setBounds(controlador.getMuros().get(i).getPosicionX(), controlador.getMuros().get(i).getPosicionY(), 50, 20);
			getContentPane().add(wallLabel);
		}
	}
	
	
	class ManejoTimer implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			controlador.moverHorda();
			controlador.moverProyectiles();
			puntajeLabel.setText("Puntos: " + String.valueOf(controlador.getPuntaje()));
			vidasLabel.setText("Vidas: " + String.valueOf(controlador.getVidas()));
			nivelLabel.setText("Nivel: " + String.valueOf(controlador.getNivel()));
			actualizarInvasores();
			actualizarProyectiles();
			actualizarMuros();
			
			if (controlador.estanInvasoresExterminados()) {
				iniciarNuevoNivel();
			}
			
			if (contadorParaDisparar == iteracionesParaDisparar) {
				controlador.dispararHorda();;
				contadorParaDisparar=0;
			}
			contadorParaDisparar++;
			
			
			if(controlador.isGameOver()) {
				timer.stop();
				JOptionPane.showMessageDialog(null,"Tu puntaje fue de " + controlador.getPuntaje(),
						"GAME OVER", JOptionPane.INFORMATION_MESSAGE);
				VentanaIngresoNombre ventanaIngresoNombre = new VentanaIngresoNombre(controlador);
				ventanaIngresoNombre.setVisible(true);
				ventanaIngresoNombre.setLocationRelativeTo(null);
				dispose();
			}
			
		}
		
	}
	

	
	
}
