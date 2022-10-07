package juego;

import java.awt.Color;

import entorno.Entorno;

public class Rectangulo {
	int x;
	int y;
	int ancho;
	int largo;
	int angulo;
	Color color;
	
	
	public Rectangulo(int x, int y) {
		this.x=x;
		this.y=y;
		this.ancho=100;
		this.largo=200;
		this.angulo=0;
		this.color=Color.white;
}
	
	public void dibujarRectangulo(Entorno e) {
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.ancho, this.angulo, this.color);
	}

	
	
}
