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
	
	
	public Arbol(int x, double y) {
		this.x=x;
		this.y=y;
		this.ancho=100;
		this.alto=5;
		this.angulo=0;
		this.color=Color.GRAY;
}// Arbol
	
	public void dibujarArbol(Entorno e) {
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, this.angulo, this.color);
	}//crearArbol
	
	public void desplazar() {
		this.x-=1;
	}//desplazar
	
	

}// class Arbol
