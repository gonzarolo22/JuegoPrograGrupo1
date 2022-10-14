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
	
	public void CrearPiedra(Entorno e) {
		e.dibujarCirculo(x, y, diametro, color);
		
	}
	public void saltar(double s) {
		this.y -=s;
	}
	public void gravedad(double g) {
		this.y += g;
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
