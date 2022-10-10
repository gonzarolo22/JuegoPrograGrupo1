package juego;

import java.awt.Color;

import entorno.Entorno;

public class Serpiente {
	
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double angulo;
	private Color color;
	
	
	public Serpiente(double x, double y) {
		this.x=x;
		this.y=y;
		this.ancho=20;
		this.alto=40;
		this.angulo=0;
		this.color=Color.CYAN;
}// Arbol
	
	public void dibujarSerpiente(Entorno e) {
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, this.angulo, this.color);
	}//dibujarSerpiente
	
	public void desplazar() {
		this.x-=2;
	}//desplazar
	
	public void mostrar() {
		System.out.println(this.x);
	}


	
	

}// class Tigre

