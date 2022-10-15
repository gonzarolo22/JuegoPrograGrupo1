package juego;

import java.awt.Color;
import java.util.Random;

import entorno.Entorno;
public class Arbol {
	
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double angulo;
	private Color color;
	private double aleatorio;
	
	public Arbol(double x, double y) {
		this.x=x;
		this.y=y;
		this.ancho=100;
		this.alto=5;
		this.angulo=0;
		this.color=Color.GRAY;
		this.aleatorio= 1;
}// Arbol
	
	public void dibujarArbol(Entorno e) {
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, this.angulo, this.color);
	}//crearArbol
	
	public void desplazar() {
		this.x-=2;
	}//desplazar

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getAleatorio() {
		return aleatorio;
	}

	public void setAleatorio(double aleatorio) {
		this.aleatorio = aleatorio;
	}
	
	
	

}// class Arbol
