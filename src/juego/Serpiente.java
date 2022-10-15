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
	private double aleatorio;
	
	
	public Serpiente() {
		this.x=0;
		this.y=0;
		this.ancho=40;
		this.alto=40;
		this.angulo=0;
		this.color=Color.CYAN;
		this.aleatorio=  1;
}// Arbol
	
	public void dibujarSerpiente(Entorno e) {
		e.dibujarRectangulo(this.x, this.y-this.alto/2, this.ancho, this.alto, this.angulo, this.color);
	}//dibujarSerpiente
	
	public void desplazar() {
		this.x-=2;
	}//desplazar
	
	public void mostrar() {
		System.out.println(this.x);
	}
	
	public boolean siCoincideConArbol(Arbol a) {
		if(a.getAleatorio() == this.aleatorio) {
			this.x=a.getX();
			this.y=a.getY();
			return true;
		}
		else {
			return false;
		}
		
		
	}


	
	

}// class Tigre

