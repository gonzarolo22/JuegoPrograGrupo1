 package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Tigre {
	
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double angulo;
	private Color color;
	private Image imag;
	
	
	public Tigre(int x, double y) {
		this.x=x;
		this.y=y;
		this.ancho=100;
		this.alto=60;
		this.angulo=0;
		this.color=Color.magenta;
		this.imag = Herramientas.cargarImagen("tigre.gif");
}// Arbol
	
	public void dibujarTigre(Entorno e) {
		//e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, this.angulo, this.color);
		e.dibujarImagen(imag, x, y, 0, 0.50);
	}//dibujarTigre
	
	public void desplazar() {
		this.x-=2;
	}//desplazar
	
	public void mostrar() {
		System.out.println(this.x);
	}


	
	

}// class Tigre
