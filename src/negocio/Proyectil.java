package negocio;


public class Proyectil {

    private int posicionX;
    private int posicionY;
    private boolean estado;
    private int sentido;
    private int velocidad;
	
	public Proyectil(int posicionX, int posicionY, int sentido, int velocidad) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.sentido = sentido;
		this.estado = true;
		this.setVelocidad(velocidad);
	}

    public void setEstado(boolean vivo){
		this.estado= vivo;
	}
	
	public boolean getEstado(){
		return this.estado;
	}
	
	public int getPosicionX() {
		return posicionX;
	}
	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}
	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}


	public void mover(int velocidad) {
		this.posicionY = posicionY + (this.sentido * velocidad) + (this.sentido * 6);
	
	}


	public int getSentido() {
		return this.sentido;
	}


	public void setSentido(int sentido) {
		this.sentido = sentido;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
	

}