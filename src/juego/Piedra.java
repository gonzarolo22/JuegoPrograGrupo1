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
	
	public boolean chocaConTigre(Tigre t) {
		return (t.getX() - t.getAncho()/2 < x + diametro/2 && 
				x - diametro/2 < t.getX() + t.getAncho()/2 &&
				
				t.getY() - t.getAlto()/2 < y + diametro/2 &&
				y - diametro/2 < t.getY() + t.getAlto()/2);
	}
		
	public boolean chocaConSerpiente(Serpiente s) {
		return (s.getX() - s.getAncho()/2 < x + diametro/2 && 
				x - diametro/2 < s.getX() + s.getAncho()/2 &&
				
				s.getY() - s.getAlto()/2 < y + diametro/2 &&
				y - diametro/2 < s.getY() + s.getAlto()/2);
	}
	}
	public void lanzar(double g) {
		this.x += g;
	}
	public void saltar(double s) {
		this.y -=s;
	}
	public void gravedad(int g) {
		this.y += g;
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
