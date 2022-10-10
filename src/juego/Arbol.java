package juego;

import java.awt.Color;
import java.util.Random;

import entorno.Entorno;
public class Arbol {
	
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private int angulo;
	private Color color;
	
	
	public Arbol(int x, int y) {
		this.x=x;
		this.y=y;
		this.ancho=100;
		this.alto=5;
		this.angulo=0;
		this.color=Color.GRAY;
}// Arbol
	
	public void dibujarArbol(Entorno e) {
		
		Random rand1 = new Random();
		  System.out.println(rand1.nextInt(4) + 1);
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, this.angulo, this.color);
		
	}//crearArbol
	
	public void desplazar() {
		this.x-=1;
	}
	
	

}// class Arbol
