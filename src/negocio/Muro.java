package negocio;


public class Muro {


    private int salud;
    private boolean estado;
    private int posicionX;
    private int posicionY;


    public Muro(int posicionX){
		this.salud = 100;
		this.posicionX = posicionX;
		this.posicionY = 450;
		this.estado = true;		
	}
	
	public int getSalud() {
		return this.salud;
	}

	public int getPosicionX() {
		return this.posicionX;
	}

	public int getPosicionY() {
		return this.posicionY;
	}

	public boolean isVivo() {
		return estado;
	}

	public void disminuirVida10() {
		this.salud = this.salud - 10;
	}
	
	public void disminuirVida5() {
		this.salud = this.salud - 5;
	}
	
	public boolean recibiDisparo(int deQuien,int posicionDisparoX, int posicionDisparoY) {
		if(posicionDisparoX > this.posicionX - 10 && posicionDisparoX < this.posicionX + 38
				&& posicionDisparoY > this.posicionY - 20 && posicionDisparoY < this.posicionY + 20 ){			
			if(deQuien == 1)
				disminuirVida5(); 
			if(deQuien == -1)
				disminuirVida10();
			if(this.salud <= 0){
				this.estado = false;
			}
			return true;
		} else
			return false;
	
	}


}