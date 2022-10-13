package juego;

import java.awt.Color;

import entorno.Entorno;

public class Mono {
	private int x;
	private int y;
	private int ancho;
	private int largo;
	private int angulo;
	private Color color;

	
	
	
	//prueba


	public Mono(int x, int y) {
		this.x=x;
		this.y=y;
		this.ancho=50;
		this.largo=60;
		this.angulo=0;
		this.color=Color.white;
		
}
	
	public void dibujarMono(Entorno e) {
		e.dibujarRectangulo(this.x+this.ancho,this.y, this.ancho, this.largo, this.angulo, this.color);
	}
	
	public void saltar(int s) {
		this.y -=s;
	}//saltar
	
	public void gravedad(double g) {
		this.y += g;
	}
	
	public boolean chocaConSuelo(Entorno e, Suelo s) {
		if (this.y > e.alto()-s.alto) {
			return true;
		}else {
			return false;
		}
	}//chocaConSuelo
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}//class Mono
