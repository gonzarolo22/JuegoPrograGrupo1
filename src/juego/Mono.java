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
		this.imag = Herramientas.cargarImagen("mono1.gif");
		
		
}
	
	public void dibujarMono(Entorno e) {
		//e.dibujarRectangulo(this.x,this.y,this.ancho, this.alto, this.angulo, this.color);
		e.dibujarImagen(imag, x, y, 0, 1.06);
		
	}
	
	public void saltar() {
		if(this.y>200) {
		this.y -=8;
		}
	}
	
	public void gravedad(Entorno e) {
		if(this.y < e.alto()-200) {
			this.y += 8;
		}
	}
	
	public boolean chocaConArbol(Arbol a) {
		return (a.getX() - a.getAncho()/2 < x + ancho/2 && 
				x - ancho/2 < a.getX() + a.getAncho()/2 &&
				
				a.getY() - a.getAlto()/2 < y + ancho/2 &&
				y - ancho/2 < a.getY() + a.getAlto()/2);
	}
	
	public void monoEnArbol(Arbol a) {
		this.x = a.getX();
		this.y=a.getY()-30;
	}
	
	
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
