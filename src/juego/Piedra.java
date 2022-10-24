package juego;

import java.awt.Color;

import entorno.Entorno;

public class Piedra {
	
	private double x;
	private double y;
	private double diametro;
	private Color color;
	
	
	public Piedra(double x, double y) {
		this.x=x;
		this.y=y;
		this.diametro=20;
		this.color= Color.white;
	}
	
	public void crearPiedra(Entorno e) {
		e.dibujarCirculo(x, y, diametro, color);
		
	}
	public void lanzar(double g) {
		this.x += g;
	}
	public void saltar(double s) {
		this.y -=s;
	}
	public void gravedad() {
		this.y +=4.5;
	}
	
	
	
	public boolean saleDePantalla(Entorno e) {
		if (this.x > e.ancho() ) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	
	
	
}
