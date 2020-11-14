package negocio;

import java.util.*;

public class Partida {

    private int nivel;
    private int tamanoAnchoX;
    private int tamanoLargoY;
    private int vida;
    private String dificultad;
    private int velocidad;
    private int posPrimeroHorda;
    private int posUltimoHorda;
    private int hacia;
    private int contadorBajada;
    private int puntaje;
    private int puntajeParaOtraVida;
    private Bateria bateria;
    private ArrayList<Invasor> invasores;
    private ArrayList<Proyectil> proyectiles;
    private ArrayList<Muro> muros;
    
    public Partida(String dificultad) {
    	this.setDificultad(dificultad);
    	this.nivel = 1;
    	this.tamanoAnchoX = 600;
    	this.tamanoLargoY = 600;
    	this.vida = 3;
    	this.bateria = new Bateria(300, 500);
    	this.invasores = this.generarHordaDeInvasores();
    	this.proyectiles = new ArrayList<Proyectil>();
    	this.muros = this.generarMuros();
    	this.hacia = 1;
    	this.contadorBajada = 1;
    	this.puntaje = 0;
    }
 
    
    public void moverBateriaDerecha() {
    	bateria.moverBateriaDerecha();
    }
    
    public void moverBateriaIzquierda() {
    	bateria.moverBateriaIzquierda();
    }
    
    public int getPosicionBateriaX() {
    	return bateria.getPosicionX();
    }
    public int getPosicionBateriaY() {
    	return bateria.getPosicionY();
    }

    public int getPuntaje() {
        return this.puntaje;
    }
    
    public int getVidas() {
    	return this.vida;
    }


	public int getTamanoAnchoX() {
		return tamanoAnchoX;
	}
	
	public int getTamanoLargoY() {
		return tamanoLargoY;
	}
	
