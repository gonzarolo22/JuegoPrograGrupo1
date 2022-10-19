package juego;
// EN SELVA CREO LOS ARBOLES.
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
	private int aleatorio;
	
	public Arbol(double x, double y, int aleatorio) {   //le creo aleatorio para que cuando crée un arbol le de un aleatorio 
		this.x=x;										//entre 1 y 5, y si coincide entre el aleatorio de serpiente dibuja a la serpiente
		this.y=y;
		this.ancho=100;
		this.alto=5;
		this.angulo=0;
		this.color=Color.GRAY;
		this.aleatorio= aleatorio;
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
	
	
	public double getAncho() {
		return ancho;
	}



	public double getAlto() {
		return alto;
	}



	public void setAleatorio(int aleatorio) {
		this.aleatorio = aleatorio;
	}
	
	
	

}// class Arbol
