package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Mono {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double angulo;
	private Color color;
	private Image imag;
	
	
	
	


	public Mono(int x, int y) {
		this.x=x;
		this.y=y;
		this.ancho=50;
		this.alto=60;
		this.angulo=0;
		this.color=Color.blue;
		
		
}
	
	public void dibujarMono(Entorno e) {
		e.dibujarRectangulo(this.x,this.y,this.ancho, this.alto, this.angulo, this.color);
		
	}
	
	public void saltar() {
		this.y -=8;
	}
	
	public void gravedad(Entorno e) {
		if(this.y < e.alto()-200) {
			this.y += 8;
		}
	}
	
	public void chocaConArbol(Arbol a) {
		if (this.y == a.getY() && this.x==a.getX()) {
			this.y=a.getY();
			this.x=a.getX();
		}
	}//chocaConSuelo
	
	
	public double getX() {
		return x; 
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}//class Mono
