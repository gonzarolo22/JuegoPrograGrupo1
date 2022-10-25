package juego;

import java.awt.Color;

import entorno.Entorno;

public class Suelo {
	int x;
	int y;
	int ancho;
	int alto;
	int angulo;
	Color color;
	
	//constructor
	public Suelo (Entorno e,int x){
		this.x=x;
		this.y=e.alto()-this.alto/2;
		this.ancho= e.ancho();
		this.alto=100;
		this.angulo=0;
		this.color=Color.green;
	}
	
	
	public void dibujarRectangulo(Entorno e) {
		e.dibujarRectangulo(this.x, e.alto()-this.alto/2, this.ancho, this.alto, this.angulo, this.color);
	}

}
