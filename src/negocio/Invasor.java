package negocio;


public class Invasor {

	public Invasor() {
    }

    private int posicionX;
    private int posicionY;
    private int velocidad;
    private boolean estado;
    
    public Invasor(int posicionX, int posicionY, int velocidad) {
    	this.posicionX = posicionX;
    	this.posicionY = posicionY;
    	this.estado = true;
    	this.velocidad = velocidad;
    }

	public boolean recibirDisparo(int posicionDisparoX, int posicionDisparoY){
		if(posicionDisparoX > this.posicionX && posicionDisparoX < this.posicionX + 40)
			if(posicionDisparoY < this.posicionY + 14 && posicionDisparoY > this.posicionY - 14){
				estado = false;
				return true;
			}
		return false;
	}
	
	public Proyectil disparar() {
		Proyectil proy = new Proyectil(posicionX, posicionY, 1, this.velocidad);
		return proy;
	}

    public void moverInvasorDerecha() {
    	posicionX = posicionX + 1 * this.velocidad;
    }
    
    public void moverInvasorIzquierda() {
    	posicionX = posicionX - 1 * this.velocidad;
    }
    
	public void moverInvasorAbajo(){
		posicionY = posicionY + 10;
	}

	public void moverInvasorArriba(){
		posicionY = posicionY - 10;
	}
    
    public int getPosicionX() {
    	return this.posicionX;
    }
    
    public int getPosicionY() {
    	return this.posicionY;
    }
    
    public boolean isVivo() {
    	return this.estado;
    }

}