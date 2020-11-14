package negocio;



public class Bateria {

    public Bateria(int posicionX, int posicionY) {
    	this.posicionX = posicionX;
    	this.posicionY = posicionY;
    }

    private int posicionX;
    private int posicionY;

	public Proyectil disparar() {
		Proyectil proyectil = new Proyectil(posicionX + 20, 480, -1, 1);
		return proyectil;
	}
	
	public boolean recibirDisparo(int posicionDisparoX, int posicionDisparoY){
		if(posicionDisparoX > this.posicionX && posicionDisparoX < this.posicionX + 40)
			if(posicionDisparoY > 475 && posicionDisparoY < 505){
				return true;
			}
		return false;
	}

    public void moverBateriaIzquierda() {
    	this.posicionX = posicionX - 5;
    }

    public void moverBateriaDerecha() {
    	this.posicionX = posicionX + 5;
    }
    
    public int getPosicionX() {
    	return this.posicionX;
    }
    
    public int getPosicionY() {
    	return this.posicionY;
    }

}