	public boolean bateriaPuedeMoverseDerecha() {
		if(bateria.getPosicionX() < this.getTamanoAnchoX()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean bateriaPuedeMoverseIzquierda() {
		if(bateria.getPosicionX() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private ArrayList<Invasor> generarHordaDeInvasores() {
		invasores = null;
		ArrayList<Invasor> invasores = new ArrayList<Invasor>();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 5; j++) {
				Invasor invasor = new Invasor(100 + 40 * j, 10 + 30 * i, this.velocidad);
				invasores.add(invasor);
			} 
		}
		
		return invasores;
	}
	
	private ArrayList<Muro> generarMuros() {
		ArrayList<Muro> muros = new ArrayList<Muro>();
		
		muros.add(new Muro(150));
		muros.add(new Muro(300));
		muros.add(new Muro(450));
		
		return muros;
	}
	
	public ArrayList<Integer> getPosicionesInvasoresX() {
		ArrayList<Integer> posX = new ArrayList<Integer>();
		for(Invasor inv : this.invasores) {
			posX.add(inv.getPosicionX());
		}
		this.posPrimeroHorda = posX.get(0);
		this.posUltimoHorda = posX.get(posX.size()-1);
		return posX;
	}
	
	public ArrayList<Integer> getPosicionesInvasoresY() {
		ArrayList<Integer> posY = new ArrayList<Integer>();
		for(Invasor inv : this.invasores) {
			posY.add(inv.getPosicionY());
		}
		return posY;
	}
	
	public ArrayList<Invasor> getInvasores() {
		return this.invasores;
	}
	
	public ArrayList<Muro> getMuros() {
		return this.muros;
	}
	
	public ArrayList<Proyectil> getProyectiles() {
		return this.proyectiles;
	}
	
	private boolean puedoMovHordaDerecha() {
		if(this.posUltimoHorda < this.tamanoAnchoX)
			return true;
		else
			return false;
	}
	
	private boolean puedoMovHordaIzquierda() {
		if(this.posPrimeroHorda > 0)
			return true;
		else
			return false;
	}
	
	private void verHacia(){
		if(!puedoMovHordaDerecha() && this.contadorBajada < 8 && this.contadorBajada > 0){
			this.hacia = -1;
			this.contadorBajada++;
			for (Invasor inv : this.invasores) {
				inv.moverInvasorAbajo();
			}
			if (contadorBajada == 8) contadorBajada = -1;
		} else if (!puedoMovHordaDerecha() && this.contadorBajada > -8 && this.contadorBajada < 0){
			this.hacia = -1;
			this.contadorBajada--;
			for (Invasor inv : this.invasores) {
				inv.moverInvasorArriba();
			}
			if (contadorBajada == -8) contadorBajada = 1;
		}
		
		if(!puedoMovHordaIzquierda() && this.contadorBajada < 8 && this.contadorBajada > 0){
			this.hacia = 1;
			this.contadorBajada++;
			for (Invasor inv : this.invasores) {
				inv.moverInvasorAbajo();
			}
			if (contadorBajada == 8) contadorBajada = -1;
		} else if(!puedoMovHordaIzquierda() && this.contadorBajada > -8 && this.contadorBajada < 0){
			this.hacia = 1;
			this.contadorBajada--;
			for (Invasor inv : this.invasores) {
				inv.moverInvasorArriba();
			}
			if (contadorBajada == -8) contadorBajada = 1;
		}
	}
	
	public void moverHorda() { 
		verHacia();
		if(hacia==1)			
		if(puedoMovHordaDerecha()){
			for (Invasor inv : this.invasores) {
				for (int i = 0; i < nivel; i++)
					inv.moverInvasorDerecha();
			}
			for (int i = 0; i < nivel; i++){
				this.posUltimoHorda = posUltimoHorda + 1 * this.velocidad;
				this.posPrimeroHorda = posPrimeroHorda + 1 * this.velocidad;
			}
		}
		if(hacia==-1)
		if(puedoMovHordaIzquierda()){
			for (Invasor inv : this.invasores){ 
				for (int i = 0; i < nivel; i++)
					inv.moverInvasorIzquierda();
			}
			for (int i = 0; i < nivel; i++){
				this.posUltimoHorda = posUltimoHorda - 1 * this.velocidad;	
				this.posPrimeroHorda = posPrimeroHorda - 1 * this.velocidad;;
			}
		}
	}
	
	private boolean puedoDisparar(){
		int x = 0;
		for (Proyectil proyectil : this.proyectiles) {
			if(proyectil.getSentido() == -1) x++;		
		}
		if(x > 1) return false;
		return true;
	}
	
	public void disparaHorda() {
		Random rand = new Random();
		int randomNum = rand.nextInt(invasores.size());
		while(!invasores.get(randomNum).isVivo()){
			randomNum = rand.nextInt(invasores.size());
		}
		Proyectil proyectil = invasores.get(randomNum).disparar();
		proyectiles.add(proyectil);
	}
	
	
	public void disparar() {
		if(puedoDisparar()){
			Proyectil proyectil = bateria.disparar();
			proyectiles.add(proyectil);
		}
	}
	
	public void actualizarDisparos(){
		for (int i=0; i < proyectiles.size() ; i++)
			if(!proyectiles.get(i).getEstado() || proyectiles.get(i).getPosicionY() > 530 || proyectiles.get(i).getPosicionY() < -10)
				proyectiles.remove(proyectiles.indexOf(proyectiles.get(i)));
		for (Proyectil proyectil : proyectiles) {
			if(proyectil.getEstado()) proyectil.mover(this.velocidad);
			if(proyectil.getSentido() == 1){
				if(bateria.recibirDisparo(proyectil.getPosicionX(), proyectil.getPosicionY()) && proyectil.getEstado()){
					proyectil.setEstado(false);
					this.vida--;
				}
			}
			else{
				for(int i=0;i < invasores.size();i++){
					if(invasores.get(i).isVivo())
						if(invasores.get(i).recibirDisparo(proyectil.getPosicionX(), proyectil.getPosicionY())){
							proyectil.setEstado(false);					
							this.puntaje = puntaje + 10;
							this.puntajeParaOtraVida = puntajeParaOtraVida + 10;
							sumarVida();
						}
				}				
			}
			for (Muro muro : muros) {
				if(muro.isVivo())
					if(muro.recibiDisparo(proyectil.getSentido(), proyectil.getPosicionX(), proyectil.getPosicionY()))
						proyectil.setEstado(false);
			}
		}
	}
	
	private void sumarVida(){
		if(this.puntajeParaOtraVida > 50){
			vida++;
			this.puntajeParaOtraVida = 0;
		}
	}
	
	public boolean esGameOver() {
		if(this.vida == 0)
			return true;
		else 
			return false;
	}
	
	public void setDificultad(String dificultad ) {
		this.dificultad = dificultad;
		this.setVelocidad(this.dificultad);
	}
	
	private void setVelocidad(String dificultad) {
		if(dificultad.equalsIgnoreCase("Facil"))
			this.velocidad = 3;
		if(dificultad.equalsIgnoreCase("Medio"))
			this.velocidad = 6;
		if(dificultad.equalsIgnoreCase("Dificil"))
			this.velocidad = 9;
	}
    
	public boolean estanInvasoresExterminados(){
		boolean estadoTodosInvasores = true;
		for (Invasor inv : invasores) {
			if(inv.isVivo())
				estadoTodosInvasores = false;					
		}
		if(estadoTodosInvasores){
			this.pasarNivel();
			return true;
		}
		return false;
	}
	
	private void pasarNivel() {
		this.hacia = 1;
		this.contadorBajada = 1;
		this.proyectiles = null;
		this.invasores = null;
		this.puntaje = this.puntaje + 50;
		this.proyectiles = new ArrayList<Proyectil>();
		this.nivel = this.nivel + 1;
    	this.invasores = this.generarHordaDeInvasores();
    	this.proyectiles = new ArrayList<Proyectil>();
    	this.muros = this.generarMuros();
	}
	
	public int getNivel() {
		return this.nivel;
	}
	
	public Puntaje agregarPuntaje(String nombreJugador) {
		Puntaje puntoPartida = new Puntaje(nombreJugador, this.puntaje);
		return puntoPartida;
	}


}