package controller;

import java.util.*;

import negocio.Invasor;
import negocio.Muro;
import negocio.Partida;
import negocio.Proyectil;
import negocio.Puntaje;


public class Controlador {

	private static Controlador instancia;
	private Partida partida;
    private ArrayList<Puntaje> puntos;
	
	
    public Controlador() {
    	this.puntos = new ArrayList<Puntaje>();
    }


    public void iniciarPartida(String dificultad) {
    	this.partida = new Partida(dificultad);
    }

    public void finalizarPartida(Partida partida) {
    	partida = null;
    }
    
    public ArrayList<Puntaje> verRanking() {
    	return puntos;
    }

    public void moverBateriaIzquierda() {
    	partida.moverBateriaIzquierda();
    }

    public void moverBateriaDerecha() {
    	partida.moverBateriaDerecha();
    }

    public void dispararProyectil() {
    	partida.disparar();

    }

    public void agregarPuntaje(String nombreJugador) {
    	ArrayList<Puntaje> temp = new ArrayList<Puntaje>();
    	if(puntos.size() > 0) {
	    	for(Puntaje punto : puntos) {
	    		if(punto.getPuntajeJugador() > partida.getPuntaje()) {
	    			temp.add(punto);
	    		} else {
	    			temp.add(partida.agregarPuntaje(nombreJugador));
	    			temp.add(punto);
	    		}
	    	}
	    	puntos = temp;
	    	temp = null;
    	} else {
    		puntos.add(partida.agregarPuntaje(nombreJugador));
    	}
    }
    
    public static Controlador getInstancia() {
		  if (instancia == null)
		   instancia = new Controlador();
		  return instancia;
	}
    
    public int getPosicionBateriaX() {
    	return partida.getPosicionBateriaX();
    }
    
    public int getPosicionBateriaY() {
    	return partida.getPosicionBateriaY();
    }
    
    public ArrayList<Integer> getPosicionesInvasoresX() {
    	return partida.getPosicionesInvasoresX();
    }
    
    public ArrayList<Integer> getPosicionesInvasoresY() {
    	return partida.getPosicionesInvasoresY();
    }
    
    public ArrayList<Invasor> getInvasores() {
    	return partida.getInvasores();
    }
    
    public ArrayList<Muro> getMuros() {
    	return partida.getMuros();
    }
    
    public ArrayList<Proyectil> getProyectiles() {
    	return partida.getProyectiles();
    }
    
    public void moverHorda() {
    	partida.moverHorda();
    }
    
    public void moverProyectiles() {
    	partida.actualizarDisparos();
    	
    }
    
    public int getTamanoAnchoX() {
    	return partida.getTamanoAnchoX();
    }
    
    public int getTamanoLargoY() {
    	return partida.getTamanoLargoY();
    }
    
    public boolean bateriaPuedeMoverseDerecha() {
    	return partida.bateriaPuedeMoverseDerecha();
    }
    
    public boolean bateriaPuedeMoverseIzquierda() {
    	return partida.bateriaPuedeMoverseIzquierda();
    }
    
    public int getPuntaje() {
    	return partida.getPuntaje();
    }
    
    public int getVidas() {
    	return partida.getVidas();
    }
    
    public int getNivel() {
    	return partida.getNivel();
    }
    
    public boolean estanInvasoresExterminados() {
    	return partida.estanInvasoresExterminados();
    }
    
    public void dispararHorda() {
    	partida.disparaHorda();
    }
    
    public boolean isGameOver() {
    	return partida.esGameOver();
    }
    

}