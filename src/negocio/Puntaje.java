package negocio;


public class Puntaje {

    private String nombreJugador;
    private int puntajeJugador;

    public Puntaje(String nombreJugador, int puntajeJugador) {
    	this.setNombreJugador(nombreJugador);
    	this.setPuntajeJugador(puntajeJugador);
    }
    public Puntaje() {}

	public String getNombreJugador() {
		return nombreJugador;
	}
	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}
	public int getPuntajeJugador() {
		return puntajeJugador;
	}
	public void setPuntajeJugador(int puntajeJugador) {
		this.puntajeJugador = puntajeJugador;
	}

    
    

}